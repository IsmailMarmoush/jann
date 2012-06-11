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
package com.marmoush.jann.neuralgraph;

import java.util.List;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultEdge;

/**
 * The Interface INeuralDirectedGraphable.
 */
public interface INeuralDirectedGraphable extends
	DirectedGraph<Integer, DefaultEdge> {

    /**
     * Gets the all layers num of neurons.
     * 
     * @return the all layers num of neurons
     */
    public List<Integer> getAllLayersNumOfNeurons();

    /**
     * Gets the input length.
     * 
     * @param layerIndex
     *            the layer index
     * @return the input length
     */
    public int getInputLength(final int layerIndex);

    /**
     * Gets the layer num of neurons.
     * 
     * @param layerIndex
     *            the layer index
     * @return the layer num of neurons
     */
    public int getLayerNumOfNeurons(final int layerIndex);

    /**
     * Gets the predecessors of.
     * 
     * @param layerIndex
     *            the layer index
     * @return the predecessors of
     */
    public List<Integer> getPredecessorsOf(final int layerIndex);

    /**
     * Gets the successors of.
     * 
     * @param layerIndex
     *            the layer index
     * @return the successors of
     */
    public List<Integer> getSuccessorsOf(final int layerIndex);

    /**
     * Sets the all layers num of neurons.
     * 
     * @param nNeuronsPerLayer
     *            the new all layers num of neurons
     */
    public void setAllLayersNumOfNeurons(List<Integer> nNeuronsPerLayer);

}
