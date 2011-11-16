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

// TODO: Auto-generated Javadoc
/**
 * The Class PerformanceUtils.
 */
public class PerformanceUtils
{

    /**
     * Mae.
     *
     * @param error the error
     * @return the double
     */
    public static double mae(final DoubleMatrix error)
    {
	return MatrixFunctions.abs(error).sum() / error.length;
    }

    /**
     * Mse.
     *
     * @param error the error
     * @return the double
     */
    public static double mse(final DoubleMatrix error)
    {
	return MatrixFunctions.pow(error, 2).sum() / error.length;
    }

    /**
     * Sse.
     *
     * @param error the error
     * @return the double
     */
    public static double sse(final DoubleMatrix error)
    {
	return MatrixFunctions.pow(error, 2).sum();
    }
}
