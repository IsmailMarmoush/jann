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

import java.util.ArrayList;
import java.util.List;

import org.jblas.DoubleMatrix;
import org.jblas.MatrixFunctions;
import org.jblas.Singular;
import org.jblas.Solve;

/**
 * The Class MatrixUtils.
 */
public class MatrixUtils {
    /**
     * The difference between 1 and the smallest exactly representable number
     * greater than one. Gives an upper bound on the relative error due to
     * rounding of floating point numbers.
     */
    public static final double MACHEPS = 2E-16;

    public static DoubleMatrix colVecsList2BatchMtrx(
	    List<DoubleMatrix> listOfVectors) {
	// assert vectors with same size
	int rows = listOfVectors.get(0).rows;
	int cols = listOfVectors.size();
	DoubleMatrix batch = DoubleMatrix.zeros(rows, cols);
	for (int i = 0; i < listOfVectors.size(); i++) {
	    batch.putColumn(i, listOfVectors.get(i));
	}
	return batch;
    }

    public static boolean equals(DoubleMatrix mtrx1, DoubleMatrix mtrx2) {
	if (mtrx1.equals(mtrx2)) {
	    return true;
	} else {
	    DoubleMatrix bo = mtrx1.eq(mtrx2);
	    if (bo.min() == 1) {
		return true;
	    }
	}
	return false;
    }

    public static DoubleMatrix featureMapping(double x1, double x2, int degree) {
	// Returns row vector
	// for i = 1:degree
	// for j = 0:i
	// out(:, end+1) = (X1.^(i-j)).*(X2.^j);
	int nFeatures = getNumFeaturesMapped(degree);
	List<Double> list = new ArrayList<Double>(nFeatures);
	for (int i = 1; i <= degree; i++) {
	    for (int j = 0; j <= i; j++) {
		list.add(Math.pow(x1, i - j) * Math.pow(x2, j));
	    }
	}
	return new DoubleMatrix(list);
    }

    public static DoubleMatrix featureMapping(DoubleMatrix input, int degree) {
	// returns a matrix.rows = input.rows, matrix.columns=nFeatures(degree)
	int nFeatures = MatrixUtils.getNumFeaturesMapped(degree);
	DoubleMatrix mtrx = DoubleMatrix.zeros(input.rows, nFeatures);
	DoubleMatrix row = null;
	// assert(input.columns==2)
	for (int i = 0; i < input.rows; i++) {
	    double x1 = input.get(i, 0);
	    double x2 = input.get(i, 1);
	    row = MatrixUtils.featureMapping(x1, x2, degree);
	    mtrx.putRow(i, row);
	}
	return mtrx;
    }

    public static DoubleMatrix featureScalingByAvrg(DoubleMatrix input) {
	double mean = input.mean();
	double max = input.max();
	double min = input.min();
	return input.sub(mean).div(max - min);
    }

    public static DoubleMatrix featureScalingBySTD(DoubleMatrix input) {
	double mean = input.mean();
	double std = standardDeviation(input);
	return input.sub(mean).div(std);
    }

    public static int getNumFeaturesMapped(int degree) {
	return (int) (1.5 * degree + 0.5 * Math.pow(degree, 2));
    }

    public static String getSize(DoubleMatrix mtrx) {
	return mtrx.rows + "*" + mtrx.columns;
    }

    public static DoubleMatrix inv(DoubleMatrix mtrx) {
	return Solve.solvePositive(mtrx, DoubleMatrix.eye(mtrx.rows));
    }

    public static List<DoubleMatrix> batchMtrxToColVecsList(DoubleMatrix batch) {
	// assert vectors with same size
	int cols = batch.columns;
	List<DoubleMatrix> colVecList = new ArrayList<DoubleMatrix>(cols);
	for (int i = 0; i < cols; i++) {
	    colVecList.add(batch.getColumn(i));
	}
	return colVecList;
    }

