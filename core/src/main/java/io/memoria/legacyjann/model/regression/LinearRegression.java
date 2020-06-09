package io.memoria.legacyjann.model.regression;

import io.memoria.legacyjann.sv.DefaultSvLayer;
import io.memoria.legacyjann.utils.functors.IPerformance;
import io.memoria.legacyjann.utils.functors.ITransfere;
import io.memoria.legacyjann.utils.functors.IWeight;
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
