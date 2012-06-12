package com.marmoush.jann.model.regression.linear;

import com.marmoush.jann.sv.SvLayer;
import com.marmoush.jann.utils.functors.IPerformance;
import com.marmoush.jann.utils.functors.ITransfere;
import com.marmoush.jann.utils.functors.IWeight;

public class OneVariableLinReg {
    private SvLayer layer;

    public OneVariableLinReg(boolean bias) {
	setLayer(new SvLayer(1, 1));
	layer.setWeightFnctr(IWeight.DOTPROD);
	layer.setTransfereFnctr(ITransfere.PURELIN);
	layer.setPerformancefnctr(IPerformance.MSE);
	
    }
    
    public SvLayer getLayer() {
	return layer;
    }

    public void setLayer(SvLayer layer) {
	this.layer = layer;
    }
}
                                                      