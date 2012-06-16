package com.marmoush.jann.test.unit.train;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.jblas.DoubleMatrix;
import org.junit.Before;
import org.junit.Test;

import com.marmoush.jann.sv.SvLayer;
import com.marmoush.jann.train.TrainLinRgr;
import com.marmoush.jann.train.TrainResult;
import com.marmoush.jann.utils.MatrixUtils;
import com.marmoush.jann.utils.TrainUtils;

public class TestTrainLinRgr {
    private TrainLinRgr train = null;
    private DoubleMatrix batchInputs = null;
    private DoubleMatrix batchTargets = null;
    private SvLayer layer = null;
    List<DoubleMatrix> inputList = null;
    List<DoubleMatrix> targetList = null;

    @Before
    public void setUp() throws Exception {
//	File f = new File("\\hello.txt");
//	boolean blnCreated = f.createNewFile();
//	System.out.println("Was file " + f.getPath() + " created ? : "
//		+ blnCreated);
	String path = ClassLoader.getSystemResource("ex1data1.txt").toString();
	DoubleMatrix data = DoubleMatrix.loadAsciiFile(path);

	data.print();
	batchInputs = DoubleMatrix.valueOf("1 1 1 ; 1 1 1 ; 0 9 2 ; 3 2 1");
	batchTargets = DoubleMatrix.valueOf("1;1; 0;0");
	inputList = MatrixUtils.mtrx2colVecsList(batchInputs.transpose());
	targetList = MatrixUtils.mtrx2colVecsList(batchTargets.transpose());

	layer = new SvLayer(batchInputs.columns, 1);
	train = new TrainLinRgr(0.1, 1000, 5);

    }

    @Test
    public void testBatchToLimits() {
	TrainResult tr = train.batchToLimits(layer, inputList, targetList);
	System.out.println(tr.toString());

	DoubleMatrix nqWeight = TrainUtils.normalEqPinv(batchInputs,
		batchTargets);

	System.out.println("NormalEq Weight=" + nqWeight);
	System.out.println("BatchLinRgr Weight=" + layer.getWeight());
    }

}
