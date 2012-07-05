/*
 * Copyright 2012 Ismail Marmoush
 * 
 * This file is part of JANN.
 * 
 * JANN is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License Version 3 as published by the Free
 * Software Foundation, either version 3 of the License, or any later version.
 * 
 * JANN is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * JANN. If not, see http://www.gnu.org/licenses/.
 * 
 * For More Information Please Visit http://jann.marmoush.com
 */
package com.marmoush.jann.sv;

import org.jblas.DoubleMatrix;

import com.marmoush.jann.ILayer;
import com.marmoush.jann.utils.functors.IPerformance;

public interface ISvLayer extends ILayer {
    /**
     * Gets the learn rate.
     * 
     * @return the learn rate
     */
    public abstract double getLearnRate();
    /**
     * Gets the performance.
     * 
     * @return the performance
     */
    public abstract double getPerformance();
    /**
     * Gets the performancefnctr.
     * 
     * @return the performancefnctr
     */
    public abstract IPerformance getPerformancefnctr();

    public abstract double getReguFctr();

    /**
     * Gets the target.
     * 
     * @return the target
     */
    public abstract DoubleMatrix getTarget();

    /**
     * Sets the learn rate.
     * 
     * @param lrnRate
     *            the new learn rate
     */
    public abstract void setLearnRate(double lrnRate);

    /**
     * Sets the performance.
     * 
     * @param performance
     *            the new performance
     */
    public abstract void setPerformance(double performance);

    /**
     * Sets the performancefnctr.
     * 
     * @param performancefnctr
     *            the new performancefnctr
     */
    public abstract void setPerformancefnctr(IPerformance performancefnctr);

    public abstract void setReguFctr(double reg);

    /**
     * Sets the target.
     * 
     * @param target
     *            the new target
     */
    public abstract void setTarget(DoubleMatrix target);

    /**
     * Update performance.
     * 
     * @return the double
     */
    public abstract double updatePerformance();

}