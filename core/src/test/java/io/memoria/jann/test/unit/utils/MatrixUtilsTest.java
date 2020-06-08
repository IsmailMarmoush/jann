package io.memoria.jann.test.unit.utils;

import io.memoria.jann.utils.MatrixUtils;
import org.jblas.DoubleMatrix;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MatrixUtilsTest {

  private DoubleMatrix mtrx;

  @Test
  public void testBatchMtrx2colVecsList() {
    DoubleMatrix batchInputs = DoubleMatrix.valueOf("8 1 6; 3 5 7 ; 4 9 2; 3 2 2");
    List<DoubleMatrix> list = MatrixUtils.batchMtrxToColVecsList(batchInputs);
    MatrixUtils.print(true, list.toArray(new DoubleMatrix[0]));
  }

  @Test
  public void testFeatureMapping() {
    System.out.println(MatrixUtils.getNumFeaturesMapped(9));
    MatrixUtils.featureMapping(2, 2, 9).print();

  }

  @Test
  public void testFeatureMappingInput() {
    DoubleMatrix input = MatrixUtils.randomMatrixFloor(3, 2, 1, 3);
    input.print();
    MatrixUtils.batchFeatureMapping(input, 2, 0, 1).print();
  }

  @Test
  public void testFeatureScaling() {
    DoubleMatrix m = DoubleMatrix.valueOf("2 4 4 4 5 5 7 9");
    System.out.println(m);
    System.out.println(MatrixUtils.featureScalingByAvrg(m));
  }

  @Test
  public void testRound() {
    MatrixUtils.round(mtrx, 2);
    DoubleMatrix testResult = DoubleMatrix.valueOf("1.23 12.35 123.46 1234.57 12345.68");
    Assertions.assertEquals(mtrx, testResult);
  }

  @Test
  public void testStandardDeviation() {
    DoubleMatrix m = DoubleMatrix.valueOf("3 4 5");
    System.out.println(m);
    System.out.println(MatrixUtils.standardDeviation(m));
    // assertTrue(MatrixUtils.standardDeviation(m) == 2);
  }
}
