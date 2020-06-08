package io.memoria.jann.test.unit.chart.jframe;

import chart.jframe.LineJf;
import io.memoria.jann.utils.MatrixUtils;
import org.jfree.data.xy.XYSeries;

import chart.ChartUtils;

public class LinRgrFrameTest {

  public static void main(String[] args) {
    XYSeries xys = ChartUtils.xySeries("Training", MatrixUtils.range(0, 1, 1500), MatrixUtils.range(0, 2, 3000));
    LineJf frame = new LineJf(xys);
    frame.setxAxisTitle("x");
    frame.setyAxisTitle("y");
    frame.setApptitle("My app");
    frame.run();
  }
}
