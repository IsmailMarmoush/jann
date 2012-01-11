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
 * The Class MatrixUtils.
 */
public class MatrixUtils {

	/**
	 * Random matrix.
	 * 
	 * @param rows
	 *            the rows
	 * @param cols
	 *            the cols
	 * @param min
	 *            the min
	 * @param max
	 *            the max
	 * @return the double matrix
	 */
	public static DoubleMatrix randomMatrix(int rows, int cols, double min,
			double max) {
		return DoubleMatrix.rand(rows, cols).muli((max - min) + 1).addi(min);
	}

	/**
	 * Random matrix floor.
	 * 
	 * @param rows
	 *            the rows
	 * @param cols
	 *            the cols
	 * @param min
	 *            the min
	 * @param max
	 *            the max
	 * @return the double matrix
	 */
	public static DoubleMatrix randomMatrixFloor(int rows, int cols, int min,
			int max) {
		DoubleMatrix randMtrx = DoubleMatrix.rand(rows, cols);
		randMtrx.muli((max - min) + 1).addi(min);
		return MatrixFunctions.floori(randMtrx);
	}

	/**
	 * Sets the fill random.
	 * 
	 * @param matrices
	 *            the new fill random
	 */
	public static void setFillRandom(DoubleMatrix... matrices) {
		for (DoubleMatrix mtrx : matrices) {
			if (mtrx != null) {
				mtrx.fill(0).addi(DoubleMatrix.rand(mtrx.rows, mtrx.columns));
			}
		}
	}

	/**
	 * Sets the fill random floor.
	 * 
	 * @param matrices
	 *            the new fill random floor
	 */
	public static void setFillRandomFloor(DoubleMatrix... matrices) {
		for (DoubleMatrix mtrx : matrices) {
			if (mtrx != null) {
				mtrx.fill(0).addi(DoubleMatrix.rand(mtrx.rows, mtrx.columns));
				MatrixFunctions.floori(mtrx);
			}
		}
	}

	/**
	 * Sets the fill random min max.
	 * 
	 * @param min
	 *            the min
	 * @param max
	 *            the max
	 * @param matrices
	 *            the matrices
	 */
	public static void setFillRandomMinMax(double min, double max,
			DoubleMatrix... matrices) {
		for (DoubleMatrix mtrx : matrices) {
			if (mtrx != null) {
				mtrx.fill(0).addi(DoubleMatrix.rand(mtrx.rows, mtrx.columns));
				mtrx.muli((max - min) + 1);
				mtrx.addi(min);
			}
		}
	}

	/**
	 * Sets the fill random min max floor.
	 * 
	 * @param min
	 *            the min
	 * @param max
	 *            the max
	 * @param matrices
	 *            the matrices
	 */
	public static void setFillRandomMinMaxFloor(int min, int max,
			DoubleMatrix... matrices) {
		for (DoubleMatrix mtrx : matrices) {
			if (mtrx != null) {
				mtrx.fill(0).addi(DoubleMatrix.rand(mtrx.rows, mtrx.columns));
				mtrx.muli((max - min) + 1).addi(min);
				MatrixFunctions.floori(mtrx);
			}
		}
	}
}
