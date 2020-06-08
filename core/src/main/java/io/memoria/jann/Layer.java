package io.memoria.jann;

import io.memoria.jann.utils.functors.ITransfere;
import io.memoria.jann.utils.functors.IWeight;
import org.jblas.DoubleMatrix;

public interface Layer extends FillableLayer {

  public abstract DoubleMatrix getBias();

  public abstract void setBias(DoubleMatrix bias);

  public abstract DoubleMatrix getInput();

  public abstract void setInput(DoubleMatrix input);

  public abstract DoubleMatrix getNetSum();

  public abstract void setNetSum(DoubleMatrix netsum);

  public abstract DoubleMatrix getOutput();

  public abstract void setOutput(DoubleMatrix output);

  public abstract double getTheta();

  public abstract void setTheta(double theta);

  public abstract ITransfere getTransfereFnctr();

  public abstract void setTransfereFnctr(ITransfere transfereFnctr);

  public abstract DoubleMatrix getWeight();

  /*
   * (non-Javadoc)
   * @see com.marmoush.jann.IFillableLayer#setFill(double,
   * org.jblas.DoubleMatrix[])
   */

  public abstract void setWeight(DoubleMatrix weight);

  /*
   * (non-Javadoc)
   * @see
   * com.marmoush.jann.IFillableLayer#setFillRandom(org.jblas.DoubleMatrix[])
   */

  public abstract IWeight getWeightFnctr();

  public abstract void setWeightFnctr(IWeight weightFnctr);

  @Override
  public abstract int hashCode();

  @Override
  public abstract boolean equals(Object obj);

  @Override
  public abstract String toString();

  public abstract boolean isBiased();

  public abstract boolean isInputOnlyLayer();

  @Override
  public abstract void setFill(double value, DoubleMatrix... matrices);

  @Override
  public abstract void setFillRandom(DoubleMatrix... matrices);

  public abstract void simulate();

  public abstract DoubleMatrix updateNetSum();

  public abstract DoubleMatrix updateOutput();

}
