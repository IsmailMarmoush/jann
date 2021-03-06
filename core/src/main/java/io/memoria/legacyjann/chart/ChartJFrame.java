package io.memoria.legacyjann.chart;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public abstract class ChartJFrame extends JFrame {

  private static final long serialVersionUID = -7922555494678114867L;

  private String apptitle = "JANN Chart";

  private JFreeChart chart = null;

  private int height = 600;

  private boolean legend = false;

  private PlotOrientation orientation = PlotOrientation.VERTICAL;

  private boolean tooltips = false;

  private boolean urls = false;

  private int width = 800;

  private String xAxisTitle = "X-Axis";

  private XYSeriesCollection xySeriesCollection = null;

  private String yAxisTitle = "Y-Axis";

  public String getApptitle() {
    return apptitle;
  }

  public void setApptitle(String apptitle) {
    this.apptitle = apptitle;
  }

  public JFreeChart getChart() {
    return chart;
  }

  public void setChart(JFreeChart chart) {
    this.chart = chart;
  }

  public PlotOrientation getOrientation() {
    return orientation;
  }

  public void setOrientation(PlotOrientation orientation) {
    this.orientation = orientation;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public String getxAxisTitle() {
    return xAxisTitle;
  }

  public void setxAxisTitle(String xTitle) {
    this.xAxisTitle = xTitle;
  }

  public XYSeriesCollection getXySeriesCollection() {
    return xySeriesCollection;
  }

  public void setXySeriesCollection(XYSeriesCollection xySeriesCollection) {
    this.xySeriesCollection = xySeriesCollection;
  }

  public String getyAxisTitle() {
    return yAxisTitle;
  }

  public void setyAxisTitle(String yTitle) {
    this.yAxisTitle = yTitle;
  }

  public boolean isLegend() {
    return legend;
  }

  public void setLegend(boolean legend) {
    this.legend = legend;
  }

  public boolean isTooltips() {
    return tooltips;
  }

  public void setTooltips(boolean tooltips) {
    this.tooltips = tooltips;
  }

  public boolean isUrls() {
    return urls;
  }

  public void setUrls(boolean urls) {
    this.urls = urls;
  }

  public void run() {
    Dimension die = new Dimension(width, height);
    setPreferredSize(die);
    ChartPanel chartPanel = new ChartPanel(chart);
    chartPanel.setPreferredSize(die);
    setTitle(apptitle);
    setContentPane(chartPanel);
    pack();
    setVisible(true);
  }

}
