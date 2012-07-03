package com.marmoush.jann.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ScatterDataImg extends ChartImage {
    public ScatterDataImg(String path, XYSeries... series) {
	setPath(path);
	XYSeriesCollection dataset = ChartUtils.getXYSeriesCollection(series);
	JFreeChart chart = ChartFactory.createScatterPlot(getTitle(),
		getxAxisTitle(), getyAxisTitle(), dataset, getOrientation(),
		isLegend(), isTooltips(), isUrls());
	setChart(chart);
    }
}
