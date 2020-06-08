/*
 * Copyright 2011 Ismail Marmoush This file is part of JANN. JANN is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * General Public License Version 3 as published by the Free Software
 * Foundation, either version 3 of the License, or any later version. JANN is
 * distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details. You
 * should have received a copy of the GNU General Public License along with
 * JANN. If not, see http://www.gnu.org/licenses/. For More Information Please
 * Visit http://jann.marmoush.com
 */
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
