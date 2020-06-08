package io.memoria.jann.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;

public class LineJf extends ChartJFrame {

  private static final long serialVersionUID = -6259803593823138950L;

  public LineJf() {
  }

  public LineJf(XYSeries... seriesList) {
    setXySeriesCollection(JChartUtils.getXYSeriesCollection(seriesList));

  }

  @Override
  public void run() {
    JFreeChart chart = ChartFactory.createXYLineChart(getTitle(),
                                                      getxAxisTitle(),
                                                      getyAxisTitle(),
                                                      getXySeriesCollection(),
                                                      getOrientation(),
                                                      isLegend(),
                                                      isTooltips(),
                                                      isUrls());
    setChart(chart);
    super.run();
  }
}
