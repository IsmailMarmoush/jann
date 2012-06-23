package com.marmoush.jann.plot;

public class Plot {
    public static void main(String[] args) {
	RegressionChart regression = new RegressionChart("Linear Regression",
		"Linear Regression");
	regression.pack();
	regression.setVisible(true);
    }
}
