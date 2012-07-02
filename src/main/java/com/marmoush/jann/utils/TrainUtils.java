/*
 * Copyright 2011 Ismail Marmoush
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

/**
 * The Class TrainUtils.
 */
public class TrainUtils {
    public static void batchGD(SvLayer layer, DoubleMatrix batchTrainingEx,
	    DoubleMatrix batchTargets) {
	/*
	 * Batch only works for one neuron and m here is number of training
	 * examples
	 */
	// grad = 1./m * X' * (sigmoid(X * theta) - y)
	layer.setInput(batchTrainingEx);
	layer.setTarget(batchTargets);
	layer.simulate();
	double m = batchTargets.length;
	DoubleMatrix error = layer.getOutput().sub(layer.getTarget());
	DoubleMatrix xT = batchTrainingEx.transpose();
	DoubleMatrix grad = xT.mmul(error).divi(m);
	// W= W - lrnRate .* grad;
	layer.getWeight().subi(grad.mul(layer.getLearnRate()));
	if (layer.isBiased()) {
	    // gradBias = 1/m * ones(X_rows,1)' * (sigmoid(X*theta));
	    double gradBias = error.sum() / m;
	    // bias=bias - lrnRate* grad;
	    layer.getBias().subi(layer.getLearnRate() * gradBias);
	}
    }

    public static DoubleMatrix normalEqInv(DoubleMatrix x, DoubleMatrix targets) {
	// normal equations works only for linear regression (one output)
	// X rows = m trainning examples
	// X cols = n of features
	// y is column vector where rows = target value for each training
	// examples
	// ((X'*X)^-1) * (X' * y)
	DoubleMatrix inverse = MatrixUtils.inv(x.transpose().mmul(x));
	DoubleMatrix xTransposeY = x.transpose().mmul(targets);
	return inverse.mmul(xTransposeY);
    }

    public static DoubleMatrix normalEqPinv(DoubleMatrix x, DoubleMatrix targets) {
	// normal equations works only for linear regression (one output)
	// X rows = m trainning examples
	// X cols = n of features
	// y is column vector where rows = target value for each training
	// examples
	// ((X'*X)^-1) * (X' * y)
	DoubleMatrix inverse = MatrixUtils.pinv(x.transpose().mmul(x));
	DoubleMatrix xTransposeY = x.transpose().mmul(targets);
	return inverse.mmul(xTransposeY);

    }

    public static void stochasticGD(SvLayer layer,
	    List<DoubleMatrix> trainingEx, List<DoubleMatrix> targets) {
	double performanceSum = 0;
	for (int i = 0; i < trainingEx.size(); i++) {
	    layer.setInput(trainingEx.get(i));
	    layer.setTarget(targets.get(i));
	    layer.simulate();
	    performanceSum += layer.getPerformance();
	    int m = targets.get(i).length; // m = number of neurons
	    DoubleMatrix error = layer.getOutput().sub(layer.getTarget());
	    DoubleMatrix sum = error.transpose().mmul(trainingEx.get(i));
	    DoubleMatrix deltaWeight = sum.mul(layer.getLearnRate() / m);
	    layer.getWeight().subi(deltaWeight);
	    if (layer.isBiased()) {
		double deltaBias = (layer.getLearnRate() / m) * error.sum();
		layer.getBias().subi(deltaBias);
	    }
	    layer.setPerformance(performanceSum / trainingEx.size());
	}
    }
}
