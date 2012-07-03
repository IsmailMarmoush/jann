package com.marmoush.jann.chart.jframe;

import java.awt.HeadlessException;

import javax.swing.JFrame;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

public abstract class ChartJFrame extends JFrame {
    private static final long serialVersionUID = -7922555494678114867L;
    private int height = 600;
    private int width = 800;
    private String xTitle = "X-Axis";
    private String yTitle = "Y-Axis";
    private JFreeChart chart = null;

    public ChartJFrame() throws HeadlessException {
	super();
    }

    public ChartJFrame(String appTitle, JFreeChart chart)
	    throws HeadlessException {
	super(appTitle);
	this.chart=chart;
	ChartPanel chartPanel = new ChartPanel(chart);
	chartPanel.setPreferredSize(new java.awt.Dimension(getWidth(),
		getHeight()));
	setContentPane(chartPanel);
	pack();
	setVisible(true);
    }

    public int getHeight() {
	return height;
    }

    public int getWidth() {
	return width;
    }

    public String getxTitle() {
	return xTitle;
    }

    public String getyTitle() {
	return yTitle;
    }

    public void setHeight(int height) {
	this.height = height;
    }

    public void setWidth(int width) {
	this.width = width;
    }

    public void setxTitle(String xTitle) {
	this.xTitle = xTitle;
    }

    public void setyTitle(String yTitle) {
	this.yTitle = yTitle;
    }

}
