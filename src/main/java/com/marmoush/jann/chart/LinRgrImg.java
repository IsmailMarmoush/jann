package com.marmoush.jann.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;

public class LinRgrImg extends ChartImage {

    public LinRgrImg(String path, XYSeries... series) {
	setPath(path);
	setXySeriesCollec(ChartUtils.getXYSeriesCollection(series));

    }

    @Override
    public void createJPEG() {
	JFreeChart chart = ChartFactory.createXYLineChart(getTitle(),
		getxAxisTitle(), getyAxisTitle(), getXySeriesCollec(),
		getOrientation(), isLegend(), isTooltips(), isUrls());
	setChart(chart);
	super.createJPEG();
    }

    @Override
    public void createPNG() {
	JFreeChart chart = ChartFactory.createXYLineChart(getTitle(),
		getxAxisTitle(), getyAxisTitle(), getXySeriesCollec(),
		getOrientation(), isLegend(), isTooltips(), isUrls());
	setChart(chart);
	super.createPNG();
    }
}
