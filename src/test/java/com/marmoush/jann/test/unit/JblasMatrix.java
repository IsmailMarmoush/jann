package com.marmoush.jann.test.unit;

import java.util.ArrayList;
import java.util.List;

import org.jblas.DoubleMatrix;

import com.marmoush.jann.utils.MatrixUtils;
import com.marmoush.jann.utils.TrainUtils;

public class JblasMatrix {
    public static void main(String[] args) {
	// DoubleMatrix m = DoubleMatrix.valueOf("8 1 6;3 5 7;4 9 2");
	// DoubleMatrix k = Solve.solve(m, DoubleMatrix.eye(3));
	// System.out.println(k);

	DoubleMatrix inputs = DoubleMatrix.valueOf("  0.3922 0.7060    0.0462;"
		+ "0.6555    0.0318    0.0971; 0.1712    0.2769    0.8235");
	DoubleMatrix targets = DoubleMatrix.valueOf("1;1; 0");

	DoubleMatrix weight = TrainUtils.NormalEq(inputs, targets);
	System.out.println(weight);

	
	List<DoubleMatrix> vecColList = new ArrayList<DoubleMatrix>();
	for (int i = 0; i < 10; i++) {
	    vecColList.add( DoubleMatrix.ones(2,1));
	}
	DoubleMatrix batch= MatrixUtils.colVecsList2BatchMtrx(vecColList);
	System.out.println(batch);
	
    }
}
