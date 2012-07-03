package com.marmoush.jann.chart.jframe;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.marmoush.jann.chart.ChartUtils;

public class LinRgrJf extends ChartJFrame {

    private static final long serialVersionUID = -6259803593823138950L;
    private XYSeriesCollection dataset = null;

    public LinRgrJf() {
    }

    public LinRgrJf(XYSeries... seriesList) {
	this.dataset = ChartUtils.getXYSeriesCollection(seriesList);
	JFreeChart chart = ChartFactory.createXYLineChart(getTitle(),
		getxAxisTitle(), getyAxisTitle(), dataset, getOrientation(),
		isLegend(), isTooltips(), isUrls());
	setChart(chart);
    }

    public XYSeriesCollection getDataset() {
	return dataset;
    }

    public void setDataset(XYSeriesCollection dataset) {
	this.dataset = dataset;
    }

}
