/*
 * Copyright 2011 Ismail Marmoush This file is part of JANN. JANN is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * General Public License Version 3 as published by the Free Software
 * Foundation, either version 3 of the License, or any later version. JANN is
 * distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details. You
 * should have received a copy of the GNU General Public License along with
 * JANN. If not, see http://www.gnu.org/licenses/. For More Information Please
 * Visit http://jann.marmoush.com
 */
package io.memoria.jann.utils.functors;

import io.memoria.jann.utils.WeightUtils;
import org.jblas.DoubleMatrix;

import io.memoria.jann.Layer;

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
