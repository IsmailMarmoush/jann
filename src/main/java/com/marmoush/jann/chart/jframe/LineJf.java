/*
 * Copyright 2012 Ismail Marmoush
 * 
 * This file is part of JANN.
 * 
 * JANN is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License Version 3 as published by the Free
 * Software Foundation, either version 3 of the License, or any later version.
 * 
 * JANN is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * JANN. If not, see http://www.gnu.org/licenses/.
 * 
 * For More Information Please Visit http://jann.marmoush.com
 */
package com.marmoush.jann.chart.jframe;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;

import com.marmoush.jann.chart.ChartUtils;

public class LineJf extends ChartJFrame {

    private static final long serialVersionUID = -6259803593823138950L;

    public LineJf() {
    }

    public LineJf(XYSeries... seriesList) {
	setXySeriesCollection(ChartUtils.getXYSeriesCollection(seriesList));

    }

    @Override
    public void run() {
	JFreeChart chart = ChartFactory.createXYLineChart(getTitle(),
		getxAxisTitle(), getyAxisTitle(), getXySeriesCollection(),
		getOrientation(), isLegend(), isTooltips(), isUrls());
	setChart(chart);
	super.run();
    }
}
