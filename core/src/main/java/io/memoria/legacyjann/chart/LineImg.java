package io.memoria.legacyjann.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;

import static io.memoria.legacyjann.chart.JChartUtils.getXYSeriesCollection;

public class LineImg extends ChartImage {

  public LineImg(String path, XYSeries... series) {
    setPath(path);
    setXySeriesCollec(getXYSeriesCollection(series));

  }

  @Override
  public void createJPEG() {
    JFreeChart chart = ChartFactory.createXYLineChart(getTitle(),
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
    JFreeChart chart = ChartFactory.createXYLineChart(getTitle(),
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
