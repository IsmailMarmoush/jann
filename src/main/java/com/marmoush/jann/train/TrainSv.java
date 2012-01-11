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

import java.io.Serializable;

import org.jblas.DoubleMatrix;

/**
 * The Class TrainSv.
 */
public class TrainSv implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5129138762577069628L;

	/** The delta bias. */
	private DoubleMatrix deltaBias;

	/** The delta bias sum. */
	private DoubleMatrix deltaBiasSum;

	/** The delta weight. */
	private DoubleMatrix deltaWeight;

	/** The delta weight sum. */
	private DoubleMatrix deltaWeightSum;

	/** The max epochs. */
	private int maxEpochs;

	/** The performance goal. */
	private double performanceGoal;

	/** The time limit. */
	private long timeLimit;

	/**
	 * Instantiates a new train sv.
	 */
	public TrainSv() {
		super();
	}

	/**
	 * Instantiates a new train sv.
	 * 
	 * @param performanceGoal
	 *            the performance goal
	 * @param timeLimit
	 *            the time limit
	 * @param maxEpochs
	 *            the max epochs
	 */
	public TrainSv(double performanceGoal, long timeLimit, int maxEpochs) {
		super();
		this.performanceGoal = performanceGoal;
		this.timeLimit = timeLimit;
		this.maxEpochs = maxEpochs;
	}

	/**
	 * Gets the delta bias.
	 * 
	 * @return the delta bias
	 */
	public DoubleMatrix getDeltaBias() {
		return deltaBias;
	}

	/**
	 * Gets the delta bias sum.
	 * 
	 * @return the delta bias sum
	 */
	public DoubleMatrix getDeltaBiasSum() {
		return deltaBiasSum;
	}

	/**
	 * Gets the delta weight.
	 * 
	 * @return the delta weight
	 */
	public DoubleMatrix getDeltaWeight() {
		return deltaWeight;
	}

	/**
	 * Gets the delta weight sum.
	 * 
	 * @return the delta weight sum
	 */
	public DoubleMatrix getDeltaWeightSum() {
		return deltaWeightSum;
	}

	/**
	 * Gets the epochs.
	 * 
	 * @return the epochs
	 */
	public int getEpochs() {
		return maxEpochs;
	}

	/**
	 * Gets the performance goal.
	 * 
	 * @return the performance goal
	 */
	public double getPerformanceGoal() {
		return performanceGoal;
	}

	/**
	 * Gets the time limit.
	 * 
	 * @return the time limit
	 */
	public long getTimeLimit() {
		return timeLimit;
	}

	/**
	 * Sets the delta bias.
	 * 
	 * @param deltaBias
	 *            the new delta bias
	 */
	public void setDeltaBias(DoubleMatrix deltaBias) {
		this.deltaBias = deltaBias;
	}

	/**
	 * Sets the delta bias sum.
	 * 
	 * @param deltaBiasSum
	 *            the new delta bias sum
	 */
	public void setDeltaBiasSum(DoubleMatrix deltaBiasSum) {
		this.deltaBiasSum = deltaBiasSum;
	}

	/**
	 * Sets the delta weight.
	 * 
	 * @param deltaWeight
	 *            the new delta weight
	 */
	public void setDeltaWeight(DoubleMatrix deltaWeight) {
		this.deltaWeight = deltaWeight;
	}

	/**
	 * Sets the delta weight sum.
	 * 
	 * @param deltaWeightSum
	 *            the new delta weight sum
	 */
	public void setDeltaWeightSum(DoubleMatrix deltaWeightSum) {
		this.deltaWeightSum = deltaWeightSum;
	}

	/**
	 * Sets the epochs.
	 * 
	 * @param maxEpochs
	 *            the new epochs
	 */
	public void setEpochs(int maxEpochs) {
		this.maxEpochs = maxEpochs;
	}

	/**
	 * Sets the performance goal.
	 * 
	 * @param performanceGoal
	 *            the new performance goal
	 */
	public void setPerformanceGoal(double performanceGoal) {
		this.performanceGoal = performanceGoal;
	}

	/**
	 * Sets the time limit.
	 * 
	 * @param timeLimit
	 *            the new time limit
	 */
	public void setTimeLimit(long timeLimit) {
		this.timeLimit = timeLimit;
	}

}