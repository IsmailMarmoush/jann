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

/**
 * The Class TrainResult.
 */
public class TrainResult {

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

    /** The performance average. */
    private double performanceAverage = 0;

    /** The performance average factor. */
    private int performanceAverageFactor = 0;

    /** The start. */
    private long start;

    /** The time elapsed. */
    private long timeElapsed;

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
     * Gets the performance average.
     * 
     * @return the performance average
     */
    public double getPerformanceAverage() {
	return performanceAverage;
    }

    /**
     * Gets the performance average factor.
     * 
     * @return the performance average factor
     */
    public int getPerformanceAverageFactor() {
	return performanceAverageFactor;
    }

    /**
     * Sets the performance average.
     * 
     * @param performanceSum
     *            the new performance average
     */
    public void setPerformanceAverage(double performanceSum) {
	this.performanceAverage = performanceSum;
    }

    /**
     * Sets the performance average factor.
     * 
     * @param performanceAverageFactor
     *            the new performance average factor
     */
    public void setPerformanceAverageFactor(int performanceAverageFactor) {
	this.performanceAverageFactor = performanceAverageFactor;
    }

    /**
     * Start.
     */
    public void start() {
	this.start = System.currentTimeMillis();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("\natEpoch=");
	builder.append(atEpoch);
	builder.append(" \ncause=");
	builder.append(cause);
	builder.append(" \nperformanceAverage=");
	builder.append(performanceAverage);
	builder.append(" \ntimeElapsed=");
	builder.append(timeElapsed);
	return builder.toString();
    }

    /**
     * Update performance average.
     * 
     * @param performance
     *            the performance
     * @return the double
     */
    public double updatePerformanceAverage(double performance) {
	this.performanceAverageFactor++;
	performanceAverage = (performanceAverage + performance)
		/ performanceAverageFactor;
	return performanceAverage;
    }

}
