package com.marmoush.jann.test.unit.train;

import java.io.File;
import java.util.List;

import org.jblas.DoubleMatrix;
import org.jfree.data.xy.XYSeries;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.marmoush.jann.chart.ChartUtils;
import com.marmoush.jann.model.regression.LinearRegression;
import com.marmoush.jann.test.TestingData;
import com.marmoush.jann.train.Train;
import com.marmoush.jann.utils.MatrixUtils;
import com.marmoush.jann.utils.TrainUtils;
import com.marmoush.jann.utils.functors.IPerformance;

public class LingRgrTrainTest {
    private DoubleMatrix targets = null;
    private DoubleMatrix inputs = null;
    private List<DoubleMatrix> inputList = null;
    private List<DoubleMatrix> targetList = null;

    @Test
    public void createImage() {
	List<Double> range = MatrixUtils.range(0, 1, 10);
	List<Double> batchErr = trainBatchLinRgr();
	XYSeries xyBatch = ChartUtils.xySeries("Batch", range, batchErr);
	List<Double> stochErr = trainStochasticLinRgr();
	XYSeries xyStoch = ChartUtils.xySeries("Stochastic", range, stochErr);
	String path = "ChartsOutput" + File.separator + "chart.png";
//	ChartUtils.createLinRgrPNG(path,
//		"Linear Regression Batch Vs Stochastic","Iterations","Error", xyBatch, xyStoch);
    }

    @Before
    public void setUp() throws Exception {
	String path = TestingData.getPath("ex1", "ex1data1.txt");
	DoubleMatrix data = DoubleMatrix.loadAsciiFile(path);

	inputs = data.getColumn(0);
	targets = data.getColumn(1);
	inputList = MatrixUtils.batchMtrxToColVecsList(inputs
		.transpose());
	targetList = MatrixUtils.batchMtrxToColVecsList(targets
		.transpose());
    }

    @After
    public void tearDown() throws Exception {

	System.out.println("-----------------------------------------------");
    }

    public List<Double> trainBatchLinRgr() {
	System.out.println();
	System.out.println("Batch Training");
	final LinearRegression blr = new LinearRegression(inputs,
		targets, true);
	blr.setFill(1, blr.getWeight(), blr.getBias());
	blr.setLearnRate(0.01);
	blr.setReguFctr(1);
	blr.setPerformancefnctr(IPerformance.LINRGR_RGU);
	Train training = new Train(1500, 0.001, 1000) {
	    @Override
	    public double train() {
		TrainUtils.batchGd(blr, inputs, targets);
		return blr.getPerformance();
	    }
	};
	training.run();
	System.out.println(training);
	blr.getBias().print();
	blr.getWeight().print();
	return training.getPerformanceHistory();
    }

    public List<Double> trainStochasticLinRgr() {
	System.out.println();
	System.out.println("Stochastic Trainging ");
	final LinearRegression slr = new LinearRegression(inputList,
		targetList, true);
	slr.setFill(1, slr.getWeight(), slr.getBias());
	slr.setLearnRate(0.01);
	Train training = new Train(10, 0.001, 1000) {
	    @Override
	    public double train() {
		TrainUtils.stochasticGd(slr, inputList, targetList);
		return slr.getPerformance();
	    }
	};
	training.run();
	System.out.println(training);
	return training.getPerformanceHistory();
    }
}