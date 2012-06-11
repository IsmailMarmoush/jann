package com.marmoush.jann.model.regression.linear;

import com.marmoush.jann.sv.SvLayer;

public class OneVariableLnReg {
    private SvLayer layer;
    public OneVariableLnReg(boolean bias,int nInputs) {
	layer=new SvLayer(nInputs, 1);
	
    }
    
    
}
