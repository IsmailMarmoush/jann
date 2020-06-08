package io.memoria.jann.neuralgraph;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.util.ArrayList;
import java.util.List;

public class NeuralDirectedGraph extends DefaultDirectedGraph<Integer, DefaultEdge> implements NeuralDirectedGraphable {

  private static final long serialVersionUID = 8695054227504317702L;

  private List<Integer> layersNumOfNeurons = new ArrayList<Integer>();

  public NeuralDirectedGraph(int... nNeuronsPerLayer) {
    this();
    for (int i = 0; i < nNeuronsPerLayer.length; i++) {
      this.layersNumOfNeurons.add(nNeuronsPerLayer[i]);
      this.addVertex(i);
    }
  }

  public NeuralDirectedGraph() {
    super(DefaultEdge.class);
  }

  public NeuralDirectedGraph(List<Integer> nNeuronsPerLayer) {
    this();
    this.layersNumOfNeurons = nNeuronsPerLayer;
    for (int i = 0; i < nNeuronsPerLayer.size(); i++) {
      this.addVertex(i);
    }
  }

  public void clearConnections() {
    for (int i = 1; i < this.getNumOfLayers(); i++) {
      this.removeAllEdges(i, i);
      this.removeAllEdges(i - 1, i);
      this.removeAllEdges(i, i - 1);
    }

  }

  public int getNumOfLayers() {
    return this.layersNumOfNeurons.size();
  }

  /*
   * (non-Javadoc)
   * @see
   * com.marmoush.jann.neuralgraph.INeuralDirectedGraphable#getLayersNumOfNeurons
   * ()
   */

  public void connectLayersAsFF() {
    for (int i = 1; i < getNumOfLayers(); i++) {
      addEdge(i - 1, i);
    }
  }

  /*
   * (non-Javadoc)
   * @see
   * com.marmoush.jann.NeuralGraph.NeuralDirectedGraphable#getInputLength(int)
   */

  @Override
  public List<Integer> getAllLayersNumOfNeurons() {
    return layersNumOfNeurons;
  }

  /*
   * (non-Javadoc)
   * @see
   * com.marmoush.jann.NeuralGraph.NeuralDirectedGraphable#getNumOfNeuronsInLayer
   * (int)
   */

  @Override
  public void setAllLayersNumOfNeurons(List<Integer> nNeuronsPerLayer) {
    this.layersNumOfNeurons = nNeuronsPerLayer;
  }

  @Override
  public int getInputLength(final int lyrIdx) {
    int inputLengthSum = 0;
    List<Integer> predecessors = getPredecessorsOf(lyrIdx);
    for (int i = 0; i < predecessors.size(); i++) {
      inputLengthSum += layersNumOfNeurons.get(predecessors.get(i));
    }
    return inputLengthSum;
  }

  /*
   * (non-Javadoc)
   * @see
   * com.marmoush.jann.NeuralGraph.NeuralDirectedGraphable#getPredecessorsOf
   * (int)
   */

  @Override
  public int getLayerNumOfNeurons(final int lyrIdx) {
    return layersNumOfNeurons.get(lyrIdx);
  }

  /*
   * (non-Javadoc)
   * @see
   * com.marmoush.jann.NeuralGraph.NeuralDirectedGraphable#getSuccessorsOf
   * (int)
   */

  @Override
  public List<Integer> getPredecessorsOf(final int lyrIdx) {
    return Graphs.predecessorListOf(this, lyrIdx);
  }

  /*
   * (non-Javadoc)
   * @see
   * com.marmoush.jann.NeuralGraph.NeuralDirectedGraphable#setNumOfNeuronsInLayer
   * (java.util.List)
   */

  @Override
  public List<Integer> getSuccessorsOf(int lyrIdx) {
    return Graphs.successorListOf(this, lyrIdx);
  }
}
