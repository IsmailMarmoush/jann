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
package com.marmoush.jann;

import org.jblas.DoubleMatrix;

import com.marmoush.jann.utils.functors.ITransfere;
import com.marmoush.jann.utils.functors.IWeight;

public interface ILayer extends IFillableLayer {

    /**
     * @param obj
     * @return
     */
    @Override
    public abstract boolean equals(Object obj);

    public abstract DoubleMatrix getBias();

    public abstract DoubleMatrix getInput();

    public abstract DoubleMatrix getNetSum();

    public abstract DoubleMatrix getOutput();

    public abstract double getTheta();

    public abstract ITransfere getTransfereFnctr();

    public abstract DoubleMatrix getWeight();

    public abstract IWeight getWeightFnctr();

    @Override
    public abstract int hashCode();

    public abstract boolean isBiased();

    public abstract boolean isInputOnlyLayer();

    
    public abstract void setBias(DoubleMatrix bias);

    /*
     * (non-Javadoc)
     * @see com.marmoush.jann.IFillableLayer#setFill(double,
     * org.jblas.DoubleMatrix[])
     */
    /**
     * @param value
     * @param matrices
     */
    @Override
    public abstract void setFill(double value, DoubleMatrix... matrices);

    /*
     * (non-Javadoc)
     * @see
     * com.marmoush.jann.IFillableLayer#setFillRandom(org.jblas.DoubleMatrix[])
     */
    
    @Override
    public abstract void setFillRandom(DoubleMatrix... matrices);

    
    public abstract void setInput(DoubleMatrix input);

    
    public abstract void setNetSum(DoubleMatrix netsum);

    
    public abstract void setOutput(DoubleMatrix output);

    
    public abstract void setTheta(double theta);

    
    public abstract void setTransfereFnctr(ITransfere transfereFnctr);

    
    public abstract void setWeight(DoubleMatrix weight);

    
    public abstract void setWeightFnctr(IWeight weightFnctr);

    public abstract void simulate();

    @Override
    public abstract String toString();

    public abstract DoubleMatrix updateNetSum();

    public abstract DoubleMatrix updateOutput();

}