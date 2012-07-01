package com.marmoush.jann.test.unit.train;

import java.io.File;
import java.util.List;

import org.jblas.DoubleMatrix;
import org.jfree.data.xy.XYSeries;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.marmoush.jann.chart.ChartUtils;
import com.marmoush.jann.chart.LinRgrChartUtils;
import com.marmoush.jann.model.regression.LinearRegression;
import com.marmoush.jann.model.regression.LogisticRegression;
import com.marmoush.jann.test.TestingData;
import com.marmoush.jann.train.Train;
import com.marmoush.jann.utils.MatrixUtils;
import com.marmoush.jann.utils.TrainUtils;

public class LogRgrTrainTest {
    private DoubleMatrix batchTargets = null;
    private DoubleMatrix batchTrainingEx = null;
    private List<DoubleMatrix> inputList = null;
    private List<DoubleMatrix> targetList = null;

    @Before
    public void setUp() throws Exception {
	String path = TestingData.getPath("ex2", "ex2data1.txt");
	DoubleMatrix data = DoubleMatrix.loadAsciiFile(path);
	batchTrainingEx = data.getColumns(new int[] { 0, 1 });
	batchTargets = data.getColumn(2);
//	inputList = MatrixUtils.batchMtrxToColVecsList(batchTrainingEx
//		.transpose());
//	targetList = MatrixUtils.batchMtrxToColVecsList(batchTargets
//		.transpose());
    }

    @After
    public void tearDown() throws Exception {

	System.out.println("-----------------------------------------------");
    }

    @Test
    public void createImage() {
	List<Double> range = MatrixUtils.range(0, 1, 1500);
	// List<Double> stochErr = trainStochasticLinRgr();
	List<Double> batchErr = trainBatchLogRgr();
	// XYSeries xyStoch = ChartUtils.xySeries("Stochastic", range,
	// stochErr);
	XYSeries xyBatch = ChartUtils.xySeries("Batch", range, batchErr);
	String path = "ChartsOutput" + File.separator + "chart2.png";
	LinRgrChartUtils.createLinRgrPNG(path,
		"Linear Regression Batch Vs Stochastic", xyBatch);
    }

    @Test
    public void testBatchLinRgr() {
	trainBatchLogRgr();
    }

//    @Test
//    public void testStochasticLinRgr() {
//	// trainStochasticLinRgr();
//    }

    public List<Double> trainBatchLogRgr() {
	System.out.println("Batch Training");
	final LogisticRegression blr = new LogisticRegression(batchTrainingEx,
		batchTargets, true);
	Train batchTrain = new Train(1500, 0.001, 1000) {
	    @Override
	    public double train() {
		TrainUtils.batchLogRgr(blr, batchTrainingEx, batchTargets);
//		return blr.getPerformance();
		return 1;
	    }
	};
	batchTrain.run();
	System.out.println(batchTrain);
	return batchTrain.getPerformanceHistory();
    }

    public List<Double> trainStochasticLinRgr() {
	System.out.println("Stochastic Trainging ");
	final LinearRegression slr = new LinearRegression(inputList,
		targetList, true);
	Train stochasticTraining = new Train(1500, 0.001, 1000) {
	    @Override
	    public double train() {
		TrainUtils.stochasticLinRgr(slr, inputList, targetList);
		return slr.getPerformance();
	    }
	};
	stochasticTraining.run();
	System.out.println(stochasticTraining);
	return stochasticTraining.getPerformanceHistory();
    }
}
