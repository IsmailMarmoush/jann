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
package com.marmoush.jann.utils.functors;

import org.jblas.DoubleMatrix;

import com.marmoush.jann.utils.PerformanceUtils;

/**
 * The Interface IPerformance.
 */
public interface IPerformance extends IFunctionable {
    
    /** The Constant MAE. */
    public static final IPerformance MAE = new IPerformance() {
	@Override
	public double measurePerformance(DoubleMatrix error) {
	    return PerformanceUtils.mae(error);
	}

	@Override
	public String toString() {
	    return "MAE";
	}
    };

    /** The Constant MSE. */
    public static final IPerformance MSE = new IPerformance() {
	@Override
	public double measurePerformance(DoubleMatrix error) {
	    return PerformanceUtils.mse(error);
	}

	@Override
	public String toString() {
	    return "MSE";
	}
    };

    /** The Constant SSE. */
    public static final IPerformance SSE = new IPerformance() {
	@Override
	public double measurePerformance(DoubleMatrix error) {
	    return PerformanceUtils.sse(error);
	}

	@Override
	public String toString() {
	    return "SSE";
	}
    };

    /**
     * Measure performance.
     * 
     * @param error
     *            the error
     * @return the double
     */
    public double measurePerformance(DoubleMatrix error);
}
