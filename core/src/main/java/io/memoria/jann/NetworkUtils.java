package io.memoria.jann;

import io.memoria.legacyjann.Layer;
import io.memoria.legacyjann.neuralgraph.NeuralDirectedGraphable;
import io.memoria.legacyjann.sv.SvLayer;
import org.jblas.DoubleMatrix;

import java.util.List;

public class NetworkUtils {

  public static void bpff(List<? extends Layer> net, NeuralDirectedGraph ngraph) {

  }

  public static Layer[] getPredecessors(int idx, List<? extends Layer> net, NeuralDirectedGraphable graph) {
    List<Integer> predIndices = graph.getPredecessorsOf(idx);
    int size = predIndices.size();
    Layer[] layers = new Layer[size];
    for (int i = 0; i < size; i++) {
      layers[i] = net.get(predIndices.get(i));
    }
    return layers;
  }

  public static void setFill(List<? extends Layer> net, double value) {
    for (Layer layer : net) {
      // no need to checking the setfill doesn't fill a null matrix
      // if (!layer.isInputOnlyLayer())
      layer.setFill(value, layer.getInput(), layer.getBias(), layer.getWeight());
    }
  }

  public static void setFillLearnRate(List<? extends SvLayer> net, double lrnRate) {
    for (SvLayer svLayer : net) {
      svLayer.setLearnRate(lrnRate);
    }
  }

  public static void setFillRandom(List<? extends Layer> net) {
    for (Layer layer : net) {
      layer.setFillRandom(layer.getInput(), layer.getBias(), layer.getWeight());
    }
  }

  public static void setFillRandomFloor(List<? extends Layer> net) {
    for (Layer layer : net) {
      layer.setFillRandomFloor(layer.getInput(), layer.getBias(), layer.getWeight());
    }
  }

  public static void setFillRandomMinMax(List<? extends Layer> net, double min, double max) {
    for (Layer layer : net) {
      layer.setFillRandomMinMax(min, max, layer.getInput(), layer.getBias(), layer.getWeight());
    }
  }

  public static void setFillRandomMinMaxFloor(List<? extends Layer> net, int min, int max) {
    for (Layer layer : net) {
      layer.setFillRandomMinMaxFloor(min, max, layer.getInput(), layer.getBias(), layer.getWeight());
    }
  }

  public static void simulate(List<? extends Layer> net, NeuralDirectedGraph ngraph) {
    updateNetworkInput(net, ngraph);
    updateNetworkNetsum(net);
    /*
     * Updating output isn't in the same loop because that would change the
     * real output when somelayers are transfered before others the
     * predecessorsInput would change
     */
    updateNetworkOutput(net);
  }

  public static void updateNetworkInput(List<? extends Layer> net, NeuralDirectedGraph ngraph) {
    Layer lyr = null;
    DoubleMatrix input = null;
    for (int i = 0; i < net.size(); i++) {
      lyr = net.get(i);
      input = NetworkUtils.getInputsConcat(i, net, ngraph);
      if (input != null)
        lyr.setInput(input);
    }
  }

  public static void updateNetworkNetsum(List<? extends Layer> net) {
    for (Layer lyr : net) {
      lyr.updateNetSum();
    }
  }

  public static void updateNetworkOutput(List<? extends Layer> net) {
    for (Layer layer : net) {
      layer.updateOutput();
    }
  }

  public static DoubleMatrix getInputsConcat(int layerIdx, List<? extends Layer> net, NeuralDirectedGraphable graph) {

    return null;
  }
}
