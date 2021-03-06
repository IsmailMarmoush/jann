package io.memoria.legacyjann.test.unit.train;

import io.memoria.legacyjann.chart.LineImg;
import io.memoria.legacyjann.model.regression.LinearRegression;
import io.memoria.legacyjann.train.Train;
import io.memoria.legacyjann.utils.MatrixUtils;
import io.memoria.legacyjann.utils.TrainUtils;
import io.memoria.legacyjann.utils.functors.IPerformance;
import org.jblas.DoubleMatrix;
import org.jfree.data.xy.XYSeries;

import java.io.File;
import java.util.List;

import static io.memoria.legacyjann.chart.JChartUtils.xySeries;

public class LingRgrTrainTest {

  private final List<DoubleMatrix> inputList = null;

  private final DoubleMatrix inputs = null;

  private final List<DoubleMatrix> targetList = null;

  private final DoubleMatrix targets = null;

  public void createImage() {
    List<Double> range = MatrixUtils.range(0, 1, 10);
    List<Double> batchErr = trainBatchLinRgr();
    XYSeries xyBatch = xySeries("Batch", range, batchErr);
    List<Double> stochErr = trainStochasticLinRgr();
    XYSeries xyStoch = xySeries("Stochastic", range, stochErr);
    String path = "ChartsOutput" + File.separator + "chart.png";
    LineImg img = new LineImg(path, xyBatch, xyStoch);
    img.setxAxisTitle("Iterations");
    img.setyAxisTitle("Error");
    img.createPNG();
  }

  public List<Double> trainBatchLinRgr() {
    System.out.println();
    System.out.println("Batch Training");
    final LinearRegression blr = new LinearRegression(inputs, targets, true);
    blr.setFill(1, blr.getWeight(), blr.getBias());
    blr.setLearnRate(0.01);
    blr.setReguFctr(1);
    blr.setPerformancefnctr(IPerformance.LINRGR_RGU);
    Train training = new Train(1500, 0.001, 1000) {
      @Override
      public double train() {
        TrainUtils.batchGd(blr, inputs, targets);
        return blr.getPerformance();
      }
    };
    training.run();
    System.out.println(training);
    blr.getBias().print();
    blr.getWeight().print();
    return training.getPerformanceHistory();
  }

  public List<Double> trainStochasticLinRgr() {
    System.out.println();
    System.out.println("Stochastic Trainging ");
    final LinearRegression slr = new LinearRegression(inputList, targetList, true);
    slr.setFill(1, slr.getWeight(), slr.getBias());
    slr.setLearnRate(0.01);
    Train training = new Train(10, 0.001, 1000) {
      @Override
      public double train() {
        TrainUtils.stochasticGd(slr, inputList, targetList);
        return slr.getPerformance();
      }
    };
    training.run();
    System.out.println(training);
    return training.getPerformanceHistory();
  }

}
