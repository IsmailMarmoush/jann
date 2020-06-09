package io.memoria.legacyjann.test.integration;

import io.memoria.legacyjann.NeuralNetwork;
import io.memoria.legacyjann.neuralgraph.NeuralDirectedGraph;
import io.memoria.legacyjann.utils.NetworkUtils;
import org.jblas.DoubleMatrix;

public class GraphNetworkTest {

  public static void main(String[] args) {
    NeuralDirectedGraph ngraph = new NeuralDirectedGraph(3, 4, 2);
    ngraph.addEdge(0, 1);
    ngraph.addEdge(1, 2);
    NeuralNetwork net = new NeuralNetwork(ngraph);
    net.get(0).setOutput(DoubleMatrix.ones(3));
    NetworkUtils.setFill(net, 1);
    System.out.println(net);
    NetworkUtils.simulate(net, ngraph);
    System.out.println(net);
  }
}
