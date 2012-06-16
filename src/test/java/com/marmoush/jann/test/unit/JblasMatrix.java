package com.marmoush.jann.test.unit;

import org.jblas.DoubleMatrix;
import org.junit.Test;

import com.marmoush.jann.utils.MatrixUtils;

public class JblasMatrix {
    @Test
    public void rank() {
	DoubleMatrix r = DoubleMatrix
		.valueOf("16 2 3 13;5 11 10 8;9 7 6 12;4 14 15 1");
	System.out.println(MatrixUtils.rank(r));
    }
    @Test
    public void pinv(){
	DoubleMatrix r = DoubleMatrix
		.valueOf("16 2 3 13;5 11 10 8;9 7 6 12;4 14 15 1");
	DoubleMatrix inv=MatrixUtils.pinv(r);
	System.out.println(inv);
    }
    @Test
    public void dum() {
	// DoubleMatrix m = DoubleMatrix.valueOf("8 1 6;3 5 7;4 9 2");
	// DoubleMatrix k = Solve.solve(m, DoubleMatrix.eye(3));
	// System.out.println(k);

	// DoubleMatrix inputs =
	// DoubleMatrix.valueOf("  0.3922 0.7060    0.0462;"
	// + "0.6555    0.0318    0.0971; 0.1712    0.2769    0.8235");
	// DoubleMatrix targets = DoubleMatrix.valueOf("1;1; 0");
	//
	// DoubleMatrix weight = TrainUtils.NormalEq(inputs, targets);
	// System.out.println(weight);

	//
	// List<DoubleMatrix> vecColList = new ArrayList<DoubleMatrix>();
	// for (int i = 0; i < 10; i++) {
	// vecColList.add( DoubleMatrix.ones(2,1));
	// }
	// DoubleMatrix batch= MatrixUtils.colVecsList2BatchMtrx(vecColList);
	// System.out.println(batch);

    }

}
