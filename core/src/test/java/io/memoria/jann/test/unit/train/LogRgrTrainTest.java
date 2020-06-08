package io.memoria.jann.test.unit.train;

import io.memoria.jann.chart.LineImg;
import io.memoria.jann.chart.ScatterDataImg;
import io.memoria.jann.model.regression.LogisticRegression;
import io.memoria.jann.train.Train;
import io.memoria.jann.utils.MatrixUtils;
import io.memoria.jann.utils.TrainUtils;
import org.jblas.DoubleMatrix;
import org.jfree.data.xy.XYSeries;

import java.io.File;
import java.util.List;

import static io.memoria.jann.chart.JChartUtils.xySeries;

public class LogRgrTrainTest {

  private final DoubleMatrix data = null;
  private final List<DoubleMatrix> inputList = null;
  private final DoubleMatrix inputs = null;
  private final List<DoubleMatrix> targetList = null;
  private final DoubleMatrix targets = null;

  public void createDataImg() {
    String path = "ChartsOutput" + File.separator + "data.txt.png";
    DoubleMatrix d1 = DoubleMatrix.zeros(data.rows, 2);
    DoubleMatrix d2 = DoubleMatrix.zeros(data.rows, 2);
    for (int i = 0; i < data.rows; i++) {
      if (data.get(i, 2) == 1.0) {
        d1.put(i, 0, data.get(i, 0));
        d1.put(i, 1, data.get(i, 1));
      } else {
        d2.put(i, 0, data.get(i, 0));
        d2.put(i, 1, data.get(i, 1));
      }

    }
    XYSeries xys = xySeries("Admitted", d1, 0, 1);
    XYSeries xys2 = xySeries("Not Admitted", d2, 0, 1);
    ScatterDataImg img = new ScatterDataImg(path, xys, xys2);
    img.setLegend(true);
    img.setxAxisTitle("exam1 score");
    img.setyAxisTitle("exam2 score");
    img.createPNG();
  }

  public void createErrorIterImage() {
    List<Double> range = MatrixUtils.range(0, 1, 400);
    List<Double> stochErr = trainStochasticLinRgr();
    List<Double> batchErr = trainBatchLogRgr();
    XYSeries xyStoch = xySeries("Stochastic", range, stochErr);
    XYSeries xyBatch = xySeries("Batch", range, batchErr);
    String path = "ChartsOutput" + File.separator + "chart2.png";
    LineImg img = new LineImg(path, xyBatch, xyStoch);
    img.setxAxisTitle("Iterations");
    img.setyAxisTitle("Error");
    img.createPNG();

  }

  public List<Double> trainStochasticLinRgr() {
    System.out.println("Stochastic Trainging ");
    final LogisticRegression slr = new LogisticRegression(inputList, targetList, true);
    slr.setLearnRate(0.0001);
    Train training = new Train(400, 0.001, 1000) {
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

  public List<Double> trainBatchLogRgr() {
    System.out.println("Batch Training");
    final LogisticRegression blr = new LogisticRegression(inputs, targets, true);

    blr.setLearnRate(0.0001);
    Train batchTrain = new Train(1500, 0.001, 1000) {
      @Override
      public double train() {
        TrainUtils.batchGd(blr, inputs, targets);
        return blr.getPerformance();
      }
    };
    batchTrain.run();
    System.out.println(batchTrain);
    // blr.getBias().print();
    blr.getWeight().print();

    return batchTrain.getPerformanceHistory();
  }
}
