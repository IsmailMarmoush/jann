package io.memoria.jann.test.unit.utils;

import static org.junit.Assert.assertTrue;

import io.memoria.jann.utils.PerformanceUtils;
import io.memoria.jann.utils.TransfereUtils;
import org.jblas.DoubleMatrix;
import org.junit.Before;
import org.junit.Test;

public class PerformanceUtilsTest {

  private DoubleMatrix output;

  private DoubleMatrix target;

  @Before
  public void setUp() throws Exception {
    output = DoubleMatrix.valueOf("1; 2; 3");
    target = DoubleMatrix.valueOf("2; 4 ;6");
  }

  @Test
  public void testLogRgr() {
    DoubleMatrix transf = TransfereUtils.logsig(output);
    double p = PerformanceUtils.logRgr(transf, target);
    System.out.println(p);

  }

  @Test
  public void testMae() {
    double p = PerformanceUtils.mae(output, target);
    assertTrue("Performance is:" + p, p == 2);
  }

  @Test
  public void testMse() {
    double p = PerformanceUtils.mse(output, target);
    p = Math.round(p * 10000.0) / 10000.0;
    assertTrue("Performance is:" + p, p == 4.6667);
  }

  @Test
  public void testSse() {
    double p = PerformanceUtils.sse(output, target);
    assertTrue("Performance is:" + p, p == 14);
  }

}
