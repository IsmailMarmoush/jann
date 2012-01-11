/*
 * Copyright 2011 Ismail Marmoush
 * 
 * This file is part of JANN.
 * 
 * JANN is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License Version 3 as published by the Free
 * Software Foundation, either version 3 of the License, or any later version.
 * 
 * JANN is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * JANN. If not, see http://www.gnu.org/licenses/.
 * 
 * For More Information Please Visit http://jann.marmoush.com
 */
package com.marmoush.jann.train;

import java.util.List;

import org.jblas.DoubleMatrix;

import com.marmoush.jann.sv.SvLayer;

/**
 * The Class TrainSvPerceptron.
 */
public class TrainSvPerceptron extends TrainSv {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8805238891574500077L;

	/**
	 * Instantiates a new train sv perceptron.
	 * 
	 * @param performanceGoal
	 *            the performance goal
	 * @param timeLimit
	 *            the time limit
	 * @param maxEpochs
	 *            the max epochs
	 */
	public TrainSvPerceptron(double performanceGoal, long timeLimit,
			int maxEpochs) {
		super(performanceGoal, timeLimit, maxEpochs);
	}

	/**
	 * Batch once.
	 * 
	 * @param svLayer
	 *            the sv layer
	 * @param inputList
	 *            the input list
	 * @param targetList
	 *            the target list
	 * @return the double
	 */
	public double batchOnce(SvLayer svLayer, List<DoubleMatrix> inputList,
			List<DoubleMatrix> targetList) {
		// DW = lrnRate*SUM(error*inputTransposed)
		updateDWDBsum_DeltaRule(svLayer, inputList, targetList);
		getDeltaWeightSum().muli(svLayer.getLearnRate());
		// weight=oldWeight+ lrnRate*sum(deltaweight)
		svLayer.getWeight().addi(getDeltaWeightSum());
		// bias =oldbias+ sum(bias)
		svLayer.getBias().addi(getDeltaBiasSum());
		//
		svLayer.simulate();
		svLayer.updateError();
		return svLayer.updatePerformance();
	}

	/**
	 * Batch to limits.
	 * 
	 * @param svLayer
	 *            the sv layer
	 * @param inputList
	 *            the input list
	 * @param targetList
	 *            the target list
	 * @return the train result
	 */
	public TrainResult batchToLimits(SvLayer svLayer,
			List<DoubleMatrix> inputList, List<DoubleMatrix> targetList) {
		TrainResult result = new TrainResult();
		result.start();
		for (int i = 0; i < getEpochs(); i++) {
			double performance = batchOnce(svLayer, inputList, targetList);
			result.updatePerformanceAverage(performance);
			if (result.getPerformanceAverage() < getPerformanceGoal()) {
				result.end(TrainResult.PERFORMANCE_REACHED, i);
				return result;
			}

		}
		result.end(TrainResult.EPOCHS_REACHED, getEpochs());
		return result;
	}

	/**
	 * Delta rule.
	 * 
	 * @param input
	 *            the input
	 * @param error
	 *            the error
	 * @return the double matrix
	 */
	public DoubleMatrix deltaRule(DoubleMatrix input, DoubleMatrix error) {
		// DeltaWeight = (error * inputTransposed) ;
		return error.mmul(input.transpose());
	}

	public double stochasticOnce(SvLayer svLayer, DoubleMatrix input,
			DoubleMatrix target) {
		// DeltaWeight*learnRate
		updateDWDB_DeltaRule(svLayer, input, target);
		getDeltaWeight().muli(svLayer.getLearnRate());
		// weight=weight+deltaWeight
		svLayer.getWeight().addi(getDeltaWeight());
		// bias= bias+deltaBias
		svLayer.getBias().addi(getDeltaBias());
		//
		svLayer.simulate();
		svLayer.updateError();
		return svLayer.updatePerformance();
	}

