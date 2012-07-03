package com.marmoush.jann.chart.jframe;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;

import com.marmoush.jann.chart.ChartUtils;

public class ScatterDataJf extends ChartJFrame {
    private static final long serialVersionUID = -457908141851955661L;

    public ScatterDataJf() {
    }

    public ScatterDataJf(XYSeries... seriesList) {
	setXySeriesCollection(ChartUtils.getXYSeriesCollection(seriesList));
    }

    @Override
    public void run() {
	JFreeChart chart = ChartFactory.createScatterPlot(getTitle(),
		getxAxisTitle(), getyAxisTitle(), getXySeriesCollection(),
		getOrientation(), isLegend(), isTooltips(), isUrls());
	setChart(chart);
	super.run();
    }

}
