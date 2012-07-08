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
import com.marmoush.jann.utils.TransfereUtils;

// TODO: Auto-generated Javadoc
/**
 * 
 */
public interface ITransfere extends IFunctionable {

    /**
     * 
     */
    public static final ITransfere COMPET = new ITransfere() {
	@Override
	public String toString() {
	    return "COMPET";
	}

	@Override
	public DoubleMatrix transfere(ILayer layer) {
	    return TransfereUtils.compet(layer.getNetSum());
	}
    };

    /**
     * 
     */
    public static final ITransfere HARDLIM = new ITransfere() {
	@Override
	public String toString() {
	    return "HARDLIM";
	}

	@Override
	public DoubleMatrix transfere(ILayer layer) {
	    return TransfereUtils.hardlim(layer.getNetSum(), layer.getTheta());
	}
    };

    /**
     * 
     */
    public static final ITransfere HARDLIMS = new ITransfere() {
	@Override
	public String toString() {
	    return "HARDLIMS";
	}

	@Override
	public DoubleMatrix transfere(ILayer layer) {
	    return TransfereUtils.hardlims(layer.getNetSum(), layer.getTheta());
	}
    };

    /**
     * 
     */
    public static final ITransfere LOGSIG = new ITransfere() {
	@Override
	public String toString() {
	    return "LOGSIG";
	}

	@Override
	public DoubleMatrix transfere(ILayer layer) {
	    return TransfereUtils.logsig(layer.getNetSum());
	}
    };

    /**
     * 
     */
    public static final ITransfere LOGSIGDIFF = new ITransfere() {
	@Override
	public String toString() {
	    return "LOGSIGDIFF";
	}

	@Override
	public DoubleMatrix transfere(ILayer layer) {
	    return TransfereUtils.logsigDiff(layer.getNetSum());
	}
    };

    /**
     * 
     */
    public static final ITransfere POSLIN = new ITransfere() {
	@Override
	public String toString() {
	    return "POSLIN";
	}

	@Override
	public DoubleMatrix transfere(ILayer layer) {
	    return TransfereUtils.poslin(layer.getNetSum(), layer.getTheta());
	}
    };

    /**
     * 
     */
    public static final ITransfere PURELIN = new ITransfere() {
	@Override
	public String toString() {
	    return "PURELIN";
	}

	@Override
	public DoubleMatrix transfere(ILayer layer) {
	    return TransfereUtils.purelin(layer.getNetSum());
	}
    };

    /**
     * 
     */
    public static final ITransfere SATLIN = new ITransfere() {
	@Override
	public String toString() {
	    return "SATLIN";
	}

	@Override
	public DoubleMatrix transfere(ILayer layer) {
	    return TransfereUtils.satlin(layer.getNetSum());
	}
    };

    /**
     * 
     */
    public static final ITransfere SATLINS = new ITransfere() {
	@Override
	public String toString() {
	    return "SATLINS";
	}

	@Override
	public DoubleMatrix transfere(ILayer layer) {
	    return TransfereUtils.satlins(layer.getNetSum());
	}
    };

    /**
     * 
     */
    public static final ITransfere TANSIG = new ITransfere() {
	@Override
	public String toString() {
	    return "TANSIG";
	}

	@Override
	public DoubleMatrix transfere(ILayer layer) {
	    return TransfereUtils.tansig(layer.getNetSum());
	}
    };

    /**
     * 
     */
    public static final ITransfere TANSIGDIFF = new ITransfere() {
	@Override
	public String toString() {
	    return "TANSIGDIFF";
	}

	@Override
	public DoubleMatrix transfere(ILayer layer) {
	    return TransfereUtils.tansigDiff(layer.getNetSum());
	}
    };

    /**
     * 
     * 
     * @param layer
     * @return
     */
    public DoubleMatrix transfere(ILayer layer);
}
