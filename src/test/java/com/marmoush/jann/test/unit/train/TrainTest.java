package com.marmoush.jann.test.unit.train;

import java.util.List;

import org.jblas.DoubleMatrix;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.marmoush.jann.sv.SvLayer;
import com.marmoush.jann.train.Train;
import com.marmoush.jann.utils.TrainUtils;
import com.marmoush.jann.utils.functors.IPerformance;
import com.marmoush.jann.utils.functors.IWeight;

public class TrainTest {
    SvLayer layer = null;
    private DoubleMatrix batchTrainingEx = null;
    private DoubleMatrix batchTargets = null;
    List<DoubleMatrix> inputList = null;
    List<DoubleMatrix> targetList = null;

    @Before
    public void setUp() throws Exception {
	DoubleMatrix data = DoubleMatrix
		.loadAsciiFile("src\\test\\java\\ex1data1.txt");
	batchTrainingEx = data.getColumn(0);
	batchTargets = data.getColumn(1);
    }

    @After
    public void tearDown() throws Exception {

	System.out.println("-----------------------------------------------");
    }

    @Test
    public void testLinRgrLayer() {
	System.out.println(" Trainging as a layer");
	layer = new SvLayer(batchTrainingEx.columns, 1,true);
	layer.setWeightFnctr(IWeight.BATCH_DOTPROD);
	layer.setPerformancefnctr(IPerformance.MSE_LinRgr);
	layer.setLearnRate(0.01);
	layer.setFill(1, layer.getWeight(), layer.getBias());
	Train t = new Train(1500, 0.001, 1000) {
	    @Override
	    public double train() {
		TrainUtils.batchLinRgrLayer(layer, batchTrainingEx,
			batchTargets);
		return layer.getPerformance();
	    }
	};
	t.run();
	System.out.println(t);

    }
}
