package com.marmoush.jann.test.unit.train;

import java.util.List;

import org.jblas.DoubleMatrix;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.marmoush.jann.model.regression.linear.LinearRegression;
import com.marmoush.jann.train.Train;
import com.marmoush.jann.utils.MatrixUtils;
import com.marmoush.jann.utils.TrainUtils;

public class TrainTest {
    private DoubleMatrix batchTargets = null;
    private DoubleMatrix batchTrainingEx = null;
    List<DoubleMatrix> inputList = null;
    List<DoubleMatrix> targetList = null;

    @Before
    public void setUp() throws Exception {
	DoubleMatrix data = DoubleMatrix
		.loadAsciiFile("src\\test\\java\\ex1data1.txt");
	batchTrainingEx = data.getColumn(0);
	batchTargets = data.getColumn(1);
	inputList = MatrixUtils.batchMtrxToColVecsList(batchTrainingEx
		.transpose());
	targetList = MatrixUtils.batchMtrxToColVecsList(batchTargets
		.transpose());
    }

    @After
    public void tearDown() throws Exception {

	System.out.println("-----------------------------------------------");
    }

    @Test
    public void testBatchLinRgr() {
	System.out.println("Batch Training");
	final LinearRegression lr = new LinearRegression(batchTrainingEx,
		batchTargets, true);
	Train t = new Train(1500, 0.001, 1000) {
	    @Override
	    public double train() {
		TrainUtils.batchLinRgr(lr, batchTrainingEx, batchTargets);
		return lr.getPerformance();
	    }
	};
	t.run();
	System.out.println(t);
    }

    @Test
    public void testStochasticLinRgr() {
	System.out.println("Stochastic Trainging ");
	final LinearRegression lr = new LinearRegression(inputList, targetList,
		true);
	Train t = new Train(1500, 0.001, 1000) {
	    @Override
	    public double train() {
		TrainUtils.stochasticLinRgr(lr, inputList, targetList);
		return lr.getPerformance();
	    }
	};
	t.run();
	System.out.println(t);
    }
}
