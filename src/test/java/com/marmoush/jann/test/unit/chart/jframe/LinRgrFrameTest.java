package com.marmoush.jann.test.unit.chart.jframe;

import org.jfree.data.xy.XYSeries;

import com.marmoush.jann.chart.ChartUtils;
import com.marmoush.jann.chart.jframe.LinRgrChartJFrame;
import com.marmoush.jann.utils.MatrixUtils;

public class LinRgrFrameTest {
    public static void main(String[] args) {
	XYSeries xys = ChartUtils.xySeries("Training",
		MatrixUtils.range(0, 1, 1500), MatrixUtils.range(0, 2, 3000));
	new LinRgrChartJFrame("Training", xys);
    }
}
