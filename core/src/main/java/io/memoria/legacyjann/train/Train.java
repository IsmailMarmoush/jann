package io.memoria.legacyjann.train;

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

  public long getTimeLimit() {
    return timeLimit;
  }

  public void setTimeLimit(long timeLimit) {
    this.timeLimit = timeLimit;
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

  public void start() {
    this.start = System.currentTimeMillis();
  }

  public int getMaxEpochs() {
    return maxEpochs;
  }

  public void setMaxEpochs(int maxEpochs) {
    this.maxEpochs = maxEpochs;
  }

  public abstract double train();

  public void addPerformanceHistoryEntry(double performance) {
    getPerformanceHistory().add(performance);
  }

  public double getPerformanceGoal() {
    return performanceGoal;
  }

  public void end(String cause, int atEpoch) {
    this.end = System.currentTimeMillis();
    this.timeElapsed = end - start;
    this.cause = cause;
    this.atEpoch = atEpoch;
  }

  public List<Double> getPerformanceHistory() {
    return performanceHistory;
  }

  public void setPerformanceHistory(List<Double> performanceHistory) {
    this.performanceHistory = performanceHistory;
  }

  public void setPerformanceGoal(double performanceGoal) {
    this.performanceGoal = performanceGoal;
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

}
