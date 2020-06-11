package io.memoria.legacyjann.test.unit.chart.jframe;

import io.memoria.legacyjann.chart.LineJf;
import io.memoria.jann.MatrixUtils;
import org.jfree.data.xy.XYSeries;

import static io.memoria.legacyjann.chart.JChartUtils.xySeries;

public class LinRgrFrameTest {

  public static void main(String[] args) {
    XYSeries xys = xySeries("Training", MatrixUtils.range(0, 1, 1500), MatrixUtils.range(0, 2, 3000));
    LineJf frame = new LineJf(xys);
    frame.setxAxisTitle("x");
    frame.setyAxisTitle("y");
    frame.setApptitle("My app");
    frame.run();
  }
}
