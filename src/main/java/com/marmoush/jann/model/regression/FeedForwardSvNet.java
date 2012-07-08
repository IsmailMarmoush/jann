/*
 * Copyright 2011 Ismail Marmoush This file is part of JANN. JANN is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * General Public License Version 3 as published by the Free Software
 * Foundation, either version 3 of the License, or any later version. JANN is
 * distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details. You
 * should have received a copy of the GNU General Public License along with
 * JANN. If not, see http://www.gnu.org/licenses/. For More Information Please
 * Visit http://jann.marmoush.com
 */
package com.marmoush.jann.model.regression;

import org.jblas.DoubleMatrix;

import com.marmoush.jann.neuralgraph.NeuralDirectedGraph;
import com.marmoush.jann.sv.SvLayer;
import com.marmoush.jann.sv.SvNeuralNetwork;
import com.marmoush.jann.utils.functors.IPerformance;
import com.marmoush.jann.utils.functors.ITransfere;
import com.marmoush.jann.utils.functors.IWeight;

public class FeedForwardSvNet extends SvNeuralNetwork {

    private static final long serialVersionUID = 8103514276278385409L;

    /**
     * @param inputSize
     * @param weightFnctr
     * @param transFnctr
     * @param performanceFnctr
     * @param nNeuronsPerLayer
     */
    public FeedForwardSvNet(int inputSize, IWeight weightFnctr,
	    ITransfere transFnctr, IPerformance performanceFnctr,
	    int... nNeuronsPerLayer) {
	super();
	this.init(inputSize, weightFnctr, transFnctr, performanceFnctr,
		nNeuronsPerLayer);
    }

    public SvLayer getInputLayer() {
	return this.get(0);
    }

    public SvLayer getOutputLayer() {
	return this.get(this.size() - 1);
    }

    /**
     * @param inputSize
     * @param weightFnctr
     * @param transFnctr
     * @param perfFnctr
     * @param nNeuronsPerLayer
     */
    public void init(int inputSize, IWeight weightFnctr, ITransfere transFnctr,
	    IPerformance perfFnctr, int... nNeuronsPerLayer) {
	NeuralDirectedGraph graph = new NeuralDirectedGraph(nNeuronsPerLayer);
	graph.connectLayersAsFF();
	super.init(graph);
	this.getInputLayer().setInput(new DoubleMatrix(inputSize));
	this.getInputLayer().setWeight(
		new DoubleMatrix(nNeuronsPerLayer[0], inputSize));
    }

    public void setInput(DoubleMatrix input) {
	this.getInputLayer().setInput(input);
    }

    public void setTarget(DoubleMatrix target) {
	this.getOutputLayer().setTarget(target);
    }
}