	/**
	 * Stochastic once.
	 * 
	 * @param svLayer
	 *            the sv layer
	 * @param inputList
	 *            the input list
	 * @param targetList
	 *            the target list
	 * @return the double
	 */
	public double stochasticOnce(SvLayer svLayer, List<DoubleMatrix> inputList,
			List<DoubleMatrix> targetList) {
		double performanceSum = 0;
		for (int i = 0; i < inputList.size(); i++) {
			performanceSum += stochasticOnce(svLayer, inputList.get(i),
					targetList.get(i));
		}
		performanceSum /= inputList.size();
		return performanceSum;
	}

	public TrainResult stochasticToLimits(SvLayer svLayer, DoubleMatrix input,
			DoubleMatrix target) {
		TrainResult result = new TrainResult();
		result.start();
		for (int i = 0; i < getEpochs(); i++) {
			double performance = stochasticOnce(svLayer, input, target);
			result.updatePerformanceAverage(performance);
			if (result.getPerformanceAverage() < getPerformanceGoal()) {
				result.end(TrainResult.PERFORMANCE_REACHED, i);
				return result;
			}
		}
		result.end(TrainResult.EPOCHS_REACHED, getEpochs());
		return result;
	}

	/**
	 * Stochastic to limits.
	 * 
	 * @param svLayer
	 *            the sv layer
	 * @param inputList
	 *            the input list
	 * @param targetList
	 *            the target list
	 * @return the train result
	 */
	public TrainResult stochasticToLimits(SvLayer svLayer,
			List<DoubleMatrix> inputList, List<DoubleMatrix> targetList) {
		/*
		 * For each epoch, all training vectors (or sequences) are each
		 * presented once in a different random order, with the network and
		 * weight and bias values updated accordingly after each individual
		 * presentation.
		 * 
		 * Training stops when any of these conditions is met: The maximum
		 * number of epochs (repetitions) is reached. Performance is minimized
		 * to the goal. The maximum amount of time is exceeded.
		 */
		TrainResult result = new TrainResult();
		result.start();
		for (int i = 0; i < getEpochs(); i++) {
			double performance = stochasticOnce(svLayer, inputList, targetList);
			result.updatePerformanceAverage(performance);
			if (result.getPerformanceAverage() < getPerformanceGoal()) {
				result.end(TrainResult.PERFORMANCE_REACHED, i);
				return result;
			}
		}
		result.end(TrainResult.EPOCHS_REACHED, getEpochs());
		return result;
	}

	/**
	 * Update dwd b_ delta rule.
	 * 
	 * @param svLayer
	 *            the sv layer
	 * @param input
	 *            the input
	 * @param target
	 *            the target
	 */
	public void updateDWDB_DeltaRule(SvLayer svLayer, DoubleMatrix input,
			DoubleMatrix target) {
		svLayer.setInput(input);
		svLayer.simulate();
		svLayer.setTarget(target);
		svLayer.updateError();
		DoubleMatrix dw = deltaRule(input, svLayer.getError());
		setDeltaWeight(dw);
		DoubleMatrix db = svLayer.getError();
		setDeltaBias(db);
	}

	/**
	 * Update dwd bsum_ delta rule.
	 * 
	 * @param svLayer
	 *            the sv layer
	 * @param inputList
	 *            the input list
	 * @param targetList
	 *            the target list
	 */
	public void updateDWDBsum_DeltaRule(SvLayer svLayer,
			List<DoubleMatrix> inputList, List<DoubleMatrix> targetList) {
		/*
		 * Each weight and bias is updated according to its learning function
		 * after each epoch (one pass through the entire set of input vectors).
		 */
		int rows = svLayer.getWeight().rows;
		int cols = svLayer.getWeight().columns;
		// Reset DeltaWeightSum
		setDeltaWeightSum(DoubleMatrix.zeros(rows, cols));
		// Reset DeltaBiasSum
		setDeltaBiasSum(DoubleMatrix.zeros(svLayer.getBias().length));
		for (int i = 0; i < inputList.size(); i++) {
			updateDWDB_DeltaRule(svLayer, inputList.get(i), targetList.get(i));
			// deltaWeightSum=DeltaWeightSum+deltaWeight
			getDeltaWeightSum().addi(getDeltaWeight());
			// deltaBiasSum=deltaBiasSum + deltaBias
			getDeltaBiasSum().addi(getDeltaBias());
		}
	}
}
