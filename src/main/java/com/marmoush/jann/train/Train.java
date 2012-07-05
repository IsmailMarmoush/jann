/*
 * Copyright 2012 Ismail Marmoush
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

import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class TrainResult.
 */
public abstract class Train {

    /** The Constant EPOCHS_REACHED. */
    public static final String EPOCHS_REACHED = "EpochsReached";

    /** The Constant PERFORMANCE_REACHED. */
    public static final String PERFORMANCE_REACHED = "PerformanceGoalMet";

    /** The Constant TIME_LIM_REACHED. */
    public static final String TIME_LIM_REACHED = "MaxTimeExceeded";

    /** The at epoch. */
    private int atEpoch;

    /** The cause. */
    private String cause;

    /** The end. */
    private long end;

    /** The max epochs. */
    private int maxEpochs;

    /** The performance goal. */
    private double performanceGoal;

    /** The performance history. */
    private List<Double> performanceHistory = new ArrayList<Double>();

    /** The start. */
    private long start;

    /** The time elapsed. */
    private long timeElapsed;

    /** The time limit. */
    private long timeLimit;

    /**
     * Instantiates a new train.
     */
    public Train() {
    }

    /**
     * Instantiates a new train.
     *
     * @param maxEpochs the max epochs
     * @param performanceGoal the performance goal
     * @param timeLimit the time limit
     */
    public Train(int maxEpochs, double performanceGoal, long timeLimit) {
	super();
	this.maxEpochs = maxEpochs;
	this.performanceGoal = performanceGoal;
	this.timeLimit = timeLimit;
    }

    /**
     * Adds the performance history entry.
     *
     * @param performance the performance
     */
    public void addPerformanceHistoryEntry(double performance) {
	getPerformanceHistory().add(performance);
    }

    /**
     * End.
     * 
     * @param cause
     *            the cause
     * @param atEpoch
     *            the at epoch
     */
    public void end(String cause, int atEpoch) {
	this.end = System.currentTimeMillis();
	this.timeElapsed = end - start;
	this.cause = cause;
	this.atEpoch = atEpoch;
    }

    /**
     * Gets the max epochs.
     *
     * @return the max epochs
     */
    public int getMaxEpochs() {
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
     * Gets the performance history.
     *
     * @return the performance history
     */
    public List<Double> getPerformanceHistory() {
	return performanceHistory;
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
     * Run.
     */
    public void run() {
	start();
	for (int i = 0; i < getMaxEpochs(); i++) {
	    double performance = train();
	    addPerformanceHistoryEntry(performance);
	    if (performance < getPerformanceGoal()) {
		end(PERFORMANCE_REACHED, i);
		return;
	    }
	}
	end(EPOCHS_REACHED, getMaxEpochs());
    }

    /**
     * Sets the max epochs.
     *
     * @param maxEpochs the new max epochs
     */
    public void setMaxEpochs(int maxEpochs) {
	this.maxEpochs = maxEpochs;
    }

    /**
     * Sets the performance goal.
     *
     * @param performanceGoal the new performance goal
     */
    public void setPerformanceGoal(double performanceGoal) {
	this.performanceGoal = performanceGoal;
    }

    /**
     * Sets the performance history.
     *
     * @param performanceHistory the new performance history
     */
    public void setPerformanceHistory(List<Double> performanceHistory) {
	this.performanceHistory = performanceHistory;
    }

    /**
     * Sets the time limit.
     *
     * @param timeLimit the new time limit
     */
    public void setTimeLimit(long timeLimit) {
	this.timeLimit = timeLimit;
    }

    /**
     * Start.
     */
    public void start() {
	this.start = System.currentTimeMillis();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("Train Stopped because=");
	builder.append(cause);
	builder.append("\nmaxEpochs=");
	builder.append(maxEpochs);
	builder.append("\nperformanceGoal=");
	builder.append(performanceGoal);
	builder.append("\ntimeLimit=");
	builder.append(timeLimit);
	builder.append("\natEpoch=");
	builder.append(atEpoch);
	builder.append("\ntimeElapsed=");
	builder.append(timeElapsed);
	builder.append("\nperformanceHistory=");
	builder.append(performanceHistory);
	return builder.toString();
    }

    /**
     * Train.
     *
     * @return the double
     */
    public abstract double train();

}
