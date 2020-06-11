package io.memoria.jann;

import org.jblas.DoubleMatrix;
import org.jblas.MatrixFunctions;

public abstract class TransfereUtils {

  public static DoubleMatrix compet(final DoubleMatrix netSum) {
    int maxIndex = netSum.argmax();
    DoubleMatrix result = DoubleMatrix.zeros(netSum.length);
    result.put(maxIndex, 1);
    return result;
  }

  public static DoubleMatrix hardlim(final DoubleMatrix netSum, final double theta) {
    return netSum.gt(theta);
  }

  public static DoubleMatrix hardlims(final DoubleMatrix netSum, final double theta) {
    DoubleMatrix result = new DoubleMatrix(netSum.length);
    for (int i = 0; i < netSum.length; i++) {
      result.put(i, netSum.get(i) > theta ? 1 : -1);
    }
    return result;
  }

  public static DoubleMatrix logsigDiff(final DoubleMatrix netSum) {
    // =g(z)(1-g(z))
    // =g(z)-g(z)^2
    // =netSum-netSum^2
    DoubleMatrix netSumLogSig = logsig(netSum);
    return netSumLogSig.sub(MatrixFunctions.pow(netSumLogSig, 2));
  }

  public static DoubleMatrix logsig(final DoubleMatrix netSum) {
    return MatrixFunctions.pow((MatrixFunctions.exp(netSum.neg()).add(1)), -1);
  }

  public static DoubleMatrix poslin(final DoubleMatrix netSum, final double theta) {
    DoubleMatrix result = new DoubleMatrix(netSum.length);
    for (int i = 0; i < netSum.length; i++) {
      if (netSum.get(i) < theta)
        result.put(i, 0);
    }
    return result;
  }

  public static DoubleMatrix purelin(final DoubleMatrix netSum) {
    return netSum;
  }

  public static DoubleMatrix satlin(final DoubleMatrix netSum) {
    DoubleMatrix result = new DoubleMatrix(netSum.length);
    for (int i = 0; i < netSum.length; i++) {
      if (netSum.get(i) < 0)
        result.put(i, 0);
      else
        if (netSum.get(i) > 1)
          result.put(i, 1);
    }
    return result;
  }

  public static DoubleMatrix satlins(final DoubleMatrix netSum) {
    DoubleMatrix result = new DoubleMatrix(netSum.length);
    for (int i = 0; i < netSum.length; i++) {
      if (netSum.get(i) < 0)
        result.put(i, -1);
      else
        if (netSum.get(i) > 1)
          result.put(i, 1);
    }
    return result;
  }

  public static DoubleMatrix tansigDiff(final DoubleMatrix netSum) {
    // (1-(f(netSum)^2))
    // ((f(netSum)^2)*-1)+1
    return MatrixFunctions.pow(tansig(netSum), 2).neg().add(1);
  }

  public static DoubleMatrix tansig(final DoubleMatrix netSum) {
    return MatrixFunctions.tanh(netSum);
  }
}
