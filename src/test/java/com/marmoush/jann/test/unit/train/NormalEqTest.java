/*
 * Copyright 2012 Ismail Marmoush This file is part of JANN. JANN is free
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
package com.marmoush.jann.test.unit.train;

import org.jblas.DoubleMatrix;
import org.junit.Before;
import org.junit.Test;

import com.marmoush.jann.test.TestingData;
import com.marmoush.jann.utils.TrainUtils;

//
/**
 * 
 */
public class NormalEqTest {

    /**
     * 
     */
    DoubleMatrix inputs;

    /**
     * 
     */
    DoubleMatrix targets;

    /**
     * 
     */
    @Test
    public void normalEqPinvRgu() {
	TrainUtils.normalEqPinv(inputs, targets).print();
	TrainUtils.normalEqPinvRgu(inputs, targets, 10, true).print();

    }

    /**
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
	String path = TestingData.getPath("ex2", "ex2data1Bias.txt");
	DoubleMatrix data = DoubleMatrix.loadAsciiFile(path);
	inputs = data.getColumns(new int[] { 0, 1, 2 });
	targets = data.getColumn(3);

    }
}
