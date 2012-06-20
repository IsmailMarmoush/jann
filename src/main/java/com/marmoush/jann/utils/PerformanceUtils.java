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
import org.jblas.MatrixFunctions;

/**
 * The Class PerformanceUtils.
 */
public class PerformanceUtils {

    public static double linRgrCost(DoubleMatrix batchTrainingEx,
	    DoubleMatrix batchTargets, DoubleMatrix weight) {
	int m = batchTargets.length;
	// J=(X*theta-y)' * (X*theta-y);
	DoubleMatrix part1 = batchTrainingEx.mmul(weight).sub(batchTargets)
		.transpose();
	DoubleMatrix part2 = batchTrainingEx.mmul(weight).sub(batchTargets);
	DoubleMatrix j = part1.mmul(part2);
	// J=J / (2*m);
	j.divi(2 * m);
	return j.sum();
    }

    public static double linRgrCost(DoubleMatrix batchTrainingEx,
	    DoubleMatrix batchTargets, DoubleMatrix weight, DoubleMatrix bias) {
	batchTrainingEx = DoubleMatrix.concatHorizontally(
		DoubleMatrix.ones(batchTrainingEx.rows), batchTrainingEx);
	weight = DoubleMatrix.concatVertically(bias, weight);
	return linRgrCost(batchTrainingEx, batchTargets, weight);
    }

    /**
     * Mae.
     * 
     * @param error
     *            the error
     * @return MatrixFunctions.abs(error).sum() / error.length;
     */
    public static double mae(final DoubleMatrix error) {
	return MatrixFunctions.abs(error).sum() / error.length;
    }

    /**
     * Mse.
     * 
     * @param error
     *            the error
     * @return MatrixFunctions.pow(error, 2).sum() / error.length;
     */
    public static double mse(final DoubleMatrix error) {
	// As long as error.length is actually the length of the error matrix
	// unrolled so even if the matrix was 2*3 the length should be 6
	return MatrixFunctions.pow(error, 2).sum() / error.length;
    }

    /**
     * Mse.
     * 
     * @param error
     *            the error
     * @return MatrixFunctions.pow(error, 2).sum() / error.length;
     */
    public static double mseLinRgr(final DoubleMatrix error) {
	// From Andrew Ng, the Cost function of linear Regression with one
	// neuron
	int mTrainingExamples = error.length;
	return MatrixFunctions.pow(error, 2).sum() / (2 * mTrainingExamples);
    }

    /**
     * Sse.
     * 
     * @param error
     *            the errorQp
     * @return MatrixFunctions.pow(error, 2).sum();
     */
    public static double sse(final DoubleMatrix error) {
	return MatrixFunctions.pow(error, 2).sum();
    }
}
