package io.memoria.legacyjann.utils;

import org.jblas.DoubleMatrix;
import org.jblas.MatrixFunctions;

public class PerformanceUtils {

  public static double linRgrRgu(DoubleMatrix output, DoubleMatrix target, DoubleMatrix weight, double reguFctr) {
    int m = target.length;
    double w2 = MatrixFunctions.pow(weight, 2).sum();
    double reg = (reguFctr / (2.0 * m)) * w2;
    return linRgr(output, target) + reg;
  }

  public static double linRgr(DoubleMatrix output, DoubleMatrix target) {
    double m = target.length;
    DoubleMatrix error = output.sub(target);
    return MatrixFunctions.pow(error, 2).sum() / (2.0 * m);
  }

  public static double logRgrRgu(DoubleMatrix output, DoubleMatrix target, DoubleMatrix weight, double reguFctr) {
    int m = target.length;
    double w2 = MatrixFunctions.pow(weight, 2).sum();
    double reg = (reguFctr / (2.0 * m)) * w2;
    return logRgr(output, target) + reg;
  }

  public static double logRgr(DoubleMatrix output, DoubleMatrix target) {
    // J = -1./m * ( y' * log( sigmoid(X * theta) ) + ( 1 - y' ) * log ( 1 -
    // sigmoid( X * theta)) )
    int m = target.length;
    // y' * log (sigmoid(X* theta))
    DoubleMatrix yT = target.transpose();
    DoubleMatrix outputLog = MatrixFunctions.log(output);
    DoubleMatrix part1 = yT.mmul(outputLog);
    // ( 1 - y' ) * log ( 1 - sigmoid( X * theta)) )
    DoubleMatrix logOneMinusOutput = MatrixFunctions.log(output.neg().add(1));
    DoubleMatrix oneMinusYT = yT.neg().add(1);
    DoubleMatrix part2 = oneMinusYT.mmul(logOneMinusOutput);
    return part1.add(part2).sum() * (-1.0 / m);
  }

  public static double mae(DoubleMatrix output, DoubleMatrix target) {
    int m = target.length;
    DoubleMatrix error = output.sub(target);
    return MatrixFunctions.abs(error).sum() / m;
  }

  public static double mse(DoubleMatrix output, DoubleMatrix target) {
    // As long as error.length is actually the length of the error matrix
    // unrolled so even if the matrix was 2*3 the length should be 6
    int m = target.length;
    DoubleMatrix error = output.sub(target);
    return MatrixFunctions.pow(error, 2).sum() / m;
  }

  public static double sse(DoubleMatrix output, DoubleMatrix target) {
    DoubleMatrix error = output.sub(target);
    return MatrixFunctions.pow(error, 2).sum();
  }
}
