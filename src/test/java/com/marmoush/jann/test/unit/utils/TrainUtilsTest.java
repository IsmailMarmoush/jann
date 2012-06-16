package com.marmoush.jann.test.unit.utils;

import static org.junit.Assert.*;

import org.jblas.DoubleMatrix;
import org.jblas.MatrixFunctions;
import org.jblas.Solve;
import org.junit.Before;
import org.junit.Test;

import com.marmoush.jann.sv.SvLayer;
import com.marmoush.jann.utils.MatrixUtils;
import com.marmoush.jann.utils.TrainUtils;

public class TrainUtilsTest {
    private DoubleMatrix batchInputs = null;
    private DoubleMatrix batchTargets = null;
    private SvLayer layer = null;

    @Before
    public void setUp() throws Exception {
	//
	batchInputs = DoubleMatrix.valueOf("8 1 6; 3 5 7; 4 9 2");
	// System.out.println(batchInputs);
	batchTargets = DoubleMatrix.valueOf("1;1; 0");
	layer = new SvLayer(batchInputs.columns, 1);
    }

    @Test
    public void testNormalEq() {
	DoubleMatrix weight = TrainUtils.NormalEq(batchInputs, batchTargets);
	DoubleMatrix output = batchInputs.mmul(weight);
	output = MatrixFunctions.ceil(output);
	
	System.out.println("Output:" + output);
	System.out.println("Target:" + batchTargets);
	System.out.println(output.eq(batchTargets));
	System.out.println(MatrixUtils.equals(output, batchTargets));
    }

    @Test
    public void testMseNgDervDW() {
	fail("Not yet implemented");
    }

    @Test
    public void testMseNgDervDB() {
	fail("Not yet implemented");
    }

    @Test
    public void testStochasticLinRgrGd() {
	fail("Not yet implemented");
    }

    @Test
    public void testBatchLinRgrGd() {

	// TrainUtils.batchLinRgrGd(layer, inputList, targetList)
    }
}
