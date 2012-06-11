package com.marmoush.jann.model.regression.linear;

import com.marmoush.jann.sv.SvLayer;

public class OneVariableLnReg {
    private SvLayer layer;
    public OneVariableLnReg(boolean bias,int nInputs) {
	setLayer(new SvLayer(nInputs, 1));
	
    }
    public SvLayer getLayer() {
	return layer;
    }
    public void setLayer(SvLayer layer) {
	this.layer = layer;
    }
    
    
}
