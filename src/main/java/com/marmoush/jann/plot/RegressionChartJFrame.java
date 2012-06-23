package com.marmoush.jann.plot;

import javax.swing.JFrame;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

public class RegressionChartJFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    public RegressionChartJFrame(JFreeChart chart, String applicationTitle) {
	super(applicationTitle);
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
