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

/**
 * The Class TrainSv.
 */
public class TrainSv implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 5129138762577069628L;

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