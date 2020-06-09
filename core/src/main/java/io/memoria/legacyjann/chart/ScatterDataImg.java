package io.memoria.legacyjann.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;

public class ScatterDataImg extends ChartImage {

  public ScatterDataImg(String path, XYSeries... series) {
    setPath(path);
    setXySeriesCollec(JChartUtils.getXYSeriesCollection(series));
  }

  @Override
  public void createJPEG() {
    JFreeChart chart = ChartFactory.createScatterPlot(getTitle(),
                                                      getxAxisTitle(),
                                                      getyAxisTitle(),
                                                      getXySeriesCollec(),
                                                      getOrientation(),
                                                      isLegend(),
                                                      isTooltips(),
                                                      isUrls());
    setChart(chart);
    super.createJPEG();
  }

  @Override
  public void createPNG() {
    JFreeChart chart = ChartFactory.createScatterPlot(getTitle(),
                                                      getxAxisTitle(),
                                                      getyAxisTitle(),
                                                      getXySeriesCollec(),
                                                      getOrientation(),
                                                      isLegend(),
                                                      isTooltips(),
                                                      isUrls());
    setChart(chart);
    super.createPNG();
  }
}
