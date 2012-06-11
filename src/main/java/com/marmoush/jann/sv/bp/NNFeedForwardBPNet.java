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
package com.marmoush.jann.sv.bp;

import java.util.ArrayList;
import java.util.Collection;

import org.jblas.DoubleMatrix;

import com.marmoush.jann.sv.SvLayer;

/**
 * The Class NNFeedForwardBPNet.
 */
public class NNFeedForwardBPNet extends ArrayList<SvLayer> {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 5137385272017373938L;

    /**
     * Back propagate.
     * 
     * @param weight
     *            the weight
     * @param error
     *            the error
     * @return the double matrix
     */
    public static DoubleMatrix backPropagate(final DoubleMatrix weight,
	    final DoubleMatrix error) {
	return weight.transpose().mmul(error);
    }

    /**
     * Creates the.
     * 
     * @param inputLength
     *            the input length
     * @param lyrsNumOfNeurons
     *            the lyrs num of neurons
     * @return the nN feed forward bp net
     */
    public static NNFeedForwardBPNet create(final int inputLength,
	    final int... lyrsNumOfNeurons) {
	NNFeedForwardBPNet net = new NNFeedForwardBPNet(lyrsNumOfNeurons.length);
	int nInputs = inputLength;
	int nNeurons = lyrsNumOfNeurons[0];

	net.add(new SvLayer(inputLength, lyrsNumOfNeurons[1]));
	for (int i = 1; i < lyrsNumOfNeurons.length; i++) {
	    nInputs = lyrsNumOfNeurons[i - 1];
	    nNeurons = lyrsNumOfNeurons[i];
	    net.add(new SvLayer(nInputs, nNeurons));
	}
	return net;
    }

    /**
     * Delta rule bp.
     * 
     * @param inputTransposed
     *            the input transposed
     * @param error
     *            the error
     * @param diffOutput
     *            the diff output
     * @param learnRate
     *            the learn rate
     * @return the double matrix
     */
    public static DoubleMatrix deltaRuleBp(final DoubleMatrix inputTransposed,
	    final DoubleMatrix error, final DoubleMatrix diffOutput,
	    final double learnRate) {
	// DeltaWeight=learnRate * error * inputTransposed;
	return error.mul(diffOutput).mmul(inputTransposed).mul(learnRate);
    }

    /**
     * Instantiates a new nN feed forward bp net.
     */
    public NNFeedForwardBPNet() {
	super();
    }

    /**
     * Instantiates a new nN feed forward bp net.
     * 
     * @param c
     *            the c
     */
    public NNFeedForwardBPNet(final Collection<? extends SvLayer> c) {
	super(c);
    }

    /**
     * Instantiates a new nN feed forward bp net.
     * 
     * @param initialCapacity
     *            the initial capacity
     */
    public NNFeedForwardBPNet(final int initialCapacity) {
	super(initialCapacity);
    }

    /**
     * Sim.
     * 
     * @param newInput
     *            the new input
     * @return the double matrix
     */
    public DoubleMatrix sim(DoubleMatrix newInput) {
	int size = size();
	DoubleMatrix inAndOut = newInput;
	SvLayer lyr = null;
	for (int j = 0; j < size; j++) {
	    lyr = get(j);
	    lyr.setInput(inAndOut);
	    // inAndOut = lyr.sim(inAndOut);
	    lyr.setOutput(inAndOut);
	}
	return inAndOut;
    }

    /**
     * Train once.
     * 
     * @param newInput
     *            the new input
     * @param target
     *            the target
     */
    public void trainOnce(final DoubleMatrix newInput, final DoubleMatrix target) {
	// DoubleMatrix output = sim(newInput);
	// DoubleMatrix error = TeachingUtils.calcError(output, target);
	// get(size() - 1).setError(error);
	// netBackpropagate(error);
	// adapt();
    }

    /**
     * Adapt.
     */
    protected void adapt() {
	DoubleMatrix diffOutput = null;
	DoubleMatrix deltaWeight = null;
	DoubleMatrix deltaBias = null;
	for (SvLayer lyr : this) {
	    // diffOutput = TransfereFns.compute(lyr.getDIFF_TRANSFERE_FUN(),
	    // lyr.getNetsum(), 0);
	    deltaWeight = deltaRuleBp(lyr.getInput().transpose(),
		    lyr.getError(), diffOutput, lyr.getLearnRate());
	    lyr.getWeight().addi(deltaWeight);
	    if (lyr.getBias() != null) {
		deltaBias = lyr.getError().mul(diffOutput).mul(lyr.getBias());
		lyr.getBias().addi(deltaBias.mul(lyr.getLearnRate()));
	    }
	}
    }

    /**
     * Net backpropagate.
     * 
     * @param error
     *            the error
     */
    protected void netBackpropagate(final DoubleMatrix error) {
	int lastLayer = size() - 1;
	SvLayer lyr = null;
	SvLayer preLyr = null;
	for (int i = lastLayer; i > 0; i--) {
	    lyr = get(i);
	    preLyr = get(i - 1);
	    preLyr.setError(backPropagate(lyr.getWeight(), lyr.getError()));
	}
    }
}