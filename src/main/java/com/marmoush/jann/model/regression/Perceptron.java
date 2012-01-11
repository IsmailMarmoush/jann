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
package com.marmoush.jann.model.regression;

import com.marmoush.jann.sv.SvLayer;
import com.marmoush.jann.utils.functors.IPerformance;
import com.marmoush.jann.utils.functors.IWeight;

/**
 * The Class Perceptron.
 */
public class Perceptron extends SvLayer {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1709637643397682236L;

	/**
	 * Instantiates a new perceptron.
	 * 
	 * @param nInputs
	 *            the n inputs
	 * @param nNeurons
	 *            the n neurons
	 */
	public Perceptron(int nInputs, int nNeurons) {
		super(nInputs, nNeurons);
		this.setPerformancefnctr(IPerformance.MSE);
		this.setWeightFnctr(IWeight.DOTPROD);
	}

}
