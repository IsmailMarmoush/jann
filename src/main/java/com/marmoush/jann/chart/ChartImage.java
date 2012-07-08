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
package com.marmoush.jann.chart;

import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;

public abstract class ChartImage {

    private JFreeChart chart = null;

    private int height = 600;

    private boolean legend = false;

    private PlotOrientation orientation = PlotOrientation.VERTICAL;

    private String path = null;

    private String title = "JANN Chart";

    private boolean tooltips = false;

    private boolean urls = false;

    private int width = 800;

    private String xAxisTitle = "X-Axis";

    private XYSeriesCollection xySeriesCollec = null;

    private String yAxisTitle = "Y-Axis";

    public void createJPEG() {
	try {
	    File file = new File(path);
	    ChartUtilities.saveChartAsJPEG(file, chart, width, height);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void createPNG() {
	try {
	    File file = new File(path);
	    ChartUtilities.saveChartAsPNG(file, chart, width, height);

	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public JFreeChart getChart() {
	return chart;
    }

    public int getHeight() {
	return height;
    }

    public PlotOrientation getOrientation() {
	return orientation;
    }

    public String getPath() {
	return path;
    }

    public String getTitle() {
	return title;
    }

    public int getWidth() {
	return width;
    }

    public String getxAxisTitle() {
	return xAxisTitle;
    }

    public XYSeriesCollection getXySeriesCollec() {
	return xySeriesCollec;
    }

    public String getyAxisTitle() {
	return yAxisTitle;
    }

    public boolean isLegend() {
	return legend;
    }

    public boolean isTooltips() {
	return tooltips;
    }

    public boolean isUrls() {
	return urls;
    }

    public void setChart(JFreeChart chart) {
	this.chart = chart;
    }

    public void setHeight(int height) {
	this.height = height;
    }

    public void setLegend(boolean legend) {
	this.legend = legend;
    }

    public void setOrientation(PlotOrientation orientation) {
	this.orientation = orientation;
    }

    public void setPath(String path) {
	this.path = path;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public void setTooltips(boolean tooltips) {
	this.tooltips = tooltips;
    }

    public void setUrls(boolean urls) {
	this.urls = urls;
    }

    public void setWidth(int width) {
	this.width = width;
    }

    public void setxAxisTitle(String xTitle) {
	this.xAxisTitle = xTitle;
    }

    public void setXySeriesCollec(XYSeriesCollection dataset) {
	this.xySeriesCollec = dataset;
    }

    public void setyAxisTitle(String yTitle) {
	this.yAxisTitle = yTitle;
    }

}
