package com.marmoush.jann.plot;

import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class RegressionChart extends JFrame {
    private static final long serialVersionUID = 1L;

    public RegressionChart(String applicationTitle, String chartTitle) {
	super(applicationTitle);
	// This will create the dataset
	XYSeriesCollection dataset = createDataset();
	// based on the dataset we create the chart
	JFreeChart chart = createChart(dataset, chartTitle);
	// we put the chart into a panel
	ChartPanel chartPanel = new ChartPanel(chart);
	// default size
	chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
	// add it to our application
	setContentPane(chartPanel);

	try {
	    File file = new File("ChartsOutput" + File.separator + "chart.jpg");
	    ChartUtilities.saveChartAsJPEG(file, chart, 500, 300);
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

    /**
     * Creates a sample dataset
     */
    private XYSeriesCollection createDataset() {
	XYSeries series = new XYSeries("XYGraph");
	series.add(1, 1);
	series.add(1, 2);
	series.add(2, 1);
	series.add(3, 9);
	series.add(4, 10);
	// Add the series to your data set
	XYSeriesCollection dataset = new XYSeriesCollection();
	dataset.addSeries(series);
	return dataset;

    }

    /**
     * Creates a chart
     */

    private JFreeChart createChart(XYSeriesCollection dataset, String title) {

	// Generate the graph
	JFreeChart chart = ChartFactory.createXYLineChart(title, "x-axis",
		"y-axis", dataset, PlotOrientation.VERTICAL, true, // Show
								   // Legend
		true, // Use tooltips
		false // Configure chart to generate URLs?
		);
	return chart;

    }
}
