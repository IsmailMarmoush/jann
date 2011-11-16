/*
 * Copyright 2011 Ismail Marmoush
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
package com.marmoush.jann;

import org.jblas.DoubleMatrix;

import com.marmoush.jann.utils.functors.ITransfere;
import com.marmoush.jann.utils.functors.IWeight;

// TODO: Auto-generated Javadoc
/**
 * The Interface ILayer.
 */
public interface ILayer extends IFillableLayer
{

    /**
     * Equals.
     *
     * @param obj the obj
     * @return true, if successful
     */
    @Override
    public abstract boolean equals(Object obj);

    /**
     * Gets the bias.
     *
     * @return the bias
     */
    public abstract DoubleMatrix getBias();

    /**
     * Gets the input.
     *
     * @return the input
     */
    public abstract DoubleMatrix getInput();

    /**
     * Gets the net sum.
     *
     * @return the net sum
     */
    public abstract DoubleMatrix getNetSum();

    /**
     * Gets the output.
     *
     * @return the output
     */
    public abstract DoubleMatrix getOutput();

    /**
     * Gets the theta.
     *
     * @return the theta
     */
    public abstract double getTheta();

    /**
     * Gets the transfere fnctr.
     *
     * @return the transfere fnctr
     */
    public abstract ITransfere getTransfereFnctr();

    /**
     * Gets the weight.
     *
     * @return the weight
     */
    public abstract DoubleMatrix getWeight();

    /**
     * Gets the weight fnctr.
     *
     * @return the weight fnctr
     */
    public abstract IWeight getWeightFnctr();

    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public abstract int hashCode();

    /**
     * Checks if is input only layer.
     *
     * @return true, if is input only layer
     */
    public abstract boolean isInputOnlyLayer();

    /**
     * Sets the bias.
     *
     * @param bias the new bias
     */
    public abstract void setBias(DoubleMatrix bias);

    /*
     * (non-Javadoc)
     * 
     * @see com.marmoush.jann.IFillableLayer#setFill(double,
     * org.jblas.DoubleMatrix[])
     */
    public abstract void setFill(double value, DoubleMatrix... matrices);

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.marmoush.jann.IFillableLayer#setFillRandom(org.jblas.DoubleMatrix[])
     */
    public abstract void setFillRandom(DoubleMatrix... matrices);

    /**
     * Sets the input.
     *
     * @param input the new input
     */
    public abstract void setInput(DoubleMatrix input);

    /**
     * Sets the net sum.
     *
     * @param netsum the new net sum
     */
    public abstract void setNetSum(DoubleMatrix netsum);

    /**
     * Sets the output.
     *
     * @param output the new output
     */
    public abstract void setOutput(DoubleMatrix output);

    /**
     * Sets the theta.
     *
     * @param theta the new theta
     */
    public abstract void setTheta(double theta);

    /**
     * Sets the transfere fnctr.
     *
     * @param transfereFnctr the new transfere fnctr
     */
    public abstract void setTransfereFnctr(ITransfere transfereFnctr);

    /**
     * Sets the weight.
     *
     * @param weight the new weight
     */
    public abstract void setWeight(DoubleMatrix weight);

    /**
     * Sets the weight fnctr.
     *
     * @param weightFnctr the new weight fnctr
     */
    public abstract void setWeightFnctr(IWeight weightFnctr);

    /**
     * Simulate.
     */
    public abstract void simulate();

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public abstract String toString();

    /**
     * Update net sum.
     *
     * @return the double matrix
     */
    public abstract DoubleMatrix updateNetSum();

    /**
     * Update output.
     *
     * @return the double matrix
     */
    public abstract DoubleMatrix updateOutput();

}