package io.memoria.legacyjann;

import io.memoria.legacyjann.neuralgraph.NeuralDirectedGraph;
import io.memoria.legacyjann.neuralgraph.NeuralDirectedGraphable;
import io.memoria.legacyjann.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

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
