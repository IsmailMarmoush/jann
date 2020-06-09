package io.memoria.legacyjann.neuralgraph;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.List;

public interface NeuralDirectedGraphable extends Graph<Integer, DefaultEdge> {

  public List<Integer> getAllLayersNumOfNeurons();

  public void setAllLayersNumOfNeurons(List<Integer> nNeuronsPerLayer);

  public int getInputLength(final int layerIndex);

  public int getLayerNumOfNeurons(final int layerIndex);

  public List<Integer> getPredecessorsOf(final int layerIndex);

  public List<Integer> getSuccessorsOf(final int layerIndex);

}
