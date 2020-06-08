package io.memoria.jann.test.unit.train;

import io.memoria.jann.test.TestingData;
import io.memoria.jann.utils.TrainUtils;
import org.jblas.DoubleMatrix;
import org.junit.jupiter.api.Test;

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
