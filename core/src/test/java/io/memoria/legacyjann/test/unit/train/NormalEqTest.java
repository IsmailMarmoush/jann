package io.memoria.legacyjann.test.unit.train;

import io.memoria.legacyjann.test.TestingData;
import io.memoria.jann.TrainUtils;
import org.jblas.DoubleMatrix;

import java.io.IOException;

public class NormalEqTest {

  public void normalEqPinvRgu() throws IOException {
    DoubleMatrix inputs;
    DoubleMatrix targets;
    String path = TestingData.getPath("", "data.txt");
    DoubleMatrix data = DoubleMatrix.loadAsciiFile(path);
    inputs = data.getColumns(new int[]{0, 1, 2});
    targets = data.getColumn(3);
    TrainUtils.normalEqPinv(inputs, targets).print();
    TrainUtils.normalEqPinvRgu(inputs, targets, 10, true).print();
  }
}
