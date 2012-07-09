/*
 * Copyright 2011 Ismail Marmoush This file is part of JANN. JANN is free
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
package com.marmoush.jann.chart;

import java.util.List;
import org.jblas.DoubleMatrix;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ChartUtils {

    public static XYSeriesCollection getXYSeriesCollection(
	    XYSeries... seriesList) {
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

    public static XYSeries xySeries(String key, DoubleMatrix xyMtrx,
	    int xColIdx, int yColdIdx) {

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
