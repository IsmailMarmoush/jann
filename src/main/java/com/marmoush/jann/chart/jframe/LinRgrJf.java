package com.marmoush.jann.chart.jframe;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;

import com.marmoush.jann.chart.ChartUtils;

public class LinRgrJf extends ChartJFrame {

    private static final long serialVersionUID = -6259803593823138950L;

    public LinRgrJf() {
    }

    public LinRgrJf(XYSeries... seriesList) {
	setXySeriesCollection(ChartUtils.getXYSeriesCollection(seriesList));

    }

    @Override
    public void run() {
	JFreeChart chart = ChartFactory.createXYLineChart(getTitle(),
		getxAxisTitle(), getyAxisTitle(), getXySeriesCollection(),
		getOrientation(), isLegend(), isTooltips(), isUrls());
	setChart(chart);
	super.run();
    }
}
