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
package com.marmoush.jann.test.integration;

import org.jblas.DoubleMatrix;

import com.marmoush.jann.neuralgraph.NeuralDirectedGraph;
import com.marmoush.jann.sv.SvNeuralNetwork;
import com.marmoush.jann.utils.NetworkUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class TestBp.
 */
public class TestBp
{

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args)
    {
	NeuralDirectedGraph ngraph = new NeuralDirectedGraph(3, 4, 2);
	ngraph.addEdge(0, 1);
	ngraph.addEdge(1, 2);
	SvNeuralNetwork net = new SvNeuralNetwork(ngraph);
	net.get(0).setOutput(DoubleMatrix.ones(3));
	NetworkUtils.setFillRandom(net);
	System.out.println(net);
	// NetworkUtils.simulate(net, ngraph);
	// System.out.println(net);
    }
}
