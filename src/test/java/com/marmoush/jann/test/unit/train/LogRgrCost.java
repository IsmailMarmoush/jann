/*
 * Copyright 2012 Ismail Marmoush This file is part of JANN. JANN is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * General Public License Version 3 as published by the Free Software
 * Foundation, either version 3 of the License, or any later version. JANN is
 * distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details. You
 * should have received a copy of the GNU General Public License along with
 * JANN. If not, see http://www.gnu.org/licenses/. For More Information Please
 * Visit http://jann.marmoush.com
 */
package com.marmoush.jann.test.unit.train;

import java.io.IOException;

import org.jblas.DoubleMatrix;

import com.marmoush.jann.sv.SvLayer;
import com.marmoush.jann.test.TestingData;
import com.marmoush.jann.utils.PerformanceUtils;
import com.marmoush.jann.utils.TransfereUtils;
import com.marmoush.jann.utils.functors.IPerformance;
import com.marmoush.jann.utils.functors.ITransfere;
import com.marmoush.jann.utils.functors.IWeight;

// TODO: Auto-generated Javadoc
/**
 * 
 */
public class LogRgrCost {

    /**
     * 
     *
     * @param args 
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
	String path = TestingData.getPath("ex2", "ex2data1Bias.txt");
	DoubleMatrix data = DoubleMatrix.loadAsciiFile(path);
	DoubleMatrix inputs = data.getColumns(new int[] { 0, 1, 2 });
	DoubleMatrix targets = data.getColumn(3);
	DoubleMatrix theta = DoubleMatrix.zeros(inputs.columns, 1);
	/***********************************/
	DoubleMatrix netsum = inputs.mmul(theta);
	// netsum.print();
	DoubleMatrix output = TransfereUtils.logsig(netsum);
	// output.print();
	System.out.println(PerformanceUtils.logRgr(output, targets));
	/***********************************/
	SvLayer layer = new SvLayer(inputs.columns, 1, false);
	layer.setInput(inputs);
	layer.setTarget(targets);
	layer.setWeightFnctr(IWeight.BATCH_DOTPROD);
	layer.setTransfereFnctr(ITransfere.LOGSIG);
	layer.setPerformancefnctr(IPerformance.LOGRGR);
	layer.simulate();
	// layer.getNetSum().print();
	// layer.getOutput().print();
	System.out.println(layer.getPerformance());
	/***********************************/
	int m = targets.length;
	DoubleMatrix error = output.sub(targets);
	DoubleMatrix xT = inputs.transpose();
	DoubleMatrix grad = xT.mmul(error).mul(1.0 / m);
	grad.print();
	// grad.neg().print();
	/*************************************/
    }
}
