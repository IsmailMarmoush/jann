/*
 * Copyright 2012 Ismail Marmoush
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

// TODO: Auto-generated Javadoc
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

    /**
     * Batch feature mapping.
     *
     * @param batchTrainingEx the batch training ex
     * @param degree the degree
     * @param f1ColIndex the f1 col index
     * @param f2ColIndex the f2 col index
     * @return the double matrix
     */
    public static DoubleMatrix batchFeatureMapping(
	    DoubleMatrix batchTrainingEx, int degree, int f1ColIndex,
	    int f2ColIndex) {
	// returns a matrix.rows = input.rows, matrix.columns=nFeatures(degree)
	int nFeatures = MatrixUtils.getNumFeaturesMapped(degree);
	DoubleMatrix mtrx = DoubleMatrix.zeros(batchTrainingEx.rows, nFeatures);
	DoubleMatrix row = null;

	for (int i = 0; i < batchTrainingEx.rows; i++) {
	    double x1 = batchTrainingEx.get(i, f1ColIndex);
	    double x2 = batchTrainingEx.get(i, f2ColIndex);
	    row = MatrixUtils.featureMapping(x1, x2, degree);
	    mtrx.putRow(i, row);
	}
	return mtrx;
    }

    /**
     * Batch mtrx to col vecs list.
     *
     * @param batch the batch
     * @return the list
     */
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
     * Col vecs list2 batch mtrx.
     *
     * @param listOfVectors the list of vectors
     * @return the double matrix
     */
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

    /**
     * Equals.
     *
     * @param mtrx1 the mtrx1
     * @param mtrx2 the mtrx2
     * @return true, if successful
     */
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

    /**
     * Feature mapping.
     *
     * @param x1 the x1
     * @param x2 the x2
     * @param degree the degree
     * @return the double matrix
     */
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

    /**
     * Feature mapping.
     *
     * @param input the input
     * @param degree the degree
     * @param f1Index the f1 index
     * @param f2Index the f2 index
     * @return the double matrix
     */
    public static DoubleMatrix featureMapping(DoubleMatrix input, int degree,
	    int f1Index, int f2Index) {
	// assert(input is column vector or row vector)
	if (input.rows == 1)
	    return batchFeatureMapping(input, degree, f1Index, f2Index);
	else if (input.columns == 1)
	    return batchFeatureMapping(input.transpose(), degree, f1Index,
		    f2Index);
	else
	    return null;
    }

    /**
     * Feature scaling by avrg.
     *
     * @param input the input
     * @return the double matrix
     */
    public static DoubleMatrix featureScalingByAvrg(DoubleMatrix input) {
	double mean = input.mean();
	double max = input.max();
	double min = input.min();
	return input.sub(mean).div(max - min);
    }

    /**
     * Feature scaling by std.
     *
     * @param input the input
     * @return the double matrix
     */
    public static DoubleMatrix featureScalingBySTD(DoubleMatrix input) {
	double mean = input.mean();
	double std = standardDeviation(input);
	return input.sub(mean).div(std);
    }

    /**
     * Gets the num features mapped.
     *
     * @param degree the degree
     * @return the num features mapped
     */
    public static int getNumFeaturesMapped(int degree) {
	return (int) (1.5 * degree + 0.5 * Math.pow(degree, 2));
    }

    /**
     * Gets the size.
     *
     * @param mtrx the mtrx
     * @return the size
     */
    public static String getSize(DoubleMatrix mtrx) {
	return mtrx.rows + "*" + mtrx.columns;
    }

    /**
     * Inv.
     *
     * @param mtrx the mtrx
     * @return the double matrix
     */
    public static DoubleMatrix inv(DoubleMatrix mtrx) {
	return Solve.solvePositive(mtrx, DoubleMatrix.eye(mtrx.rows));
    }

    /**
     * Computes the Moore–Penrose pseudoinverse using the SVD method.
     * 
     * Modified version of the original implementation by Kim van der Linde.
     *
     * @param x the x
     * @return the double matrix
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

    /**
     * Prints the.
     *
     * @param withSize the with size
     * @param mtrxList the mtrx list
     */
    public static void print(boolean withSize, DoubleMatrix... mtrxList) {
	for (DoubleMatrix mtrx : mtrxList) {
	    if (withSize)
		MatrixUtils.printSize(mtrx);
	    mtrx.print();
	}
    }

    /**
     * Prints the.
     *
     * @param withSize the with size
     * @param mtrxList the mtrx list
     */
    public static void print(boolean withSize, List<DoubleMatrix> mtrxList) {
	for (DoubleMatrix mtrx : mtrxList) {
	    if (withSize)
		MatrixUtils.printSize(mtrx);
	    mtrx.print();
	}
    }

    /**
     * Prints the.
     *
     * @param names the names
     * @param withSize the with size
     * @param mtrxList the mtrx list
     */
    public static void print(String[] names, boolean withSize,
	    DoubleMatrix... mtrxList) {
	for (int i = 0; i < mtrxList.length; i++) {
	    System.out.print(names[i] + ": ");
	    if (withSize)
		MatrixUtils.printSize(mtrxList[i]);
	    mtrxList[i].print();
	}
    }

    /**
     * Prints the size.
     *
     * @param mtrx the mtrx
     */
    public static void printSize(DoubleMatrix mtrx) {
	System.out.print(MatrixUtils.getSize(mtrx));
    }

    /**
     * Prints the size.
     *
     * @param mtrxArray the mtrx array
     */
    public static void printSize(DoubleMatrix... mtrxArray) {
	for (DoubleMatrix m : mtrxArray) {
	    MatrixUtils.printSize(m);
	    System.out.println();
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

    /**
     * Range.
     *
     * @param start the start
     * @param incrOrDecrValue the incr or decr value
     * @param end the end
     * @return the list
     */
    public static List<Double> range(double start, double incrOrDecrValue,
	    double end) {
	List<Double> list = new ArrayList<Double>();
	if (start < end) {
	    while (start < end) {
		list.add(start);
		start += incrOrDecrValue;
	    }
	} else if (start > end) {
	    incrOrDecrValue = Math.abs(incrOrDecrValue);
	    while (start > end) {
		list.add(start);
		start -= incrOrDecrValue;
	    }
	}
	return list;
    }

    /**
     * Rank.
     *
     * @param A the a
     * @return the double
     */
    public static double rank(DoubleMatrix A) {
	return rankEff(A, Singular.SVDValues(A));
    }

    /**
     * Rank eff.
     *
     * @param A the a
     * @param s the s
     * @return the double
     */
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

    /**
     * Round.
     *
     * @param mtrx the mtrx
     * @param decPoints the dec points
     * @return the double matrix
     */
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

    /**
     * Standard deviation.
     *
     * @param input the input
     * @return the double
     */
    public static double standardDeviation(DoubleMatrix input) {
	double mean = input.mean();
	DoubleMatrix dummy = MatrixFunctions.pow(input.sub(mean), 2);
	double sd = dummy.sum() / input.length;
	return Math.sqrt(sd);
    }

    /**
     * Standard deviation mat.
     *
     * @param input the input
     * @return the double
     */
    public static double standardDeviationMat(DoubleMatrix input) {
	double mean = input.mean();
	// TODO m-1 or m
	DoubleMatrix dummy = MatrixFunctions.pow(input.sub(mean), 2);
	double sd = dummy.sum() / (input.length - 1);
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
