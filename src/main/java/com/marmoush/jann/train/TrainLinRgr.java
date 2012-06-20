package com.marmoush.jann.train;

import org.jblas.DoubleMatrix;

import com.marmoush.jann.sv.SvLayer;
import com.marmoush.jann.utils.PerformanceUtils;
import com.marmoush.jann.utils.TrainUtils;

public class TrainLinRgr extends TrainSv {

    private static final long serialVersionUID = 1124041014599671565L;

    public TrainLinRgr() {
	super();
    }

    public TrainLinRgr(double performanceGoal, long timeLimit, int maxEpochs) {
	super(performanceGoal, timeLimit, maxEpochs);
    }

    public TrainResult batchLinRgr(DoubleMatrix batchInputs,
	    DoubleMatrix batchTargets, DoubleMatrix initWeight, double lrnRate) {
	TrainResult result = new TrainResult();
	result.start();
	for (int i = 0; i < getEpochs(); i++) {
	    DoubleMatrix newWeight = TrainUtils.batchLinRgrGd(batchInputs,
		    batchTargets, initWeight, lrnRate);
	    // cost = computeCost(X, y, theta);
	    double performance = PerformanceUtils.linRgrCost(batchInputs,
		    batchTargets, newWeight);
	    initWeight = newWeight;
	    result.addPerformanceHistoryEntry(performance);
	    result.updatePerformanceAverage(performance);
	    if (result.getPerformanceAverage() < getPerformanceGoal()) {
		result.end(TrainResult.PERFORMANCE_REACHED, i);
		return result;
	    }
	}
	result.end(TrainResult.EPOCHS_REACHED, getEpochs());
	return result;
    }

    public TrainResult batchLinRgrLayer(SvLayer layer,
	    DoubleMatrix batchTrainingEx, DoubleMatrix batchTargets) {
	TrainResult result = new TrainResult();
	result.start();
	for (int i = 0; i < getEpochs(); i++) {
	    TrainUtils.batchLinRgrLayer(layer, batchTrainingEx, batchTargets);
	    double performance=layer.getPerformance();
	    // TODO compare linRgrCost with layer.getPerformance
	    // cost = computeCost(X, y, theta);
	    double cost = PerformanceUtils.linRgrCost(batchTrainingEx,
		    batchTargets, layer.getWeight());
	    result.addPerformanceHistoryEntry(cost);
	    result.updatePerformanceAverage(cost);
	    if (result.getPerformanceAverage() < getPerformanceGoal()) {
		result.end(TrainResult.PERFORMANCE_REACHED, i);
		return result;
	    }
	}
	result.end(TrainResult.EPOCHS_REACHED, getEpochs());
	return result;
    }
}
