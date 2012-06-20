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
package com.marmoush.jann.sv;

import org.jblas.DoubleMatrix;

import com.marmoush.jann.Layer;
import com.marmoush.jann.utils.functors.IPerformance;

/**
 * The Class SvLayer.
 */
public class SvLayer extends Layer {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 214303268098893298L;

    /** The error. */
    private DoubleMatrix error = null;

    /** The learn rate. */
    private double learnRate = 1;

    /** The performance. */
    private double performance;

    /** The performancefnctr. */
    private IPerformance performancefnctr = IPerformance.MSE;

    /** The target. */
    private DoubleMatrix target = null;

    /**
     * Instantiates a new sv layer.
     */
    public SvLayer() {
	super();
    }

    /**
     * Instantiates a new sv layer.
     * 
     * @param nInputs
     *            the n inputs
     * @param nNeurons
     *            the n neurons
     */
    public SvLayer(int nInputs, int nNeurons) {
	super(nInputs, nNeurons);
	error = new DoubleMatrix(new double[nNeurons]);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.marmoush.jann.Layer#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	SvLayer other = (SvLayer) obj;
	if (error == null) {
	    if (other.error != null)
		return false;
	} else if (!error.equals(other.error))
	    return false;
	if (Double.doubleToLongBits(learnRate) != Double
		.doubleToLongBits(other.learnRate))
	    return false;
	if (target == null) {
	    if (other.target != null)
		return false;
	} else if (!target.equals(other.target))
	    return false;
	return true;
    }

    /**
     * Gets the error.
     * 
     * @return the error
     */
    public DoubleMatrix getError() {
	return error;
    }

    /**
     * Gets the learn rate.
     * 
     * @return the learn rate
     */
    public double getLearnRate() {
	return learnRate;
    }

    /**
     * Gets the performance.
     * 
     * @return the performance
     */
    public double getPerformance() {
	return performance;
    }

    /**
     * Gets the performancefnctr.
     * 
     * @return the performancefnctr
     */
    public IPerformance getPerformancefnctr() {
	return performancefnctr;
    }

    /**
     * Gets the target.
     * 
     * @return the target
     */
    public DoubleMatrix getTarget() {
	return target;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.marmoush.jann.Layer#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((error == null) ? 0 : error.hashCode());
	long temp;
	temp = Double.doubleToLongBits(learnRate);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	result = prime * result + ((target == null) ? 0 : target.hashCode());
	return result;
    }

    /**
     * Sets the error.
     * 
     * @param error
     *            the new error
     */
    public void setError(DoubleMatrix error) {
	this.error = error;
    }

    /**
     * Sets the learn rate.
     * 
     * @param lrnRate
     *            the new learn rate
     */
    public void setLearnRate(double lrnRate) {
	this.learnRate = lrnRate;
    }

    /**
     * Sets the performance.
     * 
     * @param performance
     *            the new performance
     */
    public void setPerformance(double performance) {
	this.performance = performance;
    }

    /**
     * Sets the performancefnctr.
     * 
     * @param performancefnctr
     *            the new performancefnctr
     */
    public void setPerformancefnctr(IPerformance performancefnctr) {
	this.performancefnctr = performancefnctr;
    }

    /**
     * Sets the target.
     * 
     * @param target
     *            the new target
     */
    public void setTarget(DoubleMatrix target) {
	this.target = target;
    }

    @Override
    public void simulate() {
	super.simulate();
	updateError();
	updatePerformance();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.marmoush.jann.Layer#toString()
     */
    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append(super.toString());
	builder.append("\nerror=");
	builder.append(error);
	builder.append(" \nlearnRate=");
	builder.append(learnRate);
	builder.append(" \nperformance=");
	builder.append(performance);
	builder.append(" \nperformancefnctr=");
	builder.append(performancefnctr);
	builder.append(" \ntarget=");
	builder.append(target);
	return builder.toString();
    }

    /**
     * Update error.
     * 
     * @return the double matrix
     */
    public DoubleMatrix updateError() {
	setError(getOutput().sub(getTarget()));
	return error;
    }

    /**
     * Update performance.
     * 
     * @return the double
     */
    public double updatePerformance() {
	performance = performancefnctr.measurePerformance(error);
	return performance;
    }

}
