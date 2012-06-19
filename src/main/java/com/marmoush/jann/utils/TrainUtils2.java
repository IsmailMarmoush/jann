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
public class TrainUtils2 {
    
    public static DoubleMatrix batchLinRgrGd( DoubleMatrix batchTrainingEx,DoubleMatrix batchTargets,DoubleMatrix initWeight,double lrnRate) {
	// In batch the weights are updated after examining all examples
	// m = length(y); % number of training examples
	int m = batchTargets.length;
	// out=X*theta; %97*2 * 2*1= 97*1
	
	DoubleMatrix output=batchTrainingEx.mmul(initWeight);
	// sm=(out-y)'*X; %(97*1-97*1)' * 97*2 = 1*97 * 97*2 = 1*2
	DoubleMatrix sm=output.sub(batchTargets).transpose().mmul(batchTrainingEx);
	// deltaTheta=sm.*(alpha/m); % (1*2) .* number
	DoubleMatrix deltaWeight=sm.mul(lrnRate/m);
	// theta=theta-deltaTheta'; % 2*1 - (1*2)' = 2*1 - 2*1
	initWeight=initWeight.sub(deltaWeight);
	return initWeight;
    }

    /**
     * Mse ng derv db.
     * 
     * @param lrnRate
     *            the lrn rate
     * @param output
     *            the output
     * @param target
     *            the target
     * @return the double matrix
     */
    public static DoubleMatrix mseNgDervDB(double lrnRate, DoubleMatrix output,
	    DoubleMatrix target) {

	DoubleMatrix error = output.sub(target);
	DoubleMatrix db = error.muli(lrnRate / output.length);
	return db;
    }

    /**
     * Mse ng derv dw.
     * 
     * @param lrnRate
     *            the lrn rate
     * @param input
     *            the input
     * @param output
     *            the output
     * @param target
     *            the target
     * @return deltaWeight
     */
    public static DoubleMatrix mseNgDervDW(double lrnRate, DoubleMatrix input,
	    DoubleMatrix output, DoubleMatrix target) {
	DoubleMatrix error = output.sub(target);
	// MatrixUtils.print(error,input);
	DoubleMatrix dw = error.mmul(input.transpose()).mul(
		lrnRate / output.length);
	return dw;
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

    /**
     * Stochastic lin reg gd.
     * 
     * @param layer
     *            the layer
     * @param inputList
     *            the input list
     * @param targetList
     *            the target list
     * @return the sv layer
     */
    public static double stochasticLinRgrGd(SvLayer layer,
	    List<DoubleMatrix> inputList, List<DoubleMatrix> targetList) {
	// In stochastic the weights are updated after examining each example.
	/*
	 * Recommended Settings
	 * 
	 * layer.setTransfereFnctr(ITransfere.PURELIN);
	 */
	double sumPerformance = 0;
	for (int i = 0; i < inputList.size(); i++) {
	    layer.setInput(inputList.get(i));
	    layer.setTarget(targetList.get(i));
	    layer.simulate();
	    sumPerformance += layer.getPerformance();
	    DoubleMatrix dw = mseNgDervDW(layer.getLearnRate(),
		    layer.getInput(), layer.getOutput(), layer.getTarget());
	    layer.getWeight().subi(dw);
	    DoubleMatrix db = mseNgDervDB(layer.getLearnRate(),
		    layer.getOutput(), layer.getTarget());
	    layer.getBias().subi(db);
	}
	layer.simulate();
	sumPerformance += layer.getPerformance();
	sumPerformance = sumPerformance / inputList.size();
	return sumPerformance;
    }
}
