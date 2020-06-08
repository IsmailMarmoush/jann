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
