package io.memoria.jann;

import io.memoria.jann.PerformanceUtils;
import io.memoria.jann.TransfereUtils;
import org.jblas.DoubleMatrix;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PerformanceUtilsTest {

  private DoubleMatrix output;

  private DoubleMatrix target;

  @BeforeEach
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
    Assertions.assertEquals(p, 2);
  }

  @Test
  public void testMse() {
    double p = PerformanceUtils.mse(output, target);
    p = Math.round(p * 10000.0) / 10000.0;
    Assertions.assertEquals(p, 4.6667);
  }

  @Test
  public void testSse() {
    double p = PerformanceUtils.sse(output, target);
    Assertions.assertEquals(p, 14);
  }
}
