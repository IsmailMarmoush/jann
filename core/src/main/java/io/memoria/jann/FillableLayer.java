package io.memoria.jann;

import org.jblas.DoubleMatrix;

public interface FillableLayer {

  public abstract void setFill(double value, DoubleMatrix... matrices);

  public abstract void setFillRandom(DoubleMatrix... matrices);

  public abstract void setFillRandomFloor(DoubleMatrix... matrices);

  public abstract void setFillRandomMinMax(double min, double max, DoubleMatrix... matrices);

  public abstract void setFillRandomMinMaxFloor(int min, int max, DoubleMatrix... matrices);
}
