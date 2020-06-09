package io.memoria.legacyjann.sv;

import io.memoria.legacyjann.neuralgraph.NeuralDirectedGraph;
import io.memoria.legacyjann.utils.NetworkUtils;
import org.jblas.DoubleMatrix;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class SvNeuralNetwork extends ArrayList<DefaultSvLayer> {

  private static final long serialVersionUID = 7960882092463170437L;

  private NeuralDirectedGraph neuralGraph = null;

  public SvNeuralNetwork() {
    super();
  }

  public SvNeuralNetwork(Collection<? extends DefaultSvLayer> c) {
    super(c);
  }

  public SvNeuralNetwork(int initialCapacity) {
    super(initialCapacity);
  }

  public SvNeuralNetwork(NeuralDirectedGraph neuralGraph) {
    init(neuralGraph);
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
      add(new DefaultSvLayer(nInputs, nNeurons, true));
    }
  }

  public DoubleMatrix getInputsConcat(int layerIdx) {
    return NetworkUtils.getInputsConcat(layerIdx, this, this.neuralGraph);
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
      sb.append("SvLayer:" + i);
      sb.append(this.get(i));
      sb.append("\n");
    }
    return sb.toString();
  }

}
