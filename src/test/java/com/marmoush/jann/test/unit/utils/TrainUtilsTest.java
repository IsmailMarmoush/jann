package com.marmoush.jann.test.unit.utils;

import static org.junit.Assert.fail;

import java.util.List;

import org.jblas.DoubleMatrix;
import org.junit.After;
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
	batchInputs = DoubleMatrix.valueOf("8 1 6 ; 3 5 7 ; 4 9 2 ; 3 2 1");
	batchTargets = DoubleMatrix.valueOf("1;1; 0;0");

    }

    @After
    public void tearDown() throws Exception {
	System.out.println("-----------------------------------------------");
    }

    @Test
    public void testNormalEqInv() {
	// DoubleMatrix weight = TrainUtils.normalEqInv(batchInputs,
	// batchTargets);
	// DoubleMatrix output = batchInputs.mmul(weight);
	// output = MatrixUtils.round(output, 0);
	// // System.out.println(weight);
	// System.out.println("Output:" + output);
	// System.out.println("Target:" + batchTargets);
	// System.out.println(output.eq(batchTargets));
	// System.out.println(MatrixUtils.equals(output, batchTargets));
    }

    @Test
    public void testNormalEqPinv() {
	DoubleMatrix weight = TrainUtils
		.normalEqPinv(batchInputs, batchTargets);
	DoubleMatrix output = batchInputs.mmul(weight);

	output = MatrixUtils.round(output, 0);
	// System.out.println(weight);
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
	List<DoubleMatrix> inputList = MatrixUtils.mtrx2colVecsList(batchInputs
		.transpose());
	List<DoubleMatrix> targetList = MatrixUtils
		.mtrx2colVecsList(batchTargets.transpose());

	// MatrixUtils.printSize(batchInputs,batchTargets);
	 TrainUtils.batchLinRgrGd(layer, inputList, targetList);
	System.out.println(layer);
    }

    @Test
    public void testBatchLinRgrGdMulti() {
	layer = new SvLayer(batchInputs.columns, 2);
	batchInputs = DoubleMatrix.valueOf("8 1 6 ; 3 5 7 ; 4 9 2 ; 3 2 1");
	batchTargets = DoubleMatrix.valueOf("1 1; 1 0; 0 0; 0 0");
	List<DoubleMatrix> inputList = MatrixUtils.mtrx2colVecsList(batchInputs
		.transpose());
	List<DoubleMatrix> targetList = MatrixUtils
		.mtrx2colVecsList(batchTargets.transpose());

	// MatrixUtils.printSize(batchInputs,batchTargets);
	TrainUtils.batchLinRgrGd(layer, inputList, targetList);
	System.out.println(layer);
    }
}
