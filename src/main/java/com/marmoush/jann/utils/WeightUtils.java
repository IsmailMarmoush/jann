/*
 * Copyright 2011 Ismail Marmoush This file is part of JANN. JANN is free
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

public abstract class WeightUtils {

    
    public static DoubleMatrix batchDotprod(DoubleMatrix batchTrainingEx,
	    DoubleMatrix weight) {
	DoubleMatrix out = batchTrainingEx.mmul(weight.transpose());
	return out;
    }

    
    public static DoubleMatrix batchDotprod(DoubleMatrix batchTrainingEx,
	    DoubleMatrix bias, DoubleMatrix weight) {
	/*
	 * Note1: in batch all the training data inserted at once so bias will
	 * be added to each training example remember Batch calculation is
	 * correct only when there is only one neuron, because batchTrainingEx
	 * rows = m Training examples & columns = n features if there was
	 * another neuron it will need another dimension. But if we wanted to do
	 * batch calcs for K neurons we can have K layers where each layer has
	 * one neuron
	 */
	// Based on the previous:
	// assert (bias.length==1)
	DoubleMatrix out = batchTrainingEx.mmul(weight.transpose());
	// assert (out.columns==1)
	// Add bias to each row
	out.addiRowVector(bias);
	return out;
    }

    
    public static DoubleMatrix dotprod(DoubleMatrix input, DoubleMatrix weight) {
	return weight.mmul(input);
    }

    
    public static DoubleMatrix dotprod(DoubleMatrix input, DoubleMatrix bias,
	    DoubleMatrix weight) {
	return weight.mmul(input).add(bias);
    }
}
