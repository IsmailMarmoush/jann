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
package com.marmoush.jann.sv;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.jblas.DoubleMatrix;

import com.marmoush.jann.neuralgraph.NeuralDirectedGraph;
import com.marmoush.jann.utils.NetworkUtils;

/**
 * The Class SvNeuralNetwork.
 */
public class SvNeuralNetwork extends ArrayList<SvLayer> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7960882092463170437L;

	/** The neural graph. */
	private NeuralDirectedGraph neuralGraph = null;

	/**
	 * Instantiates a new sv neural network.
	 */
	public SvNeuralNetwork() {
		super();
	}

	/**
	 * Instantiates a new sv neural network.
	 * 
	 * @param c
	 *            the c
	 */
	public SvNeuralNetwork(Collection<? extends SvLayer> c) {
		super(c);
	}

	/**
	 * Instantiates a new sv neural network.
	 * 
	 * @param initialCapacity
	 *            the initial capacity
	 */
	public SvNeuralNetwork(int initialCapacity) {
		super(initialCapacity);
	}

	/**
	 * Instantiates a new sv neural network.
	 * 
	 * @param neuralGraph
	 *            the neural graph
	 */
	public SvNeuralNetwork(NeuralDirectedGraph neuralGraph) {
		init(neuralGraph);
	}

	/**
	 * Gets the inputs concatenated for layer.
	 * 
	 * @param layerIdx
	 *            the layer idx
	 * @return the inputs concatenated for layer
	 */
	public DoubleMatrix getInputsConcatenatedForLayer(int layerIdx) {
		return NetworkUtils.getInputsConcatenatedForLayer(layerIdx, this,
				this.neuralGraph);
	}

	/**
	 * Gets the neural graph.
	 * 
	 * @return the neural graph
	 */
	public NeuralDirectedGraph getNeuralGraph() {
		return neuralGraph;
	}

	/**
	 * Inits the.
	 * 
	 * @param neuralGraph
	 *            the neural graph
	 */
	public void init(NeuralDirectedGraph neuralGraph) {
		this.neuralGraph = neuralGraph;
		int lyrIdx = 0;
		int nNeurons = 0;
		int nInputs = 0;

		Iterator<Integer> itr = neuralGraph.vertexSet().iterator();
		while (itr.hasNext()) {
			lyrIdx = itr.next();
			nInputs = neuralGraph.getInputLength(lyrIdx);
			nNeurons = neuralGraph.getLayerNumOfNeurons(lyrIdx);
			add(new SvLayer(nInputs, nNeurons));
		}
	}

	/**
	 * Sets the neural graph.
	 * 
	 * @param neuralGraph
	 *            the new neural graph
	 */
	public void setNeuralGraph(NeuralDirectedGraph neuralGraph) {
		this.neuralGraph = neuralGraph;
	}

	/**
	 * Simulate.
	 */
	public void simulate() {
		NetworkUtils.simulate(this, this.neuralGraph);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.AbstractCollection#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < this.size(); i++) {
			sb.append("SvLayer:" + i);
			sb.append(this.get(i));
			sb.append("\n");
		}
		return sb.toString();
	}

}
