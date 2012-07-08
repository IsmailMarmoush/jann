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
package com.marmoush.jann.utils;

import org.jblas.DoubleMatrix;
import org.jblas.MatrixFunctions;


/**
 * 
 */
public abstract class TransfereUtils {

    /**
     * 
     *
     * @param netSum 
     * @return 
     */
    public static DoubleMatrix compet(final DoubleMatrix netSum) {
	int maxIndex = netSum.argmax();
	DoubleMatrix result = DoubleMatrix.zeros(netSum.length);
	result.put(maxIndex, 1);
	return result;
    }

    /**
     * 
     *
     * @param netSum 
     * @param theta 
     * @return 
     */
    public static DoubleMatrix hardlim(final DoubleMatrix netSum,
	    final double theta) {
	return netSum.gt(theta);
    }

    /**
     * 
     *
     * @param netSum 
     * @param theta 
     * @return 
     */
    public static DoubleMatrix hardlims(final DoubleMatrix netSum,
	    final double theta) {
	DoubleMatrix result = new DoubleMatrix(netSum.length);
	for (int i = 0; i < netSum.length; i++) {
	    result.put(i, netSum.get(i) > theta ? 1 : -1);
	}
	return result;
    }

    /**
     * 
     *
     * @param netSum 
     * @return 
     */
    public static DoubleMatrix logsig(final DoubleMatrix netSum) {
	return MatrixFunctions.pow((MatrixFunctions.exp(netSum.neg()).add(1)),
		-1);
    }

    /**
     * 
     *
     * @param netSum 
     * @return 
     */
    public static DoubleMatrix logsigDiff(final DoubleMatrix netSum) {
	// =g(z)(1-g(z))
	// =g(z)-g(z)^2
	// =netSum-netSum^2
	DoubleMatrix netSumLogSig = logsig(netSum);
	return netSumLogSig.sub(MatrixFunctions.pow(netSumLogSig, 2));
    }

    /**
     * 
     *
     * @param netSum 
     * @param theta 
     * @return 
     */
    public static DoubleMatrix poslin(final DoubleMatrix netSum,
	    final double theta) {
	DoubleMatrix result = new DoubleMatrix(netSum.length);
	for (int i = 0; i < netSum.length; i++) {
	    if (netSum.get(i) < theta)
		result.put(i, 0);
	}
	return result;
    }

    /**
     * 
     *
     * @param netSum 
     * @return 
     */
    public static DoubleMatrix purelin(final DoubleMatrix netSum) {
	return netSum;
    }

    /**
     * 
     *
     * @param netSum 
     * @return 
     */
    public static DoubleMatrix satlin(final DoubleMatrix netSum) {
	DoubleMatrix result = new DoubleMatrix(netSum.length);
	for (int i = 0; i < netSum.length; i++) {
	    if (netSum.get(i) < 0)
		result.put(i, 0);
	    else if (netSum.get(i) > 1)
		result.put(i, 1);
	}
	return result;
    }

    /**
     * 
     *
     * @param netSum 
     * @return 
     */
    public static DoubleMatrix satlins(final DoubleMatrix netSum) {
	DoubleMatrix result = new DoubleMatrix(netSum.length);
	for (int i = 0; i < netSum.length; i++) {
	    if (netSum.get(i) < 0)
		result.put(i, -1);
	    else if (netSum.get(i) > 1)
		result.put(i, 1);
	}
	return result;
    }

    /**
     * 
     *
     * @param netSum 
     * @return 
     */
    public static DoubleMatrix tansig(final DoubleMatrix netSum) {
	return MatrixFunctions.tanh(netSum);
    }

    /**
     * 
     *
     * @param netSum 
     * @return 
     */
    public static DoubleMatrix tansigDiff(final DoubleMatrix netSum) {
	// (1-(f(netSum)^2))
	// ((f(netSum)^2)*-1)+1
	return MatrixFunctions.pow(tansig(netSum), 2).neg().add(1);
    }
}