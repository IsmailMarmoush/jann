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

    public TrainResult batchLinRgr(DoubleMatrix batchTrainingEx,
	    DoubleMatrix batchTargets, DoubleMatrix initWeight, double lrnRate) {
	TrainResult result = new TrainResult();
	result.start();
	DoubleMatrix newWeight = null;
	for (int i = 0; i < getEpochs(); i++) {
	    newWeight = TrainUtils.batchLinRgrGd(batchTrainingEx, batchTargets,
		    initWeight, lrnRate);
	    // cost = computeCost(X, y, theta);
	    initWeight = newWeight;
	    double performance = PerformanceUtils.linRgrCost(batchTrainingEx,
		    batchTargets, newWeight);
	    result.addPerformanceHistoryEntry(performance);
	    if (performance < getPerformanceGoal()) {
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
	    double performance = layer.getPerformance();
	    result.addPerformanceHistoryEntry(performance);
	    if (performance < getPerformanceGoal()) {
		result.end(TrainResult.PERFORMANCE_REACHED, i);
		return result;
	    }
	}
	result.end(TrainResult.EPOCHS_REACHED, getEpochs());
	return result;
    }
}
