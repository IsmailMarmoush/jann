package com.marmoush.jann.train;

import java.util.List;

import org.jblas.DoubleMatrix;

import com.marmoush.jann.sv.SvLayer;
import com.marmoush.jann.utils.PerformanceUtils;
import com.marmoush.jann.utils.TrainUtils;
import com.marmoush.jann.utils.TrainUtils2;

public class TrainLinRgr extends TrainSv {

    private static final long serialVersionUID = 1124041014599671565L;

    public TrainLinRgr() {
	super();
    }

    public TrainLinRgr(double performanceGoal, long timeLimit, int maxEpochs) {
	super(performanceGoal, timeLimit, maxEpochs);
    }

    public TrainResult batchToLimits(SvLayer layer,
	    List<DoubleMatrix> inputList, List<DoubleMatrix> targetList) {
	TrainResult result = new TrainResult();
	result.start();
	for (int i = 0; i < getEpochs(); i++) {
	    double performance = TrainUtils.batchLinRgrGd(layer, inputList,
		    targetList);
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

    public TrainResult batchNg(DoubleMatrix batchInputs,
	    DoubleMatrix batchTargets, DoubleMatrix initWeight, double lrnRate) {
	TrainResult result = new TrainResult();
	result.start();
	for (int i = 0; i < getEpochs(); i++) {
	    DoubleMatrix newWeight = TrainUtils2.batchLinRgrGd(batchInputs,
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
	initWeight.print();
	result.end(TrainResult.EPOCHS_REACHED, getEpochs());
	return result;
    }
}
