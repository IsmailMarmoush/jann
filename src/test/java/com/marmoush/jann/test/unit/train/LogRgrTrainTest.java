/*
 * Copyright 2012 Ismail Marmoush
 * 
 * This file is part of JANN.
 * 
 * JANN is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License Version 3 as published by the Free
 * Software Foundation, either version 3 of the License, or any later version.
 * 
 * JANN is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * JANN. If not, see http://www.gnu.org/licenses/.
 * 
 * For More Information Please Visit http://jann.marmoush.com
 */
package com.marmoush.jann.test.unit.train;

import java.io.File;
import java.util.List;

import org.jblas.DoubleMatrix;
import org.jfree.data.xy.XYSeries;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.marmoush.jann.chart.ChartUtils;
import com.marmoush.jann.chart.LineImg;
import com.marmoush.jann.chart.ScatterDataImg;
import com.marmoush.jann.model.regression.LogisticRegression;
import com.marmoush.jann.test.TestingData;
import com.marmoush.jann.train.Train;
import com.marmoush.jann.utils.MatrixUtils;
import com.marmoush.jann.utils.TrainUtils;

// TODO: Auto-generated Javadoc
/**
 * 
 */
public class LogRgrTrainTest {

    /**
     * 
     */
    private DoubleMatrix data = null;

    /**
     * 
     */
    private List<DoubleMatrix> inputList = null;

    /**
     * 
     */
    private DoubleMatrix inputs = null;

    /**
     * 
     */
    private List<DoubleMatrix> targetList = null;

    /**
     * 
     */
    private DoubleMatrix targets = null;

    /**
     * 
     */
    @Test
    public void createDataImg() {
	String path = "ChartsOutput" + File.separator + "data.png";
	DoubleMatrix d1 = DoubleMatrix.zeros(data.rows, 2);
	DoubleMatrix d2 = DoubleMatrix.zeros(data.rows, 2);
	for (int i = 0; i < data.rows; i++) {
	    if (data.get(i, 2) == 1.0) {
		d1.put(i, 0, data.get(i, 0));
		d1.put(i, 1, data.get(i, 1));
	    } else {
		d2.put(i, 0, data.get(i, 0));
		d2.put(i, 1, data.get(i, 1));
	    }

	}
	XYSeries xys = ChartUtils.xySeries("Admitted", d1, 0, 1);
	XYSeries xys2 = ChartUtils.xySeries("Not Admitted", d2, 0, 1);
	ScatterDataImg img = new ScatterDataImg(path, xys, xys2);
	img.setLegend(true);
	img.setxAxisTitle("exam1 score");
	img.setyAxisTitle("exam2 score");
	img.createPNG();
    }

    /**
     * 
     */
    @Test
    public void createErrorIterImage() {
	List<Double> range = MatrixUtils.range(0, 1, 400);
	List<Double> stochErr = trainStochasticLinRgr();
	List<Double> batchErr = trainBatchLogRgr();
	XYSeries xyStoch = ChartUtils.xySeries("Stochastic", range, stochErr);
	XYSeries xyBatch = ChartUtils.xySeries("Batch", range, batchErr);
	String path = "ChartsOutput" + File.separator + "chart2.png";
	LineImg img = new LineImg(path, xyBatch, xyStoch);
	img.setxAxisTitle("Iterations");
	img.setyAxisTitle("Error");
	img.createPNG();

    }

    /**
     * 
     * 
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
	String path = TestingData.getPath("ex2", "ex2data1.txt");
	data = DoubleMatrix.loadAsciiFile(path);
	inputs = data.getColumns(new int[] { 0, 1 });
	targets = data.getColumn(2);
	inputList = MatrixUtils.batchMtrxToColVecsList(inputs.transpose());
	targetList = MatrixUtils.batchMtrxToColVecsList(targets.transpose());

    }

    /**
     * 
     * 
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {

	System.out.println("-----------------------------------------------");
    }

    /**
     * 
     * 
     * @return
     */
    public List<Double> trainBatchLogRgr() {
	System.out.println("Batch Training");
	final LogisticRegression blr = new LogisticRegression(inputs, targets,
		true);

	blr.setLearnRate(0.0001);
	Train batchTrain = new Train(1500, 0.001, 1000) {
	    @Override
	    public double train() {
		TrainUtils.batchGd(blr, inputs, targets);
		return blr.getPerformance();
	    }
	};
	batchTrain.run();
	System.out.println(batchTrain);
	// blr.getBias().print();
	blr.getWeight().print();

	return batchTrain.getPerformanceHistory();
    }

    /**
     * 
     * 
     * @return
     */
    public List<Double> trainStochasticLinRgr() {
	System.out.println("Stochastic Trainging ");
	final LogisticRegression slr = new LogisticRegression(inputList,
		targetList, true);
	slr.setLearnRate(0.0001);
	Train training = new Train(400, 0.001, 1000) {
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
