package io.memoria.jann.utils.functors;

import io.memoria.jann.Layer;
import io.memoria.jann.utils.WeightUtils;
import org.jblas.DoubleMatrix;

public interface IWeight extends IFunctionable {

  public static final IWeight BATCH_DOTPROD = new IWeight() {
    @Override
    public String toString() {
      return "BATCH_DOTPROD";
    }

    @Override
    public DoubleMatrix weightFn(Layer layer) {
      if (layer.isBiased())
        return WeightUtils.batchDotprod(layer.getInput(), layer.getBias(), layer.getWeight());
      else
        return WeightUtils.batchDotprod(layer.getInput(), layer.getWeight());
    }
  };

  public static final IWeight DOTPROD = new IWeight() {
    @Override
    public String toString() {
      return "DOTPROD";
    }

    @Override
    public DoubleMatrix weightFn(Layer layer) {
      if (layer.isBiased())
        return WeightUtils.dotprod(layer.getInput(), layer.getBias(), layer.getWeight());
      else
        return WeightUtils.dotprod(layer.getInput(), layer.getWeight());
    }
  };

  public DoubleMatrix weightFn(Layer layer);
}
