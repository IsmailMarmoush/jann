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

import org.jblas.DoubleMatrix;
import com.marmoush.jann.model.regression.FeedForwardSvNet;
import com.marmoush.jann.train.TrainResult;
import com.marmoush.jann.train.TrainSvBPFF;
import com.marmoush.jann.utils.NetworkUtils;
import com.marmoush.jann.utils.functors.IPerformance;
import com.marmoush.jann.utils.functors.ITransfere;
import com.marmoush.jann.utils.functors.IWeight;

/**
 * The Class TestFeedForward.
 */
public class TestFeedForward {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {

		FeedForwardSvNet net = new FeedForwardSvNet(4, IWeight.DOTPROD,
				ITransfere.TANSIG, IPerformance.MSE, 3, 5, 2);
		NetworkUtils.setFillLearnRate(net, 0.1);
		NetworkUtils.setFillRandomMinMaxFloor(net, -1, 1);
		TrainSvBPFF train = new TrainSvBPFF(ITransfere.TANSIGDIFF, 0.01, 1000,
				10);
		TrainResult result = train.stochasticToLimits(net,
				DoubleMatrix.ones(4), DoubleMatrix.ones(2));
		System.out.println(net);
		System.out.println(result);
		System.out.println("Last layer performance"
				+ net.getOutputLayer().getPerformance());
	}
}
