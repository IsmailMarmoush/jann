package io.memoria.legacyjann.model.regression;

import io.memoria.legacyjann.sv.DefaultSvLayer;
import io.memoria.legacyjann.utils.functors.IPerformance;
import io.memoria.legacyjann.utils.functors.ITransfere;
import io.memoria.legacyjann.utils.functors.IWeight;
import org.jblas.DoubleMatrix;

import java.util.List;

public class LogisticRegression extends DefaultSvLayer {

  private static final long serialVersionUID = 6248944247719115094L;

  public LogisticRegression(DoubleMatrix batchTrainingEx, DoubleMatrix batchTargets, boolean biased) {
    super(batchTrainingEx.columns, 1, biased);
    setWeightFnctr(IWeight.BATCH_DOTPROD);
    setTransfereFnctr(ITransfere.LOGSIG);
    setPerformancefnctr(IPerformance.LOGRGR);
  }

  public LogisticRegression(List<DoubleMatrix> trainingEx, List<DoubleMatrix> targetList, boolean biased) {
    super(trainingEx.get(0).rows, targetList.get(0).length, biased);
    setWeightFnctr(IWeight.DOTPROD);
    setTransfereFnctr(ITransfere.LOGSIG);
    setPerformancefnctr(IPerformance.LOGRGR);
  }
}
