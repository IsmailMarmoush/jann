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
package com.marmoush.jann.utils;

import java.util.List;

import org.jblas.DoubleMatrix;

import com.marmoush.jann.ILayer;
import com.marmoush.jann.Layer;
import com.marmoush.jann.neuralgraph.NeuralDirectedGraph;
import com.marmoush.jann.sv.SvLayer;

// TODO: Auto-generated Javadoc
/**
 * The Class NetworkUtils.
 */
public class NetworkUtils {

    /**
     * Bpff.
     * 
     * @param net
     *            the net
     * @param ngraph
     *            the ngraph
     */
    public static void bpff(List<? extends Layer> net,
	    NeuralDirectedGraph ngraph) {

    }

    /**
     * Gets the inputs concatenated for layer.
     * 
     * @param layerIdx
     *            the layer idx
     * @param net
     *            the net
     * @param ngraph
     *            the ngraph
     * @return the inputs concatenated for layer
     */
    public static DoubleMatrix getInputsConcatenatedForLayer(int layerIdx,
	    List<? extends Layer> net, NeuralDirectedGraph ngraph) {
	List<Integer> predecessors = ngraph.getPredecessorsOf(layerIdx);
	// If there is only one predecessor layer
	if (predecessors.size() == 1) {
	    int predIdx = predecessors.get(0);
	    return net.get(predIdx).getOutput();
	}
	// If there is more than one Predecessor
	if (predecessors.size() > 1) {
	    // Get First predecessor output
	    int predLyrIdx = predecessors.get(0);
	    DoubleMatrix predLyrOutput = net.get(predLyrIdx).getOutput();
	    DoubleMatrix inputsConc = predLyrOutput;
	    // Concatenate the rest
	    for (int i = 1; i < predecessors.size(); i++) {
		predLyrIdx = predecessors.get(i);
		predLyrOutput = net.get(predLyrIdx).getOutput();
		inputsConc = DoubleMatrix.concatVertically(inputsConc,
			predLyrOutput);
	    }
	    return inputsConc;
	}
	return null;
    }

    /**
     * Sets the fill.
     * 
     * @param net
     *            the net
     * @param value
     *            the value
     */
    public static void setFill(List<? extends Layer> net, double value) {
	for (ILayer layer : net) {
	    // no need to checking the setfill doesn't fill a null matrix
	    // if (!layer.isInputOnlyLayer())
	    layer.setFill(value, layer.getInput(), layer.getBias(),
		    layer.getWeight());
	}
    }

    /**
     * Sets the fill learn rate.
     * 
     * @param net
     *            the net
     * @param lrnRate
     *            the lrn rate
     */
    public static void setFillLearnRate(List<? extends SvLayer> net,
	    double lrnRate) {
	for (SvLayer svLayer : net) {
	    svLayer.setLearnRate(lrnRate);
	}
    }

    /**
     * Sets the fill random.
     * 
     * @param net
     *            the new fill random
     */
    public static void setFillRandom(List<? extends Layer> net) {
	for (ILayer layer : net) {
	    layer.setFillRandom(layer.getInput(), layer.getBias(),
		    layer.getWeight());
	}
    }

    /**
     * Sets the fill random floor.
     * 
     * @param net
     *            the new fill random floor
     */
    public static void setFillRandomFloor(List<? extends Layer> net) {
	for (ILayer layer : net) {
	    layer.setFillRandomFloor(layer.getInput(), layer.getBias(),
		    layer.getWeight());
	}
    }

    /**
     * Sets the fill random min max.
     * 
     * @param net
     *            the net
     * @param min
     *            the min
     * @param max
     *            the max
     */
    public static void setFillRandomMinMax(List<? extends Layer> net,
	    double min, double max) {
	for (ILayer layer : net) {
	    layer.setFillRandomMinMax(min, max, layer.getInput(),
		    layer.getBias(), layer.getWeight());
	}
    }

    /**
     * Sets the fill random min max floor.
     * 
     * @param net
     *            the net
     * @param min
     *            the min
     * @param max
     *            the max
     */
    public static void setFillRandomMinMaxFloor(List<? extends Layer> net,
	    int min, int max) {
	for (ILayer layer : net) {
	    layer.setFillRandomMinMaxFloor(min, max, layer.getInput(),
		    layer.getBias(), layer.getWeight());
	}
    }

    /**
     * Simulate.
     * 
     * @param net
     *            the net
     * @param ngraph
     *            the ngraph
     */
    public static void simulate(List<? extends Layer> net,
	    NeuralDirectedGraph ngraph) {
	updateNetworkInput(net, ngraph);
	updateNetworkNetsum(net);
	/*
	 * Updating output isn't in the same loop because that would change the
	 * real output when somelayers are transfered before others the
	 * predecessorsInput would change
	 */
	updateNetworkOutput(net);
    }

    /**
     * Update network input.
     * 
     * @param net
     *            the net
     * @param ngraph
     *            the ngraph
     */
    public static void updateNetworkInput(List<? extends Layer> net,
	    NeuralDirectedGraph ngraph) {
	ILayer lyr = null;
	DoubleMatrix input = null;
	for (int i = 0; i < net.size(); i++) {
	    lyr = net.get(i);
	    input = NetworkUtils.getInputsConcatenatedForLayer(i, net, ngraph);
	    if (input != null)
		lyr.setInput(input);
	}
    }

    /**
     * Update network netsum.
     * 
     * @param net
     *            the net
     */
    public static void updateNetworkNetsum(List<? extends Layer> net) {
	for (ILayer lyr : net) {
	    lyr.updateNetSum();
	}
    }

    /**
     * Update network output.
     * 
     * @param net
     *            the net
     */
    public static void updateNetworkOutput(List<? extends Layer> net) {
	for (ILayer layer : net) {
	    layer.updateOutput();
	}
    }
}
