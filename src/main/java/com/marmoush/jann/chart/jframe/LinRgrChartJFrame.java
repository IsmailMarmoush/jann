package com.marmoush.jann.chart.jframe;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.marmoush.jann.chart.ChartUtils;

public class LinRgrChartJFrame extends ChartJFrame {
    private static final long serialVersionUID = 1L;
    private XYSeriesCollection dataset = null;

    public LinRgrChartJFrame() {
    }

    public LinRgrChartJFrame(XYSeries... seriesList) {
	this.dataset = ChartUtils.getXYSeriesCollection(seriesList);
    }

    public XYSeriesCollection getDataset() {
	return dataset;
    }

    public void setDataset(XYSeriesCollection dataset) {
	this.dataset = dataset;
    }

}
