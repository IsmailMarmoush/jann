package com.marmoush.jann.test;

import org.jblas.DoubleMatrix;

public class DummyTest {
    public static void main(String[] args) {
	DoubleMatrix ones=DoubleMatrix.ones(3);
	ones.addRowVector(DoubleMatrix.ones(1)).print();
	
    }
}
