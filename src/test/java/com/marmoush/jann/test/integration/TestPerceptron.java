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
package com.marmoush.jann.test.integration;

import java.util.ArrayList;
import java.util.List;

import org.jblas.DoubleMatrix;

import com.marmoush.jann.model.regression.Perceptron;
import com.marmoush.jann.train.TrainSvPerceptron;
import com.marmoush.jann.train.TrainResult;
import com.marmoush.jann.utils.MatrixUtils;

/**
 * The Class TestPerceptron.
 */
public class TestPerceptron {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {

		// Init input
		int inLength = 100;
		DoubleMatrix input = MatrixUtils.randomMatrixFloor(inLength, 1, 0, 1);
		System.out.println(input);
		List<DoubleMatrix> inputList = new ArrayList<DoubleMatrix>();
		inputList.add(input);
		inputList.add(input);
		inputList.add(input);

		// Init target
		int outLength = 100;
		DoubleMatrix target = MatrixUtils.randomMatrixFloor(outLength, 1, 0, 1);
		System.out.println(target);
		List<DoubleMatrix> targetList = new ArrayList<DoubleMatrix>();
		targetList.add(target);
		targetList.add(target);
		targetList.add(target);

		// Init Perceptron
		Perceptron p = new Perceptron(inLength, outLength);
		p.setFillRandom(p.getWeight());
		TrainSvPerceptron dr = new TrainSvPerceptron(0.1, 100, 20);
		TrainResult tr = dr.stochasticToLimits(p, inputList, targetList);
		System.out.println(p);
		System.out.println(tr);
	}
}
