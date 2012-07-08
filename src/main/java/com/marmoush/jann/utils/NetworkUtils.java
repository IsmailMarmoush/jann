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
package com.marmoush.jann.utils;

import java.util.List;

import org.jblas.DoubleMatrix;

import com.marmoush.jann.ILayer;
import com.marmoush.jann.neuralgraph.INeuralDirectedGraphable;
import com.marmoush.jann.neuralgraph.NeuralDirectedGraph;
import com.marmoush.jann.sv.ISvLayer;

public class NetworkUtils {

    /**
     * @param net
     * @param ngraph
     */
    public static void bpff(List<? extends ILayer> net,
	    NeuralDirectedGraph ngraph) {

    }

    /**
     * @param layerIdx
     * @param net
     * @param graph
     * @return
     */
    public static DoubleMatrix getInputsConcat(int layerIdx,
	    List<? extends ILayer> net, INeuralDirectedGraphable graph) {

	return null;
    }

    /**
     * @param idx
     * @param net
     * @param graph
     * @return
     */
    public static ILayer[] getPredecessors(int idx, List<? extends ILayer> net,
	    INeuralDirectedGraphable graph) {
	List<Integer> predIndices = graph.getPredecessorsOf(idx);
	int size = predIndices.size();
	ILayer[] layers = new ILayer[size];
	for (int i = 0; i < size; i++) {
	    layers[i] = net.get(predIndices.get(i));
	}
	return layers;
    }

    /**
     * @param net
     * @param value
     */
    public static void setFill(List<? extends ILayer> net, double value) {
	for (ILayer layer : net) {
	    // no need to checking the setfill doesn't fill a null matrix
	    // if (!layer.isInputOnlyLayer())
	    layer.setFill(value, layer.getInput(), layer.getBias(),
		    layer.getWeight());
	}
    }

    /**
     * @param net
     * @param lrnRate
     */
    public static void setFillLearnRate(List<? extends ISvLayer> net,
	    double lrnRate) {
	for (ISvLayer iSvLayer : net) {
	    iSvLayer.setLearnRate(lrnRate);
	}
    }

    
    public static void setFillRandom(List<? extends ILayer> net) {
	for (ILayer layer : net) {
	    layer.setFillRandom(layer.getInput(), layer.getBias(),
		    layer.getWeight());
	}
    }

    
    public static void setFillRandomFloor(List<? extends ILayer> net) {
	for (ILayer layer : net) {
	    layer.setFillRandomFloor(layer.getInput(), layer.getBias(),
		    layer.getWeight());
	}
    }

    /**
     * @param net
     * @param min
     * @param max
     */
    public static void setFillRandomMinMax(List<? extends ILayer> net,
	    double min, double max) {
	for (ILayer layer : net) {
	    layer.setFillRandomMinMax(min, max, layer.getInput(),
		    layer.getBias(), layer.getWeight());
	}
    }

    /**
     * @param net
     * @param min
     * @param max
     */
    public static void setFillRandomMinMaxFloor(List<? extends ILayer> net,
	    int min, int max) {
	for (ILayer layer : net) {
	    layer.setFillRandomMinMaxFloor(min, max, layer.getInput(),
		    layer.getBias(), layer.getWeight());
	}
    }

    /**
     * @param net
     * @param ngraph
     */
    public static void simulate(List<? extends ILayer> net,
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
     * @param net
     * @param ngraph
     */
    public static void updateNetworkInput(List<? extends ILayer> net,
	    NeuralDirectedGraph ngraph) {
	ILayer lyr = null;
	DoubleMatrix input = null;
	for (int i = 0; i < net.size(); i++) {
	    lyr = net.get(i);
	    input = NetworkUtils.getInputsConcat(i, net, ngraph);
	    if (input != null)
		lyr.setInput(input);
	}
    }

    
    public static void updateNetworkNetsum(List<? extends ILayer> net) {
	for (ILayer lyr : net) {
	    lyr.updateNetSum();
	}
    }

    
    public static void updateNetworkOutput(List<? extends ILayer> net) {
	for (ILayer layer : net) {
	    layer.updateOutput();
	}
    }
}
