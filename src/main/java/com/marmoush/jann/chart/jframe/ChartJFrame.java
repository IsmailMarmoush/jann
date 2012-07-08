/*
 * Copyright 2012 Ismail Marmoush This file is part of JANN. JANN is free
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
package com.marmoush.jann.chart.jframe;

import java.awt.Dimension;

import javax.swing.JFrame;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;


/**
 * 
 */
public abstract class ChartJFrame extends JFrame {

    
    private static final long serialVersionUID = -7922555494678114867L;

    
    private String apptitle = "JANN Chart";

    
    private JFreeChart chart = null;

    
    private int height = 600;

    
    private boolean legend = false;

    
    private PlotOrientation orientation = PlotOrientation.VERTICAL;

    
    private boolean tooltips = false;

    
    private boolean urls = false;

    
    private int width = 800;

    
    private String xAxisTitle = "X-Axis";

    
    private XYSeriesCollection xySeriesCollection = null;

    
    private String yAxisTitle = "Y-Axis";

    /**
     * @return
     */
    public String getApptitle() {
	return apptitle;
    }

    /**
     * @return
     */
    public JFreeChart getChart() {
	return chart;
    }

    /*
     * (non-Javadoc)
     * @see java.awt.Component#getHeight()
     */
    /**
     * @return
     */
    @Override
    public int getHeight() {
	return height;
    }

    /**
     * @return
     */
    public PlotOrientation getOrientation() {
	return orientation;
    }

    /*
     * (non-Javadoc)
     * @see java.awt.Component#getWidth()
     */
    /**
     * @return
     */
    @Override
    public int getWidth() {
	return width;
    }

    /**
     * @return
     */
    public String getxAxisTitle() {
	return xAxisTitle;
    }

    /**
     * @return
     */
    public XYSeriesCollection getXySeriesCollection() {
	return xySeriesCollection;
    }

    /**
     * @return
     */
    public String getyAxisTitle() {
	return yAxisTitle;
    }

    /**
     * @return
     */
    public boolean isLegend() {
	return legend;
    }

    /**
     * @return
     */
    public boolean isTooltips() {
	return tooltips;
    }

    /**
     * @return
     */
    public boolean isUrls() {
	return urls;
    }

    /**
     * 
     */
    public void run() {
	Dimension die = new Dimension(width, height);
	setPreferredSize(die);
	ChartPanel chartPanel = new ChartPanel(chart);
	chartPanel.setPreferredSize(die);
	setTitle(apptitle);
	setContentPane(chartPanel);
	pack();
	setVisible(true);
    }

    /**
     * @param apptitle
     */
    public void setApptitle(String apptitle) {
	this.apptitle = apptitle;
    }

    /**
     * @param chart
     */
    public void setChart(JFreeChart chart) {
	this.chart = chart;
    }

    /**
     * @param height
     */
    public void setHeight(int height) {
	this.height = height;
    }

    /**
     * @param legend
     */
    public void setLegend(boolean legend) {
	this.legend = legend;
    }

    /**
     * @param orientation
     */
    public void setOrientation(PlotOrientation orientation) {
	this.orientation = orientation;
    }

    /**
     * @param tooltips
     */
    public void setTooltips(boolean tooltips) {
	this.tooltips = tooltips;
    }

    /**
     * @param urls
     */
    public void setUrls(boolean urls) {
	this.urls = urls;
    }

    /**
     * @param width
     */
    public void setWidth(int width) {
	this.width = width;
    }

    /**
     * @param xTitle
     */
    public void setxAxisTitle(String xTitle) {
	this.xAxisTitle = xTitle;
    }

    /**
     * @param xySeriesCollection
     */
    public void setXySeriesCollection(XYSeriesCollection xySeriesCollection) {
	this.xySeriesCollection = xySeriesCollection;
    }

    /**
     * @param yTitle
     */
    public void setyAxisTitle(String yTitle) {
	this.yAxisTitle = yTitle;
    }

}
