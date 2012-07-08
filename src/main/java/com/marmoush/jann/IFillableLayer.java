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
package com.marmoush.jann;

import org.jblas.DoubleMatrix;

// TODO: Auto-generated Javadoc
/**
 * 
 */
public interface IFillableLayer {

    /**
     * 
     *
     * @param value 
     * @param matrices 
     */
    public abstract void setFill(double value, DoubleMatrix... matrices);

    /**
     * 
     *
     * @param matrices 
     */
    public abstract void setFillRandom(DoubleMatrix... matrices);

    /**
     * 
     *
     * @param matrices 
     */
    public abstract void setFillRandomFloor(DoubleMatrix... matrices);

    /**
     * 
     *
     * @param min 
     * @param max 
     * @param matrices 
     */
    public abstract void setFillRandomMinMax(double min, double max,
	    DoubleMatrix... matrices);

    /**
     * 
     *
     * @param min 
     * @param max 
     * @param matrices 
     */
    public abstract void setFillRandomMinMaxFloor(int min, int max,
	    DoubleMatrix... matrices);
}