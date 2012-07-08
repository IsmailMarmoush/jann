/*
 * Copyright 2012 Ismail Marmoush
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
package com.marmoush.jann;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.marmoush.jann.neuralgraph.INeuralDirectedGraphable;
import com.marmoush.jann.neuralgraph.NeuralDirectedGraph;
import com.marmoush.jann.utils.NetworkUtils;

// TODO: Auto-generated Javadoc
/**
 * 
 */
public class NeuralNetwork extends ArrayList<Layer> {

    /**
     * 
     */
    private static final long serialVersionUID = -3802547694705634188L;

    /**
     * 
     */
    private NeuralDirectedGraph neuralGraph = null;

    /**
     * 
     */
    public NeuralNetwork() {
	super();
    }

    /**
     * 
     * 
     * @param c
     */
    public NeuralNetwork(Collection<? extends Layer> c) {
	super(c);
    }

    /**
     * 
     * 
     * @param neuralGraph
     */
    public NeuralNetwork(INeuralDirectedGraphable neuralGraph) {
	init(neuralGraph);
    }

    /**
     * 
     * 
     * @param initialCapacity
     */
    public NeuralNetwork(int initialCapacity) {
	super(initialCapacity);
    }

    /**
     * 
     * 
     * @return
     */
    public NeuralDirectedGraph getNeuralGraph() {
	return neuralGraph;
    }

    /**
     * 
     * 
     * @param neuralGraph
     */
    public void init(INeuralDirectedGraphable neuralGraph) {
	int lyrIdx = 0;
	int nNeurons = 0;
	int nInputs = 0;
	Iterator<Integer> itr = neuralGraph.vertexSet().iterator();
	while (itr.hasNext()) {
	    lyrIdx = itr.next();
	    nInputs = neuralGraph.getInputLength(lyrIdx);
	    nNeurons = neuralGraph.getLayerNumOfNeurons(lyrIdx);
	    add(new Layer(nInputs, nNeurons, true));
	}
    }

    /**
     * 
     * 
     * @param neuralGraph
     */
    public void setNeuralGraph(NeuralDirectedGraph neuralGraph) {
	this.neuralGraph = neuralGraph;
    }

    /**
     * 
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
	    sb.append("Layer:" + i);
	    sb.append(this.get(i));
	    sb.append("\n");
	}
	return sb.toString();
    }
}
