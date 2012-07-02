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
public class SvLayer extends Layer implements ISvLayer {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 214303268098893298L;

    /** The learn rate. */
    private double learnRate = 1;
    /** The performance. */
    private double performance;
    /** The performancefnctr. */
    private IPerformance performancefnctr = null;

    private double regularizationFctr = 0;

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
    public SvLayer(int nInputs, int nNeurons, boolean biased) {
	super(nInputs, nNeurons, biased);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	SvLayer other = (SvLayer) obj;
	if (Double.doubleToLongBits(learnRate) != Double
		.doubleToLongBits(other.learnRate))
	    return false;
	if (Double.doubleToLongBits(performance) != Double
		.doubleToLongBits(other.performance))
	    return false;
	if (performancefnctr == null) {
	    if (other.performancefnctr != null)
		return false;
	} else if (!performancefnctr.equals(other.performancefnctr))
	    return false;
	if (target == null) {
	    if (other.target != null)
		return false;
	} else if (!target.equals(other.target))
	    return false;
	return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.marmoush.jann.sv.ISvLayer#getLearnRate()
     */
    @Override
    public double getLearnRate() {
	return learnRate;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.marmoush.jann.sv.ISvLayer#getPerformance()
     */
    @Override
    public double getPerformance() {
	return performance;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.marmoush.jann.sv.ISvLayer#getPerformancefnctr()
     */
    @Override
    public IPerformance getPerformancefnctr() {
	return performancefnctr;
    }

    @Override
    public double getReguFctr() {
	return this.regularizationFctr;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.marmoush.jann.sv.ISvLayer#getTarget()
     */
    @Override
    public DoubleMatrix getTarget() {
	return target;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	long temp;
	temp = Double.doubleToLongBits(learnRate);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	temp = Double.doubleToLongBits(performance);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	result = prime
		* result
		+ ((performancefnctr == null) ? 0 : performancefnctr.hashCode());
	result = prime * result + ((target == null) ? 0 : target.hashCode());
	return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.marmoush.jann.sv.ISvLayer#setLearnRate(double)
     */
    @Override
    public void setLearnRate(double lrnRate) {
	this.learnRate = lrnRate;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.marmoush.jann.sv.ISvLayer#setPerformance(double)
     */
    @Override
    public void setPerformance(double performance) {
	this.performance = performance;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.marmoush.jann.sv.ISvLayer#setPerformancefnctr(com.marmoush.jann.utils
     * .functors.IPerformance)
     */
    @Override
    public void setPerformancefnctr(IPerformance performancefnctr) {
	this.performancefnctr = performancefnctr;
    }

    @Override
    public void setReguFctr(double reg) {
	this.regularizationFctr = reg;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.marmoush.jann.sv.ISvLayer#setTarget(org.jblas.DoubleMatrix)
     */
    @Override
    public void setTarget(DoubleMatrix target) {
	this.target = target;
    }

    @Override
    public void simulate() {
	super.simulate();
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

    /*
     * (non-Javadoc)
     * 
     * @see com.marmoush.jann.sv.ISvLayer#updatePerformance()
     */
    @Override
    public double updatePerformance() {
	performance = performancefnctr.measurePerformance(this);
	return performance;
    }

}
