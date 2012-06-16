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
package com.marmoush.jann.utils;

import java.util.List;

import org.jblas.DoubleMatrix;
import org.jblas.Solve;

import com.marmoush.jann.sv.SvLayer;
import com.marmoush.jann.utils.functors.ITransfere;

/**
 * The Class TrainUtils.
 */
public class TrainUtils {
    public static DoubleMatrix NormalEq(DoubleMatrix x, DoubleMatrix targets) {
	// normal equations works only for linear regression (one output)
	// X rows = m trainning examples
	// X cols = n of features
	// y is column vector where rows = target value for each training
	// examples
	// ((X'*X)^-1) * (X' * y)
	DoubleMatrix inverse = MatrixUtils.inv(x.transpose().mmul(x));
	DoubleMatrix xTransposeY = x.transpose().mmul(targets);
	return inverse.mmul(xTransposeY);
    }

    /**
     * Mse ng derv dw.
     * 
     * @param lrnRate
     *            the lrn rate
     * @param input
     *            the input
     * @param output
     *            the output
     * @param target
     *            the target
     * @return deltaWeight
     */
    public static DoubleMatrix mseNgDervDW(double lrnRate, DoubleMatrix input,
	    DoubleMatrix output, DoubleMatrix target) {

	DoubleMatrix error = output.sub(target);
	DoubleMatrix dw = error.mmul(input).muli(lrnRate / output.length);
	return dw;
    }

    /**
     * Mse ng derv db.
     * 
     * @param lrnRate
     *            the lrn rate
     * @param output
     *            the output
     * @param target
     *            the target
     * @return the double matrix
     */
    public static DoubleMatrix mseNgDervDB(double lrnRate, DoubleMatrix output,
	    DoubleMatrix target) {

	DoubleMatrix error = output.sub(target);
	DoubleMatrix db = error.muli(lrnRate / output.length);
	return db;
    }

    /**
     * Stochastic lin reg gd.
     * 
     * @param layer
     *            the layer
     * @param inputList
     *            the input list
     * @param targetList
     *            the target list
     * @return the sv layer
     */
    public static SvLayer stochasticLinRgrGd(SvLayer layer,
	    List<DoubleMatrix> inputList, List<DoubleMatrix> targetList) {
	// Make sure layer transfer is purelin function
	layer.setTransfereFnctr(ITransfere.PURELIN);
	// In stochastic the weights are updated after examining each example.
	for (int i = 0; i < inputList.size(); i++) {
	    layer.setInput(inputList.get(i));
	    layer.setTarget(targetList.get(i));
	    layer.simulate();
	    DoubleMatrix dw = mseNgDervDW(layer.getLearnRate(),
		    layer.getInput(), layer.getOutput(), layer.getTarget());
	    layer.getWeight().subi(dw);
	    DoubleMatrix db = mseNgDervDB(layer.getLearnRate(),
		    layer.getOutput(), layer.getTarget());
	    layer.getBias().subi(db);
	}

	return layer;
    }

    /**
     * Batch lin reg gd.
     * 
     * @param layer
     *            the layer
     * @param inputList
     *            the input list
     * @param targetList
     *            the target list
     * @return the sv layer
     */
    public static SvLayer batchLinRgrGd(SvLayer layer,
	    List<DoubleMatrix> inputList, List<DoubleMatrix> targetList) {
	// Make sure layer transfer is purelin function
	layer.setTransfereFnctr(ITransfere.PURELIN);
	// In batch the weights are updated after examining all examples
	DoubleMatrix dw = null;
	DoubleMatrix db = null;
	DoubleMatrix sumDw = DoubleMatrix.zeros(layer.getWeight().rows,
		layer.getWeight().columns);
	DoubleMatrix sumDb = DoubleMatrix.zeros(layer.getBias().rows,
		layer.getBias().columns);

	for (int i = 0; i < inputList.size(); i++) {
	    layer.setInput(inputList.get(i));
	    layer.setTarget(targetList.get(i));
	    layer.simulate();
	    dw = mseNgDervDW(layer.getLearnRate(), layer.getInput(),
		    layer.getOutput(), layer.getTarget());
	    sumDw.subi(dw);
	    db = mseNgDervDB(layer.getLearnRate(), layer.getOutput(),
		    layer.getTarget());
	    sumDb.subi(db);
	}
	sumDw.divi(inputList.size());
	sumDb.divi(inputList.size());
	layer.getWeight().subi(sumDw);
	layer.getBias().subi(sumDb);
	return layer;
    }
}
