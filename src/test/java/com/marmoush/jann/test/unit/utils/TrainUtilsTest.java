package com.marmoush.jann.test.unit.utils;

import org.jblas.DoubleMatrix;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.marmoush.jann.utils.TrainUtils;

public class TrainUtilsTest {
    private DoubleMatrix batchInputs = null;
    private DoubleMatrix batchTargets = null;

    @Before
    public void setUp() throws Exception {
	batchInputs = DoubleMatrix.valueOf("8 1 6 ; 3 5 7 ; 4 9 2 ; 3 2 1");
	batchTargets = DoubleMatrix.valueOf("1;1; 0;0");
	DoubleMatrix data = DoubleMatrix
		.loadAsciiFile("src\\test\\java\\ex1data2.txt");
	batchInputs = data.getColumns(new int[]{0,1});
	batchInputs = DoubleMatrix.concatHorizontally(
		DoubleMatrix.ones(batchInputs.rows), batchInputs);
	batchTargets = data.getColumn(2);
    }

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
	DoubleMatrix weight = TrainUtils
		.normalEqPinv(batchInputs, batchTargets);
	t = System.currentTimeMillis() - t;
	System.out.println(t);
	DoubleMatrix predict2=DoubleMatrix.valueOf("1 1650 3");
	predict2.mmul(weight).print();
    }
}
