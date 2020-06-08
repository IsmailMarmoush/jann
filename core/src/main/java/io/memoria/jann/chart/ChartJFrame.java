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
package io.memoria.jann.chart;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public abstract class ChartJFrame extends JFrame {

  private static final long serialVersionUID = -7922555494678114867L;

  private String apptitle = "JANN Chart";

  private JFreeChart chart = null;

  private int height = 600;

  private boolean legend = false;

  private PlotOrientation orientation = PlotOrientation.VERTICAL;

  private boolean tooltips = false;

  private boolean urls = false;

  private int width = 800;

  private String xAxisTitle = "X-Axis";

  private XYSeriesCollection xySeriesCollection = null;

  private String yAxisTitle = "Y-Axis";

  public String getApptitle() {
    return apptitle;
  }

  public void setApptitle(String apptitle) {
    this.apptitle = apptitle;
  }

  public JFreeChart getChart() {
    return chart;
  }

  public void setChart(JFreeChart chart) {
    this.chart = chart;
  }

  public PlotOrientation getOrientation() {
    return orientation;
  }

  public void setOrientation(PlotOrientation orientation) {
    this.orientation = orientation;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public String getxAxisTitle() {
    return xAxisTitle;
  }

  public void setxAxisTitle(String xTitle) {
    this.xAxisTitle = xTitle;
  }

  public XYSeriesCollection getXySeriesCollection() {
    return xySeriesCollection;
  }

  public void setXySeriesCollection(XYSeriesCollection xySeriesCollection) {
    this.xySeriesCollection = xySeriesCollection;
  }

  public String getyAxisTitle() {
    return yAxisTitle;
  }

  public void setyAxisTitle(String yTitle) {
    this.yAxisTitle = yTitle;
  }

  public boolean isLegend() {
    return legend;
  }

  public void setLegend(boolean legend) {
    this.legend = legend;
  }

  public boolean isTooltips() {
    return tooltips;
  }

  public void setTooltips(boolean tooltips) {
    this.tooltips = tooltips;
  }

  public boolean isUrls() {
    return urls;
  }

  public void setUrls(boolean urls) {
    this.urls = urls;
  }

  public void run() {
    Dimension die = new Dimension(width, height);
    setPreferredSize(die);
    ChartPanel chartPanel = new ChartPanel(chart);
    chartPanel.setPreferredSize(die);
    setTitle(apptitle);
    setContentPane(chartPanel);
    pack();
    setVisible(true);
  }

}
