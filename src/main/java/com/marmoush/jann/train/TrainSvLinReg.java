package com.marmoush.jann.train;

public class TrainSvLinReg extends TrainSv {

    private static final long serialVersionUID = 7212662555955933040L;

    public TrainSvLinReg() {
    }

    public TrainSvLinReg(double performanceGoal, long timeLimit, int maxEpochs) {
	super(performanceGoal, timeLimit, maxEpochs);
    }

}
