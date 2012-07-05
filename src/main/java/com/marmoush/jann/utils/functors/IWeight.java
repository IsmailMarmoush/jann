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
package com.marmoush.jann.utils.functors;

import org.jblas.DoubleMatrix;

import com.marmoush.jann.ILayer;
import com.marmoush.jann.utils.WeightUtils;

// TODO: Auto-generated Javadoc
/**
 * The Interface IWeight.
 */
public interface IWeight extends IFunctionable {

    /** The Constant DOTPROD. */
    public static final IWeight BATCH_DOTPROD = new IWeight() {
	@Override
	public String toString() {
	    return "BATCH_DOTPROD";
	}

	@Override
	public DoubleMatrix weightFn(ILayer layer) {
	    if (layer.isBiased())
		return WeightUtils.batchDotprod(layer.getInput(),
			layer.getBias(), layer.getWeight());
	    else
		return WeightUtils.batchDotprod(layer.getInput(),
			layer.getWeight());
	}
    };
    /** The Constant DOTPROD. */
    public static final IWeight DOTPROD = new IWeight() {
	@Override
	public String toString() {
	    return "DOTPROD";
	}

	@Override
	public DoubleMatrix weightFn(ILayer layer) {
	    if (layer.isBiased())
		return WeightUtils.dotprod(layer.getInput(), layer.getBias(),
			layer.getWeight());
	    else
		return WeightUtils.dotprod(layer.getInput(), layer.getWeight());
	}
    };

    /**
     * Weight fn.
     * 
     * @param layer
     *            the layer
     * @return the double matrix
     */
    public DoubleMatrix weightFn(ILayer layer);
}
