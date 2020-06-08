package io.memoria.jann.test.unit.utils;

import io.memoria.jann.utils.TrainUtils;
import org.jblas.DoubleMatrix;
import org.junit.After;
import org.junit.Test;

public class TrainUtilsTest {

  private DoubleMatrix batchInputs = null;

  private DoubleMatrix batchTargets = null;

  @After
  public void tearDown() throws Exception {
    System.out.println("-----------------------------------------------");
  }

  @Test
  public void testNormalEqInv() {
    TrainUtils.normalEqInv(batchInputs, batchTargets);
  }

  @Test
  public void testNormalEqPinv() {
    long t = System.currentTimeMillis();
    DoubleMatrix weight = TrainUtils.normalEqPinv(batchInputs, batchTargets);
    t = System.currentTimeMillis() - t;
    System.out.println(t);
    DoubleMatrix predict2 = DoubleMatrix.valueOf("1 1650 3");
    predict2.mmul(weight).print();
  }
}
