/**
 * 
 */
package com.marmoush.jann.test.unit.utils;

import static org.junit.Assert.*;

import org.jblas.DoubleMatrix;
import org.junit.Before;
import org.junit.Test;

import com.marmoush.jann.utils.MatrixUtils;

/**
 * @author marmoush
 * 
 */
public class MatrixUtilsTest {
    private DoubleMatrix mtrx;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
	mtrx = DoubleMatrix
		.valueOf("1.23456 12.3456 123.45678 1234.5678 12345.678");
    }

    /**
     * Test method for
     * {@link com.marmoush.jann.utils.MatrixUtils#round(org.jblas.DoubleMatrix, int)}
     * .
     */
    @Test
    public void testRound() {
	MatrixUtils.round(mtrx, 2);
	DoubleMatrix testResult = DoubleMatrix
		.valueOf("1.23 12.35 123.46 1234.57 12345.68");
	assertTrue(mtrx.toString(), mtrx.equals(testResult));
    }

    @Test
    public void testFeatureScaling() {
	DoubleMatrix m =DoubleMatrix.valueOf("2 4 4 4 5 5 7 9");
	System.out.println(m);
	System.out.println(MatrixUtils.featureScalingByAvrg(m));
    }

    @Test
    public void testStandardDeviation() {
	DoubleMatrix m = DoubleMatrix.valueOf("2 4 4 4 5 5 7 9");
	System.out.println(m);
	System.out.println(MatrixUtils.standardDeviation(m));
	assertTrue(MatrixUtils.standardDeviation(m)==2);
    }
}
