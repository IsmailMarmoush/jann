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
