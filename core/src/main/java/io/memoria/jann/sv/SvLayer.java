package io.memoria.jann.sv;

import io.memoria.jann.utils.functors.IPerformance;
import org.jblas.DoubleMatrix;

import io.memoria.jann.Layer;

public interface SvLayer extends Layer {

  public abstract double getLearnRate();

  public abstract void setLearnRate(double lrnRate);

  public abstract double getPerformance();

  public abstract void setPerformance(double performance);

  public abstract IPerformance getPerformancefnctr();

  public abstract void setPerformancefnctr(IPerformance performancefnctr);

  public abstract double getReguFctr();

  public abstract void setReguFctr(double reg);

  public abstract DoubleMatrix getTarget();

  public abstract void setTarget(DoubleMatrix target);

  public abstract double updatePerformance();

}
