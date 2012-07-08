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
package com.marmoush.jann.utils;

import java.util.List;

import org.jblas.DoubleMatrix;

import com.marmoush.jann.sv.SvLayer;

// TODO: Auto-generated Javadoc
/**
 * 
 */
public class TrainUtils {

    /**
     * 
     * 
     * @param lyr
     * @param inputs
     * @param targets
     */
    public static void batchGd(SvLayer lyr, DoubleMatrix inputs,
	    DoubleMatrix targets) {
	lyr.setInput(inputs);
	lyr.setTarget(targets);
	lyr.simulate();
	double m = targets.length;
	double alpha = lyr.getLearnRate();
	double lambda = lyr.getReguFctr();
	DoubleMatrix error = lyr.getOutput().sub(targets);
	DoubleMatrix grad = inputs.transpose().mmul(error).divi(m);
	double regu = 1 - ((alpha * lambda) / m);
	lyr.getWeight().muli(regu).subi(grad.mul(alpha));
	if (lyr.isBiased()) {
	    lyr.getBias().subi((alpha / m) * error.sum());
	}
    }

    /**
     * 
     * 
     * @param x
     * @param targets
     * @return
     */
    public static DoubleMatrix normalEqInv(DoubleMatrix x, DoubleMatrix targets) {
	// ((X'*X)^-1) * (X' * y)
	DoubleMatrix inverse = MatrixUtils.inv(x.transpose().mmul(x));
	DoubleMatrix xTransposeY = x.transpose().mmul(targets);
	return inverse.mmul(xTransposeY);
    }

    /**
     * 
     * 
     * @param x
     * @param targets
     * @return
     */
    public static DoubleMatrix normalEqPinv(DoubleMatrix x, DoubleMatrix targets) {
	DoubleMatrix inverse = MatrixUtils.pinv(x.transpose().mmul(x));
	DoubleMatrix xTransposeY = x.transpose().mmul(targets);
	return inverse.mmul(xTransposeY);
    }

    /**
     * 
     * 
     * @param x
     * @param targets
     * @param rguFctr
     * @param biased
     * @return
     */
    public static DoubleMatrix normalEqPinvRgu(DoubleMatrix x,
	    DoubleMatrix targets, double rguFctr, boolean biased) {
	DoubleMatrix inv = x.transpose().mmul(x);
	DoubleMatrix eye = DoubleMatrix.eye(x.columns).mul(rguFctr);
	if (biased)
	    eye.put(0, 0);
	DoubleMatrix inverse = MatrixUtils.pinv(inv.addi(eye));
	DoubleMatrix xTransposeY = x.transpose().mmul(targets);
	return inverse.mmul(xTransposeY);
    }

    /**
     * 
     * 
     * @param lyr
     * @param inputs
     * @param targets
     */
    public static void stochasticGd(SvLayer lyr, List<DoubleMatrix> inputs,
	    List<DoubleMatrix> targets) {
	int m = 0;
	double alpha = lyr.getLearnRate();
	double performanceSum = 0;
	for (int i = 0; i < inputs.size(); i++) {
	    m = targets.get(i).length;
	    lyr.setInput(inputs.get(i));
	    lyr.setTarget(targets.get(i));
	    lyr.simulate();
	    performanceSum += lyr.getPerformance();
	    DoubleMatrix error = lyr.getOutput().sub(lyr.getTarget());
	    DoubleMatrix sum = error.transpose().mmul(lyr.getInput());
	    DoubleMatrix deltaWeight = sum.mul(alpha / m);
	    lyr.getWeight().subi(deltaWeight);
	    if (lyr.isBiased()) {
		lyr.getBias().subi((alpha / m) * error.sum());
	    }
	    lyr.setPerformance(performanceSum / inputs.size());
	}
    }
}
