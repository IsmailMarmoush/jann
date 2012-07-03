package com.marmoush.jann.chart;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jblas.DoubleMatrix;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ChartUtils {
    public static void createScatterDataPNG(String path, String title,
	    String xTitle, String yTitle, XYSeries... series) {
	XYSeriesCollection xySeriesCollection = ChartUtils
		.getXYSeriesCollection(series);
	JFreeChart chart = ChartFactory.createScatterPlot(title, xTitle,
		yTitle, xySeriesCollection, PlotOrientation.VERTICAL, false,
		false, false);
	try {
	    File file = new File(path);
	    ChartUtilities.saveChartAsPNG(file, chart, 800, 600);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public static void createLinRgrPNG(String path, String title,
	    String xTitle, String yTitle, XYSeries... series) {

	XYSeriesCollection xySeriesCollection = getXYSeriesCollection(series);
	JFreeChart chart = ChartFactory.createXYLineChart(title, xTitle,
		yTitle, xySeriesCollection, PlotOrientation.VERTICAL, false,
		false, false);
	try {
	    File file = new File(path);
	    ChartUtilities.saveChartAsPNG(file, chart, 800, 600);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public static XYSeriesCollection getXYSeriesCollection(
	    XYSeries... seriesList) {
	XYSeriesCollection dataset = new XYSeriesCollection();
	for (XYSeries xySeries : seriesList) {
	    dataset.addSeries(xySeries);
	}
	return dataset;
    }

    public static XYSeries xySeries(String key, double[][] data) {
	XYSeries series = new XYSeries(key);
	for (int row = 0; row < data.length; row++) {
	    series.add(data[row][0], data[row][1]);
	}
	return series;
    }

    public static XYSeries xySeries(String key, DoubleMatrix xyMtrx,
	    int xColIdx, int yColdIdx) {

	XYSeries series = new XYSeries(key);
	for (int row = 0; row < xyMtrx.rows; row++) {
	    series.add(xyMtrx.get(row, xColIdx), xyMtrx.get(row, yColdIdx));
	}
	return series;
    }

    public static XYSeries xySeries(String key, List<Double> x, List<Double> y) {
	// assert (x.size()==y.size();
	XYSeries series = new XYSeries(key);
	for (int row = 0; row < x.size(); row++) {
	    series.add(x.get(row), y.get(row));
	}
	return series;
    }
}
