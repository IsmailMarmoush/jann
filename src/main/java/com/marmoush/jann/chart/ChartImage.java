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
package com.marmoush.jann.chart;

import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;

// TODO: Auto-generated Javadoc
/**
 * The Class ChartImage.
 */
public abstract class ChartImage {
    
    /** The chart. */
    private JFreeChart chart = null;
    
    /** The height. */
    private int height = 600;
    
    /** The legend. */
    private boolean legend = false;
    
    /** The orientation. */
    private PlotOrientation orientation = PlotOrientation.VERTICAL;
    
    /** The path. */
    private String path = null;
    
    /** The title. */
    private String title = "JANN Chart";
    
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
    
    /** The xy series collec. */
    private XYSeriesCollection xySeriesCollec = null;
    
    /**
     * Gets the xy series collec.
     *
     * @return the xy series collec
     */
    public XYSeriesCollection getXySeriesCollec() {
        return xySeriesCollec;
    }

    /**
     * Sets the xy series collec.
     *
     * @param dataset the new xy series collec
     */
    public void setXySeriesCollec(XYSeriesCollection dataset) {
        this.xySeriesCollec = dataset;
    }

    /**
     * Creates the png.
     */
    public void createPNG() {
	try {
	    File file = new File(path);
	    ChartUtilities.saveChartAsPNG(file, chart, width, height);
	    
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    /**
     * Creates the jpeg.
     */
    public void createJPEG() {
	try {
	    File file = new File(path);
	    ChartUtilities.saveChartAsJPEG(file, chart, width, height);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    /**
     * Gets the chart.
     *
     * @return the chart
     */
    public JFreeChart getChart() {
	return chart;
    }

    /**
     * Gets the height.
     *
     * @return the height
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

    /**
     * Gets the path.
     *
     * @return the path
     */
    public String getPath() {
	return path;
    }

    /**
     * Gets the title.
     *
     * @return the title
     */
    public String getTitle() {
	return title;
    }

    /**
     * Gets the width.
     *
     * @return the width
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
     * Sets the path.
     *
     * @param path the new path
     */
    public void setPath(String path) {
	this.path = path;
    }

    /**
     * Sets the title.
     *
     * @param title the new title
     */
    public void setTitle(String title) {
	this.title = title;
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
