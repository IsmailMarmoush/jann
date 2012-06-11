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

import com.marmoush.jann.model.regression.FeedForwardSvNet;
import com.marmoush.jann.sv.SvLayer;
import com.marmoush.jann.utils.functors.ITransfere;

/**
 * The Class TrainSvBPFF.
 */
public class TrainSvBPFF extends TrainSv {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -2130076899254603506L;

    /** The diff transfere fnctr. */
    private ITransfere diffTransfereFnctr;

    /**
     * Instantiates a new train sv bpff.
     * 
     * @param transFnct
     *            the trans fnct
     * @param performanceGoal
     *            the performance goal
     * @param timeLimit
     *            the time limit
     * @param maxEpochs
     *            the max epochs
     */
    public TrainSvBPFF(ITransfere transFnct, double performanceGoal,
	    long timeLimit, int maxEpochs) {
	super(performanceGoal, timeLimit, maxEpochs);
	this.diffTransfereFnctr = transFnct;
    }

    /**
     * Back propagate.
     * 
     * @param net
     *            the net
     */
    public void backPropagate(FeedForwardSvNet net) {
	for (int i = net.size() - 1; i > 0; i--) {
	    backPropagate(net.get(i - 1), net.get(i));
	}
    }

    /**
     * Back propagate.
     * 
     * @param srcLayer
     *            the src layer
     * @param destLayer
     *            the dest layer
     */
    public void backPropagate(SvLayer srcLayer, SvLayer destLayer) {
	// BPError= w' * error
	DoubleMatrix error = destLayer.getWeight().transpose()
		.mmul(destLayer.getError());
	srcLayer.setError(error);
    }

    /**
     * Gets the d b_ bp delta rule.
     * 
     * @param svLayer
     *            the sv layer
     * @return the d b_ bp delta rule
     */
    public DoubleMatrix getDB_BPDeltaRule(SvLayer svLayer) {
	double lrnRate = svLayer.getLearnRate();
	DoubleMatrix bias = svLayer.getBias();
	DoubleMatrix error = svLayer.getError();
	DoubleMatrix diffOutput = this.diffTransfereFnctr.transfere(svLayer);
	return error.mul(lrnRate).mul(diffOutput).mul(bias);
    }

    /**
     * Gets the diff transfere fnctr.
     * 
     * @return the diff transfere fnctr
     */
    public ITransfere getDiffTransfereFnctr() {
	return diffTransfereFnctr;
    }

    /**
     * Gets the d w_ bp delta rule.
     * 
     * @param svLayer
     *            the sv layer
     * @return the d w_ bp delta rule
     */
    public DoubleMatrix getDW_BPDeltaRule(SvLayer svLayer) {
	double lrnRate = svLayer.getLearnRate();
	DoubleMatrix inputTransposed = svLayer.getInput().transpose();
	DoubleMatrix error = svLayer.getError();
	DoubleMatrix diffOutput = this.diffTransfereFnctr.transfere(svLayer);
	return error.mul(lrnRate).mul(diffOutput).mul(inputTransposed);
    }

    /**
     * Sets the diff transfere fnctr.
     * 
     * @param diffTransfereFnctr
     *            the new diff transfere fnctr
     */
    public void setDiffTransfereFnctr(ITransfere diffTransfereFnctr) {
	this.diffTransfereFnctr = diffTransfereFnctr;
    }

    /**
     * Stochastic once.
     * 
     * @param net
     *            the net
     * @param input
     *            the input
     * @param target
     *            the target
     * @return the double
     */
    public double stochasticOnce(FeedForwardSvNet net, DoubleMatrix input,
	    DoubleMatrix target) {
	net.setInput(input);
	net.setTarget(target);
	net.simulate();
	net.getOutputLayer().updateError();
	backPropagate(net);
	updateWeightAndBias(net);
	net.simulate();
	net.getOutputLayer().updateError();
	net.getOutputLayer().updatePerformance();
	return net.getOutputLayer().getPerformance();
    }

    /**
     * Stochastic once.
     * 
     * @param net
     *            the net
     * @param inputList
     *            the input list
     * @param targetList
     *            the target list
     * @return the double
     */
    public double stochasticOnce(FeedForwardSvNet net,
	    List<DoubleMatrix> inputList, List<DoubleMatrix> targetList) {
	double performanceAverage = 0;
	for (int i = 0; i < inputList.size(); i++) {
	    double performance = stochasticOnce(net, inputList.get(i),
		    targetList.get(i));
	    performanceAverage += performance;
	}
	performanceAverage /= inputList.size();
	return performanceAverage;
    }

    /**
     * Stochastic to limits.
     * 
     * @param net
     *            the net
     * @param inputList
     *            the input list
     * @param targetList
     *            the target list
     * @return the train result
     */
    public TrainResult stochasticToLimits(FeedForwardSvNet net,
	    List<DoubleMatrix> inputList, List<DoubleMatrix> targetList) {
	TrainResult result = new TrainResult();
	result.start();
	for (int i = 0; i < getEpochs(); i++) {
	    double performance = stochasticOnce(net, inputList, targetList);
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
     * @param net
     *            the net
     * @param input
     *            the input
     * @param target
     *            the target
     * @return the train result
     */
    public TrainResult stochasticToLimits(FeedForwardSvNet net,
	    DoubleMatrix input, DoubleMatrix target) {
	TrainResult result = new TrainResult();
	result.start();
	for (int i = 0; i < getEpochs(); i++) {
	    double performance = stochasticOnce(net, input, target);
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
     * Update dwd b_ bp delta rule.
     * 
     * @param svLayer
     *            the sv layer
     */
    public void updateDWDB_BPDeltaRule(SvLayer svLayer) {
	double lrnRate = svLayer.getLearnRate();
	DoubleMatrix inputTransposed = svLayer.getInput().transpose();
	DoubleMatrix error = svLayer.getError();
	DoubleMatrix diffOutput = this.diffTransfereFnctr.transfere(svLayer);
	DoubleMatrix bias = svLayer.getBias();
	// update
	DoubleMatrix dw = error.mul(lrnRate).mul(diffOutput)
		.mmul(inputTransposed);
	DoubleMatrix db = error.mul(lrnRate).mul(diffOutput).mul(bias);
	svLayer.getWeight().addi(dw);
	svLayer.getBias().addi(db);
    }

    /**
     * Update weight and bias.
     * 
     * @param net
     *            the net
     */
    public void updateWeightAndBias(FeedForwardSvNet net) {
	for (SvLayer svLayer : net) {
	    updateDWDB_BPDeltaRule(svLayer);
	}
    }
}
