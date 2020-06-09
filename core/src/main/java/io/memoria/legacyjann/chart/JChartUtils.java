package io.memoria.legacyjann.chart;

import org.jblas.DoubleMatrix;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.util.List;

public class JChartUtils {

  public static XYSeriesCollection getXYSeriesCollection(XYSeries... seriesList) {
    XYSeriesCollection dataset = new XYSeriesCollection();

    for (XYSeries xySeries : seriesList) {

      dataset.addSeries(xySeries);
    }
    return dataset;
  }

  public static XYSeries xySeries(String key, double[][] data) {
    XYSeries series = new XYSeries(key);
    for (int row = 0; row < data.length; row++) {
      series.add(data[row][0], data[row][1]);
    }
    return series;
  }

  public static XYSeries xySeries(String key, DoubleMatrix x, DoubleMatrix y) {
    XYSeries series = new XYSeries(key);
    for (int idx = 0; idx < x.length; idx++) {
      series.add(x.get(idx), y.get(idx));
    }
    return series;
  }

  public static XYSeries xySeries(String key, DoubleMatrix xyMtrx, int xColIdx, int yColdIdx) {

    XYSeries series = new XYSeries(key);
    for (int row = 0; row < xyMtrx.rows; row++) {
      series.add(xyMtrx.get(row, xColIdx), xyMtrx.get(row, yColdIdx));
    }
    return series;
  }

  public static XYSeries xySeries(String key, List<Double> x, List<Double> y) {
    // assert (x.size()==y.size();
    XYSeries series = new XYSeries(key);
    for (int row = 0; row < x.size(); row++) {
      series.add(x.get(row), y.get(row));
    }
    return series;
  }
}
