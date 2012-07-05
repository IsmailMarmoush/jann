/*
 * Copyright 2012 Ismail Marmoush
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
package com.marmoush.jann.chart.jframe;

import java.awt.Dimension;

import javax.swing.JFrame;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;

// TODO: Auto-generated Javadoc
/**
 * The Class ChartJFrame.
 */
public abstract class ChartJFrame extends JFrame {
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -7922555494678114867L;
    
    /** The apptitle. */
    private String apptitle = "JANN Chart";
    
    /** The chart. */
    private JFreeChart chart = null;
    
    /** The height. */
    private int height = 600;
    
    /** The legend. */
    private boolean legend = false;
    
    /** The orientation. */
    private PlotOrientation orientation = PlotOrientation.VERTICAL;
    
    /** The tooltips. */
    private boolean tooltips = false;
    
    /** The urls. */
    private boolean urls = false;
    
    /** The width. */
    private int width = 800;
    
    /** The x axis title. */
    private String xAxisTitle = "X-Axis";
    
    /** The y axis title. */
    private String yAxisTitle = "Y-Axis";
    
    /** The xy series collection. */
    private XYSeriesCollection xySeriesCollection = null;
    
    /**
     * Gets the xy series collection.
     *
     * @return the xy series collection
     */
    public XYSeriesCollection getXySeriesCollection() {
        return xySeriesCollection;
    }

    /**
     * Sets the xy series collection.
     *
     * @param xySeriesCollection the new xy series collection
     */
    public void setXySeriesCollection(XYSeriesCollection xySeriesCollection) {
        this.xySeriesCollection = xySeriesCollection;
    }

    /**
     * Gets the apptitle.
     *
     * @return the apptitle
     */
    public String getApptitle() {
	return apptitle;
    }

    /**
     * Gets the chart.
     *
     * @return the chart
     */
    public JFreeChart getChart() {
	return chart;
    }

    /* (non-Javadoc)
     * @see java.awt.Component#getHeight()
     */
    public int getHeight() {
	return height;
    }

    /**
     * Gets the orientation.
     *
     * @return the orientation
     */
    public PlotOrientation getOrientation() {
	return orientation;
    }

    /* (non-Javadoc)
     * @see java.awt.Component#getWidth()
     */
    public int getWidth() {
	return width;
    }

    /**
     * Gets the x axis title.
     *
     * @return the x axis title
     */
    public String getxAxisTitle() {
	return xAxisTitle;
    }

    /**
     * Gets the y axis title.
     *
     * @return the y axis title
     */
    public String getyAxisTitle() {
	return yAxisTitle;
    }

    /**
     * Checks if is legend.
     *
     * @return true, if is legend
     */
    public boolean isLegend() {
	return legend;
    }

    /**
     * Checks if is tooltips.
     *
     * @return true, if is tooltips
     */
    public boolean isTooltips() {
	return tooltips;
    }

    /**
     * Checks if is urls.
     *
     * @return true, if is urls
     */
    public boolean isUrls() {
	return urls;
    }

    /**
     * Run.
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
     * Sets the apptitle.
     *
     * @param apptitle the new apptitle
     */
    public void setApptitle(String apptitle) {
	this.apptitle = apptitle;
    }

    /**
     * Sets the chart.
     *
     * @param chart the new chart
     */
    public void setChart(JFreeChart chart) {
	this.chart = chart;
    }

    /**
     * Sets the height.
     *
     * @param height the new height
     */
    public void setHeight(int height) {
	this.height = height;
    }

    /**
     * Sets the legend.
     *
     * @param legend the new legend
     */
    public void setLegend(boolean legend) {
	this.legend = legend;
    }

    /**
     * Sets the orientation.
     *
     * @param orientation the new orientation
     */
    public void setOrientation(PlotOrientation orientation) {
	this.orientation = orientation;
    }

    /**
     * Sets the tooltips.
     *
     * @param tooltips the new tooltips
     */
    public void setTooltips(boolean tooltips) {
	this.tooltips = tooltips;
    }

    /**
     * Sets the urls.
     *
     * @param urls the new urls
     */
    public void setUrls(boolean urls) {
	this.urls = urls;
    }

    /**
     * Sets the width.
     *
     * @param width the new width
     */
    public void setWidth(int width) {
	this.width = width;
    }

    /**
     * Sets the x axis title.
     *
     * @param xTitle the new x axis title
     */
    public void setxAxisTitle(String xTitle) {
	this.xAxisTitle = xTitle;
    }

    /**
     * Sets the y axis title.
     *
     * @param yTitle the new y axis title
     */
    public void setyAxisTitle(String yTitle) {
	this.yAxisTitle = yTitle;
    }

}
