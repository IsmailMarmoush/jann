package io.memoria.jann;

import java.io.Serializable;

import io.memoria.jann.utils.MatrixUtils;
import io.memoria.jann.utils.functors.ITransfere;
import io.memoria.jann.utils.functors.IWeight;
import org.jblas.DoubleMatrix;

public class DefaultLayer implements Serializable, Layer, FillableLayer {

  private static final long serialVersionUID = 59396693200260159L;

  private DoubleMatrix bias = null;

  private boolean biased = false;

  private DoubleMatrix input = null;

  private boolean inputOnlyLayer = false;

  private DoubleMatrix netSum = null;

  private DoubleMatrix output = null;

  private double theta = 0;

  private ITransfere transfereFnctr = ITransfere.PURELIN;

  private DoubleMatrix weight = null;

  private IWeight weightFnctr = null;

  public DefaultLayer() {
    super();
  }

  public DefaultLayer(final int nInputs, final int nNeurons, boolean biased) {
    input = new DoubleMatrix(nInputs);
    weight = new DoubleMatrix(nNeurons, nInputs);
    if (biased) {
      setBiased(true);
      bias = new DoubleMatrix(nNeurons);
    }
    netSum = new DoubleMatrix(nNeurons);
    output = new DoubleMatrix(nNeurons);

  }

  @Override
  public DoubleMatrix getBias() {
    return bias;
  }

  @Override
  public void setBias(DoubleMatrix bias) {
    if (biased)
      this.bias = bias;
  }

  @Override
  public DoubleMatrix getInput() {
    return input;
  }

  @Override
  public void setInput(DoubleMatrix input) {
    this.input = input;
  }

  @Override
  public DoubleMatrix getNetSum() {
    return netSum;
  }

  @Override
  public void setNetSum(DoubleMatrix netsum) {
    this.netSum = netsum;
  }

  @Override
  public DoubleMatrix getOutput() {
    return output;
  }

  @Override
  public void setOutput(DoubleMatrix output) {
    this.output = output;
  }

  @Override
  public double getTheta() {
    return theta;
  }

  @Override
  public void setTheta(double theta) {
    this.theta = theta;
  }

  @Override
  public ITransfere getTransfereFnctr() {
    return transfereFnctr;
  }

  @Override
  public void setTransfereFnctr(ITransfere transfereFnctr) {
    this.transfereFnctr = transfereFnctr;
  }

  @Override
  public DoubleMatrix getWeight() {
    return weight;
  }

  @Override
  public void setWeight(DoubleMatrix weight) {
    this.weight = weight;
  }

  /*
   * (non-Javadoc)
   * @see com.marmoush.jann.IFillableLayer#setFill(double,
   * org.jblas.DoubleMatrix)
   */

  @Override
  public IWeight getWeightFnctr() {
    return weightFnctr;
  }

  /*
   * (non-Javadoc)
   * @see
   * com.marmoush.jann.IFillableLayer#setFillRandom(org.jblas.DoubleMatrix)
   */

  @Override
  public void setWeightFnctr(IWeight weightFnctr) {
    this.weightFnctr = weightFnctr;
  }

  /*
   * (non-Javadoc)
   * @see
   * com.marmoush.jann.IFillableLayer#setFillRandomFloor(org.jblas.DoubleMatrix
   * [])
   */

  @Override
  public boolean isBiased() {
    return biased;
  }

  /*
   * (non-Javadoc)
   * @see com.marmoush.jann.IFillableLayer#setFillRandom(int, int,
   * org.jblas.DoubleMatrix)
   */

  @Override
  public boolean isInputOnlyLayer() {
    return this.inputOnlyLayer;

  }

  /*
   * (non-Javadoc)
   * @see com.marmoush.jann.IFillableLayer#setFillRandomMinMaxFloor(int, int,
   * org.jblas.DoubleMatrix[])
   */

  public void setInputOnlyLayer(boolean inputOnlyLayer) {
    this.inputOnlyLayer = inputOnlyLayer;
  }

  @Override
  public void setFill(double value, DoubleMatrix... matrices) {
    for (DoubleMatrix mtrx : matrices) {
      mtrx.fill(value);
    }
  }

  @Override
  public void setFillRandom(DoubleMatrix... matrices) {
    MatrixUtils.setFillRandom(matrices);
  }

  @Override
  public void simulate() {
    updateNetSum();
    updateOutput();
  }

  ;

  @Override
  public DoubleMatrix updateNetSum() {
    if (!isInputOnlyLayer())
      setNetSum(weightFnctr.weightFn(this));
    return netSum;
  }

  @Override
  public DoubleMatrix updateOutput() {
    if (!isInputOnlyLayer())
      setOutput(transfereFnctr.transfere(this));
    return output;
  }

  /*
   * (non-Javadoc)
   * @see
   * com.marmoush.jann.ILayer#setTransfereFnctr(com.marmoush.jann.utils.functors
   * .ITransfere)
   */

  public void setBiased(boolean biased) {
    this.biased = biased;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((bias == null) ? 0 : bias.hashCode());
    result = prime * result + ((input == null) ? 0 : input.hashCode());
    result = prime * result + ((netSum == null) ? 0 : netSum.hashCode());
    result = prime * result + ((output == null) ? 0 : output.hashCode());
    long temp;
    temp = Double.doubleToLongBits(theta);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    result = prime * result + ((weight == null) ? 0 : weight.hashCode());
    return result;
  }

  /*
   * (non-Javadoc)
   * @see
   * com.marmoush.jann.ILayer#setWeightFnctr(com.marmoush.jann.utils.functors
   * .IWeight)
   */

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    DefaultLayer other = (DefaultLayer) obj;
    if (bias == null) {
      if (other.bias != null)
        return false;
    } else
      if (!bias.equals(other.bias))
        return false;
    if (input == null) {
      if (other.input != null)
        return false;
    } else
      if (!input.equals(other.input))
        return false;
    if (netSum == null) {
      if (other.netSum != null)
        return false;
    } else
      if (!netSum.equals(other.netSum))
        return false;
    if (output == null) {
      if (other.output != null)
        return false;
    } else
      if (!output.equals(other.output))
        return false;
    if (Double.doubleToLongBits(theta) != Double.doubleToLongBits(other.theta))
      return false;
    if (weight == null) {
      if (other.weight != null)
        return false;
    } else
      if (!weight.equals(other.weight))
        return false;
    return true;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("\nbias=");
    builder.append(bias);
    builder.append(" \ninput=");
    builder.append(input);
    builder.append(" \nnetSum=");
    builder.append(netSum);
    builder.append(" \noutput=");
    builder.append(output);
    builder.append(" \ntheta=");
    builder.append(theta);
    builder.append(" \ntransfereFnctr=");
    builder.append(transfereFnctr);
    builder.append(" \nweight=");
    builder.append(weight);
    builder.append(" \nweightFnctr=");
    builder.append(weightFnctr);
    return builder.toString();
  }

  @Override
  public void setFillRandomFloor(DoubleMatrix... matrices) {
    MatrixUtils.setFillRandomFloor(matrices);
  }

  @Override
  public void setFillRandomMinMax(double min, double max, DoubleMatrix... matrices) {
    MatrixUtils.setFillRandomMinMax(min, max, matrices);
  }

  @Override
  public void setFillRandomMinMaxFloor(int min, int max, DoubleMatrix... matrices) {
    MatrixUtils.setFillRandomMinMaxFloor(min, max, matrices);
  }
}
