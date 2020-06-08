package io.memoria.jann.test.integration;

import io.memoria.jann.NeuralNetwork;
import io.memoria.jann.neuralgraph.NeuralDirectedGraph;
import io.memoria.jann.utils.NetworkUtils;
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
