/*
 * Copyright 2012 Ismail Marmoush This file is part of JANN. JANN is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * General Public License Version 3 as published by the Free Software
 * Foundation, either version 3 of the License, or any later version. JANN is
 * distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details. You
 * should have received a copy of the GNU General Public License along with
 * JANN. If not, see http://www.gnu.org/licenses/. For More Information Please
 * Visit http://jann.marmoush.com
 */
package com.marmoush.jann.test.unit.chart.jframe;

import org.jfree.data.xy.XYSeries;

import com.marmoush.jann.chart.ChartUtils;
import com.marmoush.jann.chart.jframe.LineJf;
import com.marmoush.jann.utils.MatrixUtils;


/**
 * 
 */
public class LinRgrFrameTest {

    /**
     * 
     *
     * @param args 
     */
    public static void main(String[] args) {
	XYSeries xys = ChartUtils.xySeries("Training",
		MatrixUtils.range(0, 1, 1500), MatrixUtils.range(0, 2, 3000));
	LineJf frame = new LineJf(xys);
	frame.setxAxisTitle("x");
	frame.setyAxisTitle("y");
	frame.setApptitle("My app");
	frame.run();
    }
}