    /**
     * Computes the Moore–Penrose pseudoinverse using the SVD method.
     * 
     * Modified version of the original implementation by Kim van der Linde.
     */
    public static DoubleMatrix pinv(DoubleMatrix x) {
	// SingularValueDecomposition svdX = new SingularValueDecomposition(x);
	DoubleMatrix[] fullSVD = Singular.fullSVD(x);
	DoubleMatrix singularValuesDM = fullSVD[1];
	double rank = rankEff(x, singularValuesDM);
	if (rank < 1)
	    return null;
	if (x.columns > x.rows)
	    return pinv(x.transpose()).transpose();
	double[] singularValues = singularValuesDM.toArray();
	double tol = Math.max(x.columns, x.rows) * singularValues[0] * MACHEPS;
	double[] singularValueReciprocals = new double[singularValues.length];
	for (int i = 0; i < singularValues.length; i++)
	    singularValueReciprocals[i] = Math.abs(singularValues[i]) < tol ? 0
		    : (1.0 / singularValues[i]);
	double[][] u = fullSVD[0].toArray2();
	double[][] v = fullSVD[2].toArray2();
	int min = Math.min(x.columns, u[0].length);
	double[][] inverse = new double[x.columns][x.rows];
	for (int i = 0; i < x.columns; i++)
	    for (int j = 0; j < u.length; j++)
		for (int k = 0; k < min; k++)
		    inverse[i][j] += v[i][k] * singularValueReciprocals[k]
			    * u[j][k];
	return new DoubleMatrix(inverse);
    }

    public static void print(boolean withSize, DoubleMatrix... mtrxList) {
	for (DoubleMatrix mtrx : mtrxList) {
	    if (withSize)
		MatrixUtils.printSize(mtrx);
	    mtrx.print();
	}
    }

    public static void print(boolean withSize, List<DoubleMatrix> mtrxList) {
	for (DoubleMatrix mtrx : mtrxList) {
	    if (withSize)
		MatrixUtils.printSize(mtrx);
	    mtrx.print();
	}
    }

    public static void print(String[] names, boolean withSize,
	    DoubleMatrix... mtrxList) {
	for (int i = 0; i < mtrxList.length; i++) {
	    System.out.print(names[i] + ": ");
	    if (withSize)
		MatrixUtils.printSize(mtrxList[i]);
	    mtrxList[i].print();
	}
    }

    public static void printSize(DoubleMatrix mtrx) {
	System.out.print(MatrixUtils.getSize(mtrx));
    }

    public static void printSize(DoubleMatrix... mtrxArray) {
	for (DoubleMatrix m : mtrxArray) {
	    MatrixUtils.printSize(m);
	}
    }

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

    public static double rank(DoubleMatrix A) {
	return rankEff(A, Singular.SVDValues(A));
    }

    public static double rankEff(DoubleMatrix A, DoubleMatrix s) {
	// Where s = svd(A); ==> DoubleMatrix s = Singular.SVDValues(A);

	// tol = max(size(A))*eps(max(s));
	double maxSizeA = Math.max(A.rows, A.columns);
	double eps = Math.pow(2.0, -52.0);
	double maxS = s.max();
	double tol = maxSizeA * eps * maxS;
	// r = sum(s > tol);
	double r = s.gt(tol).sum();
	return r;
    }

    public static DoubleMatrix round(DoubleMatrix mtrx, int decPoints) {
	// (int)Math.floor(a + 0.5f)
	// Math.round(double*100.0)/100.0
	double x = Math.pow(10.0, decPoints);
	mtrx.muli(x).addi(0.5);
	MatrixFunctions.floori(mtrx);
	mtrx.divi(x);
	return mtrx;
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

    public static double standardDeviation(DoubleMatrix input) {
	double mean = input.mean();
	DoubleMatrix dummy = MatrixFunctions.pow(input.sub(mean), 2);
	double sd = dummy.sum() / input.length;
	return Math.sqrt(sd);
    }

    // /**
    // * Updates MACHEPS for the executing machine.
    // */
    // public static void updateMacheps() {
    // MACHEPS = 1;
    // do
    // MACHEPS /= 2;
    // while (1 + MACHEPS / 2 != 1);
    // }
}
