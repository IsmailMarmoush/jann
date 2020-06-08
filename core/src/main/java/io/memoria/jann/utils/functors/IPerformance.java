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

import io.memoria.jann.sv.SvLayer;
import io.memoria.jann.utils.PerformanceUtils;

public interface IPerformance extends IFunctionable {

  public static final IPerformance LINRGR = new IPerformance() {
    @Override
    public double measurePerformance(SvLayer layer) {
      return PerformanceUtils.linRgr(layer.getOutput(), layer.getTarget());
    }

    @Override
    public String toString() {
      return "LINRGR";
    }
  };

  public static final IPerformance LINRGR_RGU = new IPerformance() {
    @Override
    public double measurePerformance(SvLayer layer) {
      return PerformanceUtils.linRgrRgu(layer.getOutput(), layer.getTarget(), layer.getWeight(), layer.getReguFctr());
    }

    @Override
    public String toString() {
      return "LINRGR_RGU";
    }
  };

  public static final IPerformance LOGRGR = new IPerformance() {
    @Override
    public double measurePerformance(SvLayer layer) {
      return PerformanceUtils.logRgr(layer.getOutput(), layer.getTarget());
    }

    @Override
    public String toString() {
      return "LOGRGR";
    }
  };

  public static final IPerformance LOGRGR_RGU = new IPerformance() {
    @Override
    public double measurePerformance(SvLayer layer) {
      return PerformanceUtils.logRgrRgu(layer.getOutput(), layer.getTarget(), layer.getWeight(), layer.getReguFctr());
    }

    @Override
    public String toString() {
      return "LOGRGR_RGU";
    }
  };

  public static final IPerformance MAE = new IPerformance() {
    @Override
    public double measurePerformance(SvLayer layer) {
      return PerformanceUtils.mae(layer.getOutput(), layer.getTarget());
    }

    @Override
    public String toString() {
      return "MAE";
    }
  };

  public static final IPerformance MSE = new IPerformance() {
    @Override
    public double measurePerformance(SvLayer layer) {
      return PerformanceUtils.mse(layer.getOutput(), layer.getTarget());
    }

    @Override
    public String toString() {
      return "MSE";
    }
  };

  public static final IPerformance SSE = new IPerformance() {
    @Override
    public double measurePerformance(SvLayer layer) {
      return PerformanceUtils.sse(layer.getOutput(), layer.getTarget());
    }

    @Override
    public String toString() {
      return "SSE";
    }
  };

  public double measurePerformance(SvLayer layer);
}
