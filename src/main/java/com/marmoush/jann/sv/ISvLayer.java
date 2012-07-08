/*
 * Copyright 2012 Ismail Marmoush This file is part of JANN. JANN is free
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
package com.marmoush.jann.sv;

import org.jblas.DoubleMatrix;

import com.marmoush.jann.ILayer;
import com.marmoush.jann.utils.functors.IPerformance;


/**
 * 
 */
public interface ISvLayer extends ILayer {

    /**
     * 
     *
     * @return 
     */
    public abstract double getLearnRate();

    /**
     * 
     *
     * @return 
     */
    public abstract double getPerformance();

    /**
     * 
     *
     * @return 
     */
    public abstract IPerformance getPerformancefnctr();

    /**
     * 
     *
     * @return 
     */
    public abstract double getReguFctr();

    /**
     * 
     *
     * @return 
     */
    public abstract DoubleMatrix getTarget();

    /**
     * 
     *
     * @param lrnRate 
     */
    public abstract void setLearnRate(double lrnRate);

    /**
     * 
     *
     * @param performance 
     */
    public abstract void setPerformance(double performance);

    /**
     * 
     *
     * @param performancefnctr 
     */
    public abstract void setPerformancefnctr(IPerformance performancefnctr);

    /**
     * 
     *
     * @param reg 
     */
    public abstract void setReguFctr(double reg);

    /**
     * 
     *
     * @param target 
     */
    public abstract void setTarget(DoubleMatrix target);

    /**
     * 
     *
     * @return 
     */
    public abstract double updatePerformance();

}