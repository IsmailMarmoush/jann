package com.marmoush.jann.chart.jframe;

import javax.swing.JFrame;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.marmoush.jann.chart.ChartUtils;
import com.marmoush.jann.chart.LinRgrChartUtils;

public class LinRgrChartJFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    public LinRgrChartJFrame(String title, JFreeChart chart) {
	super(title);
	// we put the chart into a panel
	ChartPanel chartPanel = new ChartPanel(chart);
	// default size
	chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
	// add it to our application
	setContentPane(chartPanel);
	pack();
	setVisible(true);
    }

    public LinRgrChartJFrame(String title, XYSeries... series) {
	super(title);
	XYSeriesCollection xySeriesCollection = ChartUtils
		.getXYSeriesCollection(series);
	JFreeChart chart = LinRgrChartUtils.getLinRgrChart(title, title,
		xySeriesCollection);
	// we put the chart into a panel
	ChartPanel chartPanel = new ChartPanel(chart);
	// default size
	chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
	// add it to our application
	setContentPane(chartPanel);
	pack();
	setVisible(true);
    }
}
