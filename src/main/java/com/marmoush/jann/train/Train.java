/*
 * Copyright 2011 Ismail Marmoush This file is part of JANN. JANN is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * General Public License Version 3 as published by the Free Software
 * Foundation, either version 3 of the License, or any later version. JANN is
 * distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details. You
 * should have received a copy of the GNU General Public License along with
 * JANN. If not, see http://www.gnu.org/licenses/. For More Information Please
 * Visit http://jann.marmoush.com
 */
package com.marmoush.jann.train;

import java.util.ArrayList;
import java.util.List;

public abstract class Train {

    public static final String EPOCHS_REACHED = "EpochsReached";

    public static final String PERFORMANCE_REACHED = "PerformanceGoalMet";

    public static final String TIME_LIM_REACHED = "MaxTimeExceeded";

    private int atEpoch;

    private String cause;

    private long end;

    private int maxEpochs;

    private double performanceGoal;

    private List<Double> performanceHistory = new ArrayList<Double>();

    private long start;

    private long timeElapsed;

    private long timeLimit;

    public Train() {
    }

    public Train(int maxEpochs, double performanceGoal, long timeLimit) {
	super();
	this.maxEpochs = maxEpochs;
	this.performanceGoal = performanceGoal;
	this.timeLimit = timeLimit;
    }

    public void addPerformanceHistoryEntry(double performance) {
	getPerformanceHistory().add(performance);
    }

    public void end(String cause, int atEpoch) {
	this.end = System.currentTimeMillis();
	this.timeElapsed = end - start;
	this.cause = cause;
	this.atEpoch = atEpoch;
    }

    public int getMaxEpochs() {
	return maxEpochs;
    }

    public double getPerformanceGoal() {
	return performanceGoal;
    }

    public List<Double> getPerformanceHistory() {
	return performanceHistory;
    }

    public long getTimeLimit() {
	return timeLimit;
    }

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

    public void setMaxEpochs(int maxEpochs) {
	this.maxEpochs = maxEpochs;
    }

    public void setPerformanceGoal(double performanceGoal) {
	this.performanceGoal = performanceGoal;
    }

    public void setPerformanceHistory(List<Double> performanceHistory) {
	this.performanceHistory = performanceHistory;
    }

    public void setTimeLimit(long timeLimit) {
	this.timeLimit = timeLimit;
    }

    public void start() {
	this.start = System.currentTimeMillis();
    }

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

    public abstract double train();

}
