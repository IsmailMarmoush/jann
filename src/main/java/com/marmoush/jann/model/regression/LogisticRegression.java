package com.marmoush.jann.model.regression;

import java.util.List;

import org.jblas.DoubleMatrix;

import com.marmoush.jann.sv.SvLayer;
import com.marmoush.jann.utils.functors.IPerformance;
import com.marmoush.jann.utils.functors.ITransfere;
import com.marmoush.jann.utils.functors.IWeight;

public class LogisticRegression extends SvLayer {
    private static final long serialVersionUID = 6248944247719115094L;

    public LogisticRegression(DoubleMatrix batchTrainingEx,
	    DoubleMatrix batchTargets, boolean biased) {
	super(batchTrainingEx.columns, 1, biased);
	setWeightFnctr(IWeight.BATCH_DOTPROD);
	setTransfereFnctr(ITransfere.LOGSIG);
	setPerformancefnctr(IPerformance.LOGRGR);
	setLearnRate(0.01);
	setFill(1, getWeight());
	if (biased)
	    setFill(1, getBias());
    }

    public LogisticRegression(List<DoubleMatrix> trainingEx,
	    List<DoubleMatrix> targetList, boolean biased) {
	super(trainingEx.get(0).rows, targetList.get(0).length, biased);
	setWeightFnctr(IWeight.DOTPROD);
	setTransfereFnctr(ITransfere.LOGSIG);
	setPerformancefnctr(IPerformance.LOGRGR);
	setLearnRate(0.01);
	setFill(1, getWeight());
	if (biased)
	    setFill(1, getBias());
    }
}
