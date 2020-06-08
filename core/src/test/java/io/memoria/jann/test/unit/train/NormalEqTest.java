package io.memoria.jann.test.unit.train;

import io.memoria.jann.test.TestingData;
import io.memoria.jann.utils.TrainUtils;
import org.jblas.DoubleMatrix;
import org.junit.Before;
import org.junit.Test;

public class NormalEqTest {

  DoubleMatrix inputs;

  DoubleMatrix targets;

  @Test
  public void normalEqPinvRgu() {
    TrainUtils.normalEqPinv(inputs, targets).print();
    TrainUtils.normalEqPinvRgu(inputs, targets, 10, true).print();

  }

  @Before
  public void setUp() throws Exception {
    String path = TestingData.getPath("ex2", "ex2data1Bias.txt");
    DoubleMatrix data = DoubleMatrix.loadAsciiFile(path);
    inputs = data.getColumns(new int[]{0, 1, 2});
    targets = data.getColumn(3);

  }
}
