package io.memoria.jann;

import io.memoria.jann.MatrixUtils;
import io.memoria.legacyjann.sv.DefaultSvLayer;
import org.jblas.DoubleMatrix;

import java.util.List;

public class TrainUtils {

  public static void batchGd(DefaultSvLayer lyr, DoubleMatrix inputs, DoubleMatrix targets) {
    lyr.setInput(inputs);
    lyr.setTarget(targets);
    lyr.simulate();
    double m = targets.length;
    double alpha = lyr.getLearnRate();
    double lambda = lyr.getReguFctr();
    DoubleMatrix error = lyr.getOutput().sub(targets);
    DoubleMatrix grad = inputs.transpose().mmul(error).divi(m);
    double regu = 1 - ((alpha * lambda) / m);
    lyr.getWeight().muli(regu).subi(grad.mul(alpha));
    if (lyr.isBiased()) {
      lyr.getBias().subi((alpha / m) * error.sum());
    }
  }

  public static DoubleMatrix normalEqInv(DoubleMatrix x, DoubleMatrix targets) {
    // ((X'*X)^-1) * (X' * y)
    DoubleMatrix inverse = MatrixUtils.inv(x.transpose().mmul(x));
    DoubleMatrix xTransposeY = x.transpose().mmul(targets);
    return inverse.mmul(xTransposeY);
  }

  public static DoubleMatrix normalEqPinv(DoubleMatrix x, DoubleMatrix targets) {
    DoubleMatrix inverse = MatrixUtils.pinv(x.transpose().mmul(x));
    DoubleMatrix xTransposeY = x.transpose().mmul(targets);
    return inverse.mmul(xTransposeY);
  }

  public static DoubleMatrix normalEqPinvRgu(DoubleMatrix x, DoubleMatrix targets, double rguFctr, boolean biased) {
    DoubleMatrix inv = x.transpose().mmul(x);
    DoubleMatrix eye = DoubleMatrix.eye(x.columns).mul(rguFctr);
    if (biased)
      eye.put(0, 0);
    DoubleMatrix inverse = MatrixUtils.pinv(inv.addi(eye));
    DoubleMatrix xTransposeY = x.transpose().mmul(targets);
    return inverse.mmul(xTransposeY);
  }

  public static void stochasticGd(DefaultSvLayer lyr, List<DoubleMatrix> inputs, List<DoubleMatrix> targets) {
    int m = 0;
    double alpha = lyr.getLearnRate();
    double performanceSum = 0;
    for (int i = 0; i < inputs.size(); i++) {
      m = targets.get(i).length;
      lyr.setInput(inputs.get(i));
      lyr.setTarget(targets.get(i));
      lyr.simulate();
      performanceSum += lyr.getPerformance();
      DoubleMatrix error = lyr.getOutput().sub(lyr.getTarget());
      DoubleMatrix sum = error.transpose().mmul(lyr.getInput());
      DoubleMatrix deltaWeight = sum.mul(alpha / m);
      lyr.getWeight().subi(deltaWeight);
      if (lyr.isBiased()) {
        lyr.getBias().subi((alpha / m) * error.sum());
      }
      lyr.setPerformance(performanceSum / inputs.size());
    }
  }
}
