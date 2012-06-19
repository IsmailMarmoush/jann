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

    public static double linRgrCost(DoubleMatrix batchTrainingEx,
	    DoubleMatrix batchTargets, DoubleMatrix weight) {
	int m = batchTargets.length;
	// J=(X*theta-y)' * (X*theta-y);
	DoubleMatrix part1=batchTrainingEx.mmul(weight).sub(batchTargets).transpose();
	DoubleMatrix part2=batchTrainingEx.mmul(weight).sub(batchTargets);
	DoubleMatrix j=part1.mmul(part2);
	// J=J / (2*m);
	j.divi(2*m);
	return j.sum();
    }

    /**
     * Mse.
     * 
     * @param error
     *            the error
     * @return MatrixFunctions.pow(error, 2).sum() / error.length;
     */
    public static double mseNg(final DoubleMatrix error) {
	// Andrew Ng
	/*
	 * The {*error.length} part generalized for multiple neurons. Not that
	 * in AndrewNG it was 2*m where m=number of training sets there is a
	 * difference between m and error.length while error.length would be
	 * equal to m IFF {layer has one neuron and the targets are inserted as
	 * batch} but this way is limited to one neuron and wasn't implemented
	 */
	return MatrixFunctions.pow(error, 2).sum() / (2 * error.length);
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
