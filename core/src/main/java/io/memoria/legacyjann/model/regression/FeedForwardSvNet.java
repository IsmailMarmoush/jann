package io.memoria.legacyjann.model.regression;

import io.memoria.legacyjann.neuralgraph.NeuralDirectedGraph;
import io.memoria.legacyjann.sv.DefaultSvLayer;
import io.memoria.legacyjann.sv.SvNeuralNetwork;
import io.memoria.legacyjann.utils.functors.IPerformance;
import io.memoria.legacyjann.utils.functors.ITransfere;
import io.memoria.legacyjann.utils.functors.IWeight;
import org.jblas.DoubleMatrix;

public class FeedForwardSvNet extends SvNeuralNetwork {

  private static final long serialVersionUID = 8103514276278385409L;

  public FeedForwardSvNet(int inputSize,
                          IWeight weightFnctr,
                          ITransfere transFnctr,
                          IPerformance performanceFnctr,
                          int... nNeuronsPerLayer) {
    super();
    this.init(inputSize, weightFnctr, transFnctr, performanceFnctr, nNeuronsPerLayer);
  }

  public void init(int inputSize,
                   IWeight weightFnctr,
                   ITransfere transFnctr,
                   IPerformance perfFnctr,
                   int... nNeuronsPerLayer) {
    NeuralDirectedGraph graph = new NeuralDirectedGraph(nNeuronsPerLayer);
    graph.connectLayersAsFF();
    super.init(graph);
    this.getInputLayer().setInput(new DoubleMatrix(inputSize));
    this.getInputLayer().setWeight(new DoubleMatrix(nNeuronsPerLayer[0], inputSize));
  }

  public DefaultSvLayer getInputLayer() {
    return this.get(0);
  }

  public void setInput(DoubleMatrix input) {
    this.getInputLayer().setInput(input);
  }

  public void setTarget(DoubleMatrix target) {
    this.getOutputLayer().setTarget(target);
  }

  public DefaultSvLayer getOutputLayer() {
    return this.get(this.size() - 1);
  }
}
