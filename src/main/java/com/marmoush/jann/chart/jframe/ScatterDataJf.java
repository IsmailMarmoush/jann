package com.marmoush.jann.chart.jframe;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.marmoush.jann.chart.ChartUtils;

public class ScatterDataJf extends ChartJFrame {
    private static final long serialVersionUID = -457908141851955661L;
    private XYSeriesCollection dataset = null;

    public ScatterDataJf() {
    }

    public ScatterDataJf(XYSeries... seriesList) {
	this.dataset = ChartUtils.getXYSeriesCollection(seriesList);
	JFreeChart chart = ChartFactory.createScatterPlot(getTitle(),
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
