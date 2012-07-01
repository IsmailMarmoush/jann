package com.marmoush.jann.chart;

import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class LinRgrChartUtils {

    public static void createLinRgrPNG(String path, String title,
	    XYSeries... series) {

	XYSeriesCollection xySeriesCollection = ChartUtils
		.getXYSeriesCollection(series);
	JFreeChart chart = LinRgrChartUtils.getLinRgrChart(title, title,
		xySeriesCollection);
	try {
	    File file = new File(path);
	    ChartUtilities.saveChartAsPNG(file, chart, 500, 300);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public static JFreeChart getLinRgrChart(String applicationTitle,
	    String chartTitle, XYSeriesCollection xySeriesCollection) {
	return ChartFactory.createXYLineChart(applicationTitle, "x-axis",
		"y-axis", xySeriesCollection, PlotOrientation.VERTICAL, true,
		true, false);
    }
}
