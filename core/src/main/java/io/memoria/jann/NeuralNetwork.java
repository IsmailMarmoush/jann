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
package io.memoria.jann;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import io.memoria.jann.neuralgraph.NeuralDirectedGraphable;
import io.memoria.jann.neuralgraph.NeuralDirectedGraph;
import io.memoria.jann.utils.NetworkUtils;

public class NeuralNetwork extends ArrayList<DefaultLayer> {

  private static final long serialVersionUID = -3802547694705634188L;

  private NeuralDirectedGraph neuralGraph = null;

  public NeuralNetwork() {
    super();
  }

  public NeuralNetwork(Collection<? extends DefaultLayer> c) {
    super(c);
  }

  public NeuralNetwork(NeuralDirectedGraphable neuralGraph) {
    init(neuralGraph);
  }

  public void init(NeuralDirectedGraphable neuralGraph) {
    int lyrIdx = 0;
    int nNeurons = 0;
    int nInputs = 0;
    Iterator<Integer> itr = neuralGraph.vertexSet().iterator();
    while (itr.hasNext()) {
      lyrIdx = itr.next();
      nInputs = neuralGraph.getInputLength(lyrIdx);
      nNeurons = neuralGraph.getLayerNumOfNeurons(lyrIdx);
      add(new DefaultLayer(nInputs, nNeurons, true));
    }
  }

  public NeuralNetwork(int initialCapacity) {
    super(initialCapacity);
  }

  public NeuralDirectedGraph getNeuralGraph() {
    return neuralGraph;
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
      sb.append("Layer:" + i);
      sb.append(this.get(i));
      sb.append("\n");
    }
    return sb.toString();
  }
}
