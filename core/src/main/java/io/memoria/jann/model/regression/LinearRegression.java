package io.memoria.jann.model.regression;

import io.memoria.jann.sv.DefaultSvLayer;
import io.memoria.jann.utils.functors.IPerformance;
import io.memoria.jann.utils.functors.ITransfere;
import io.memoria.jann.utils.functors.IWeight;
import org.jblas.DoubleMatrix;

import java.util.List;

public class LinearRegression extends DefaultSvLayer {

  private static final long serialVersionUID = -8858564328695652268L;

  public LinearRegression(DoubleMatrix batchTrainingEx, DoubleMatrix batchTargets, boolean biased) {
    super(batchTrainingEx.columns, 1, biased);
    setWeightFnctr(IWeight.BATCH_DOTPROD);
    setTransfereFnctr(ITransfere.PURELIN);
    setPerformancefnctr(IPerformance.LINRGR);
  }

  public LinearRegression(List<DoubleMatrix> trainingEx, List<DoubleMatrix> targetList, boolean biased) {
    super(trainingEx.get(0).rows, targetList.get(0).length, biased);
    setWeightFnctr(IWeight.DOTPROD);
    setTransfereFnctr(ITransfere.PURELIN);
    setPerformancefnctr(IPerformance.LINRGR);
  }
}
