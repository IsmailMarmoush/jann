package com.marmoush.jann.model.regression;

import java.util.List;

import org.jblas.DoubleMatrix;

import com.marmoush.jann.sv.SvLayer;
import com.marmoush.jann.utils.functors.IPerformance;
import com.marmoush.jann.utils.functors.ITransfere;
import com.marmoush.jann.utils.functors.IWeight;

public class LinearRegression extends SvLayer {

    private static final long serialVersionUID = -8858564328695652268L;

    public LinearRegression(DoubleMatrix batchTrainingEx,
	    DoubleMatrix batchTargets, boolean biased) {
	super(batchTrainingEx.columns, 1, biased);
	setWeightFnctr(IWeight.BATCH_DOTPROD);
	setTransfereFnctr(ITransfere.PURELIN);
	setPerformancefnctr(IPerformance.LINRGR);
    }

    public LinearRegression(List<DoubleMatrix> trainingEx,
	    List<DoubleMatrix> targetList, boolean biased) {
	super(trainingEx.get(0).rows, targetList.get(0).length, biased);
	setWeightFnctr(IWeight.DOTPROD);
	setTransfereFnctr(ITransfere.PURELIN);
	setPerformancefnctr(IPerformance.LINRGR);
    }
}
