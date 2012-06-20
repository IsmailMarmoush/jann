package com.marmoush.jann.test.unit.train;

import java.util.List;

import org.jblas.DoubleMatrix;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.marmoush.jann.train.TrainLinRgr;
import com.marmoush.jann.train.TrainResult;

public class TrainLinRgrTest {
    private TrainLinRgr train = null;
    private DoubleMatrix batchInputs = null;
    private DoubleMatrix batchTargets = null;
    List<DoubleMatrix> inputList = null;
    List<DoubleMatrix> targetList = null;

    @Before
    public void setUp() throws Exception {
	DoubleMatrix data = DoubleMatrix
		.loadAsciiFile("src\\test\\java\\ex1data1.txt");
	batchInputs = data.getColumn(0);
	batchTargets = data.getColumn(1);
	train = new TrainLinRgr(0.001, 1000, 1500);
    }

    @After
    public void tearDown() throws Exception {
	System.out.println("-----------------------------------------------");
    }

    @Test
    public void testBatchNg() {
	DoubleMatrix batchInputsWithBias = DoubleMatrix.concatHorizontally(
		DoubleMatrix.ones(batchInputs.rows, 1), batchInputs);
	TrainResult tr = train.batchLinRgr(batchInputsWithBias, batchTargets,
		DoubleMatrix.rand(batchInputsWithBias.columns, 1), 0.01);
	System.out.println(tr.toString());
    }
}
