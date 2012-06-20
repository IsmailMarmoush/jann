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

import org.jblas.DoubleMatrix;

/**
 * The Class TrainUtils.
 */
public class TrainUtils {
    public static DoubleMatrix batchLinRgrGd(DoubleMatrix batchTrainingEx,
	    DoubleMatrix batchTargets, DoubleMatrix initWeight, double lrnRate) {
	// In batch the weights are updated after examining all examples
	// m = length(y); % number of training examples
	int m = batchTargets.length;
	// out=X*theta; %97*2 * 2*1= 97*1
	DoubleMatrix output = batchTrainingEx.mmul(initWeight);
	// sm=(out-y)'*X; %(97*1-97*1)' * 97*2 = 1*97 * 97*2 = 1*2
	DoubleMatrix sm = output.sub(batchTargets).transpose()
		.mmul(batchTrainingEx);
	// deltaTheta=sm.*(alpha/m); % (1*2) .* number
	DoubleMatrix deltaWeight = sm.mul(lrnRate / m);
	// theta=theta-deltaTheta'; % 2*1 - (1*2)' = 2*1 - 2*1
	initWeight = initWeight.sub(deltaWeight);
	return initWeight;
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
}
