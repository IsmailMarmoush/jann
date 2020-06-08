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
