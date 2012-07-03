package com.marmoush.jann.chart;

import org.jfree.chart.JFreeChart;

public class ChartImage {
    private JFreeChart chart = null;
    private int height = 600;
    private int width = 800;
    private String xAxisTitle = "X-Axis";
    private String yAxisTitle = "Y-Axis";
    private String apptitle = "JANN Chart";

    public String getApptitle() {
        return apptitle;
    }

    public void setApptitle(String apptitle) {
        this.apptitle = apptitle;
    }

    public ChartImage() {
    }

    public ChartImage(JFreeChart chart) {
	this.chart = chart;
    }

    public JFreeChart getChart() {
	return chart;
    }

    public int getHeight() {
	return height;
    }

    public int getWidth() {
	return width;
    }

    public String getxAxisTitle() {
	return xAxisTitle;
    }

    public String getyAxisTitle() {
	return yAxisTitle;
    }

    public void setChart(JFreeChart chart) {
	this.chart = chart;
    }

    public void setHeight(int height) {
	this.height = height;
    }

    public void setWidth(int width) {
	this.width = width;
    }

    public void setxAxisTitle(String xTitle) {
	this.xAxisTitle = xTitle;
    }

    public void setyAxisTitle(String yTitle) {
	this.yAxisTitle = yTitle;
    }

}
