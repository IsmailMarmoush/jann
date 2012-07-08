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
package com.marmoush.jann.model.regression;

import java.util.List;

import org.jblas.DoubleMatrix;

import com.marmoush.jann.sv.SvLayer;
import com.marmoush.jann.utils.functors.IPerformance;
import com.marmoush.jann.utils.functors.ITransfere;
import com.marmoush.jann.utils.functors.IWeight;

public class LogisticRegression extends SvLayer {

    private static final long serialVersionUID = 6248944247719115094L;

    
    public LogisticRegression(DoubleMatrix batchTrainingEx,
	    DoubleMatrix batchTargets, boolean biased) {
	super(batchTrainingEx.columns, 1, biased);
	setWeightFnctr(IWeight.BATCH_DOTPROD);
	setTransfereFnctr(ITransfere.LOGSIG);
	setPerformancefnctr(IPerformance.LOGRGR);
    }

    
    public LogisticRegression(List<DoubleMatrix> trainingEx,
	    List<DoubleMatrix> targetList, boolean biased) {
	super(trainingEx.get(0).rows, targetList.get(0).length, biased);
	setWeightFnctr(IWeight.DOTPROD);
	setTransfereFnctr(ITransfere.LOGSIG);
	setPerformancefnctr(IPerformance.LOGRGR);
    }
}
