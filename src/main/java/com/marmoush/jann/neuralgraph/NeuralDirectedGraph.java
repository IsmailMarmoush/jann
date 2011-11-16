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

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

// TODO: Auto-generated Javadoc
/**
 * The Class NeuralDirectedGraph.
 */
public class NeuralDirectedGraph extends
	DefaultDirectedGraph<Integer, DefaultEdge> implements
	INeuralDirectedGraphable
{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 8695054227504317702L;

    /** The layers num of neurons. */
    private List<Integer> layersNumOfNeurons = new ArrayList<Integer>();

    /**
     * Instantiates a new neural directed graph.
     */
    public NeuralDirectedGraph()
    {
	super(DefaultEdge.class);
    }

    /**
     * Instantiates a new neural directed graph.
     *
     * @param nNeuronsPerLayer the n neurons per layer
     */
    public NeuralDirectedGraph(int... nNeuronsPerLayer)
    {
	this();
	for (int i = 0; i < nNeuronsPerLayer.length; i++)
	{
	    this.layersNumOfNeurons.add(nNeuronsPerLayer[i]);
	    this.addVertex(i);
	}
    }

    /**
     * Clear connections.
     */
    public void clearConnections()
    {
	for (int i = 1; i < this.getNumOfLayers(); i++)
	{
	    this.removeAllEdges(i, i);
	    this.removeAllEdges(i - 1, i);
	    this.removeAllEdges(i, i - 1);
	}

    }

    /**
     * Connect layers as ff.
     */
    public void connectLayersAsFF()
    {
	for (int i = 1; i < getNumOfLayers(); i++)
	{
	    addEdge(i - 1, i);
	}
    }

    /**
     * Instantiates a new neural directed graph.
     *
     * @param nNeuronsPerLayer the n neurons per layer
     */
    public NeuralDirectedGraph(List<Integer> nNeuronsPerLayer)
    {
	this();
	this.layersNumOfNeurons = nNeuronsPerLayer;
	for (int i = 0; i < nNeuronsPerLayer.size(); i++)
	{
	    this.addVertex(i);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.marmoush.jann.neuralgraph.INeuralDirectedGraphable#getLayersNumOfNeurons
     * ()
     */
    @Override
    public List<Integer> getAllLayersNumOfNeurons()
    {
	return layersNumOfNeurons;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.marmoush.jann.NeuralGraph.NeuralDirectedGraphable#getInputLength(int)
     */
    @Override
    public int getInputLength(final int lyrIdx)
    {
	int inputLengthSum = 0;
	List<Integer> predecessors = getPredecessorsOf(lyrIdx);
	for (int i = 0; i < predecessors.size(); i++)
	{
	    inputLengthSum += layersNumOfNeurons.get(predecessors.get(i));
	}
	return inputLengthSum;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.marmoush.jann.NeuralGraph.NeuralDirectedGraphable#getNumOfNeuronsInLayer
     * (int)
     */
    @Override
    public int getLayerNumOfNeurons(final int lyrIdx)
    {
	return layersNumOfNeurons.get(lyrIdx);
    }

    /**
     * Gets the num of layers.
     *
     * @return the num of layers
     */
    public int getNumOfLayers()
    {
	return this.layersNumOfNeurons.size();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.marmoush.jann.NeuralGraph.NeuralDirectedGraphable#getPredecessorsOf
     * (int)
     */
    @Override
    public List<Integer> getPredecessorsOf(final int lyrIdx)
    {
	return Graphs.predecessorListOf(this, lyrIdx);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.marmoush.jann.NeuralGraph.NeuralDirectedGraphable#getSuccessorsOf
     * (int)
     */
    @Override
    public List<Integer> getSuccessorsOf(int lyrIdx)
    {
	return Graphs.successorListOf(this, lyrIdx);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.marmoush.jann.NeuralGraph.NeuralDirectedGraphable#setNumOfNeuronsInLayer
     * (java.util.List)
     */
    @Override
    public void setAllLayersNumOfNeurons(List<Integer> nNeuronsPerLayer)
    {
	this.layersNumOfNeurons = nNeuronsPerLayer;
    }
}
