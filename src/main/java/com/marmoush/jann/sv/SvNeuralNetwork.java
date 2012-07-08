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
package com.marmoush.jann.sv;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.jblas.DoubleMatrix;

import com.marmoush.jann.neuralgraph.NeuralDirectedGraph;
import com.marmoush.jann.utils.NetworkUtils;

public class SvNeuralNetwork extends ArrayList<SvLayer> {

    private static final long serialVersionUID = 7960882092463170437L;

    private NeuralDirectedGraph neuralGraph = null;

    public SvNeuralNetwork() {
	super();
    }

    
    public SvNeuralNetwork(Collection<? extends SvLayer> c) {
	super(c);
    }

    
    public SvNeuralNetwork(int initialCapacity) {
	super(initialCapacity);
    }

    
    public SvNeuralNetwork(NeuralDirectedGraph neuralGraph) {
	init(neuralGraph);
    }

    /**
     * @param layerIdx
     * @return
     */
    public DoubleMatrix getInputsConcat(int layerIdx) {
	return NetworkUtils.getInputsConcat(layerIdx, this, this.neuralGraph);
    }

    public NeuralDirectedGraph getNeuralGraph() {
	return neuralGraph;
    }

    
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
	    add(new SvLayer(nInputs, nNeurons, true));
	}
    }

    
    public void setNeuralGraph(NeuralDirectedGraph neuralGraph) {
	this.neuralGraph = neuralGraph;
    }

    public void simulate() {
	NetworkUtils.simulate(this, this.neuralGraph);
    }

    

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
