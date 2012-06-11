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

import com.marmoush.jann.sv.SvLayer;

/**
 * The Class NewNetsUtils.
 */
public class NewNetsUtils {

    /**
     * Creates the perceptron.
     * 
     * @param input
     *            the input
     * @param nNeurons
     *            the n neurons
     * @return the sv layer
     */
    public static SvLayer createPerceptron(DoubleMatrix input, int nNeurons) {
	SvLayer p = new SvLayer(input.length, nNeurons);
	p.setInput(input);
	return p;
    }

    /**
     * Creates the perceptron.
     * 
     * @param nInputs
     *            the n inputs
     * @param nNeurons
     *            the n neurons
     * @return the sv layer
     */
    public static SvLayer createPerceptron(int nInputs, int nNeurons) {
	return new SvLayer(nInputs, nNeurons);
    }

}
