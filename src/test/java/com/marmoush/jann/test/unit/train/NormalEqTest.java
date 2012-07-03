package com.marmoush.jann.test.unit.train;

import org.jblas.DoubleMatrix;
import org.junit.Before;
import org.junit.Test;

import com.marmoush.jann.test.TestingData;
import com.marmoush.jann.utils.TrainUtils;

public class NormalEqTest {
    DoubleMatrix inputs;
    DoubleMatrix targets;

    @Before
    public void setUp() throws Exception {
	String path = TestingData.getPath("ex2", "ex2data1Bias.txt");
	DoubleMatrix data = DoubleMatrix.loadAsciiFile(path);
	inputs = data.getColumns(new int[] { 0, 1, 2 });
	targets = data.getColumn(3);
	
    }

    @Test
    public void normalEqPinvRgu() {
	TrainUtils.normalEqPinv(inputs, targets).print();
	TrainUtils.normalEqPinvRgu(inputs, targets, 10, true).print();
	
    }
}
