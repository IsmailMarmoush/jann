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

import com.marmoush.jann.utils.functors.ITransfere;
import com.marmoush.jann.utils.functors.IWeight;


/**
 * 
 */
public interface ILayer extends IFillableLayer {

    /**
     * @param obj
     * @return
     */
    @Override
    public abstract boolean equals(Object obj);

    /**
     * @return
     */
    public abstract DoubleMatrix getBias();

    /**
     * @return
     */
    public abstract DoubleMatrix getInput();

    /**
     * @return
     */
    public abstract DoubleMatrix getNetSum();

    /**
     * @return
     */
    public abstract DoubleMatrix getOutput();

    /**
     * @return
     */
    public abstract double getTheta();

    /**
     * @return
     */
    public abstract ITransfere getTransfereFnctr();

    /**
     * @return
     */
    public abstract DoubleMatrix getWeight();

    /**
     * @return
     */
    public abstract IWeight getWeightFnctr();

    /**
     * @return
     */
    @Override
    public abstract int hashCode();

    /**
     * @return
     */
    public abstract boolean isBiased();

    /**
     * @return
     */
    public abstract boolean isInputOnlyLayer();

    /**
     * @param bias
     */
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
    /**
     * @param matrices
     */
    @Override
    public abstract void setFillRandom(DoubleMatrix... matrices);

    /**
     * @param input
     */
    public abstract void setInput(DoubleMatrix input);

    /**
     * @param netsum
     */
    public abstract void setNetSum(DoubleMatrix netsum);

    /**
     * @param output
     */
    public abstract void setOutput(DoubleMatrix output);

    /**
     * @param theta
     */
    public abstract void setTheta(double theta);

    /**
     * @param transfereFnctr
     */
    public abstract void setTransfereFnctr(ITransfere transfereFnctr);

    /**
     * @param weight
     */
    public abstract void setWeight(DoubleMatrix weight);

    /**
     * @param weightFnctr
     */
    public abstract void setWeightFnctr(IWeight weightFnctr);

    /**
     * 
     */
    public abstract void simulate();

    /**
     * @return
     */
    @Override
    public abstract String toString();

    /**
     * @return
     */
    public abstract DoubleMatrix updateNetSum();

    /**
     * @return
     */
    public abstract DoubleMatrix updateOutput();

}