package io.memoria.legacyjann.chart;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.File;
import java.io.IOException;

import static org.jfree.chart.ChartUtils.saveChartAsJPEG;
import static org.jfree.chart.ChartUtils.saveChartAsPNG;

public abstract class ChartImage {

  private JFreeChart chart = null;

  private int height = 600;

  private boolean legend = false;

  private PlotOrientation orientation = PlotOrientation.VERTICAL;

  private String path = null;

  private String title = "JANN Chart";

  private boolean tooltips = false;

  private boolean urls = false;

  private int width = 800;

  private String xAxisTitle = "X-Axis";

  private XYSeriesCollection xySeriesCollec = null;

  private String yAxisTitle = "Y-Axis";

  public void createJPEG() {
    try {
      File file = new File(path);
      saveChartAsJPEG(file, chart, width, height);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void createPNG() {
    try {
      File file = new File(path);
      saveChartAsPNG(file, chart, width, height);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public JFreeChart getChart() {
    return chart;
  }

  public void setChart(JFreeChart chart) {
    this.chart = chart;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public PlotOrientation getOrientation() {
    return orientation;
  }

  public void setOrientation(PlotOrientation orientation) {
    this.orientation = orientation;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getWidth() {
    return width;
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

  public XYSeriesCollection getXySeriesCollec() {
    return xySeriesCollec;
  }

  public void setXySeriesCollec(XYSeriesCollection dataset) {
    this.xySeriesCollec = dataset;
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

}
