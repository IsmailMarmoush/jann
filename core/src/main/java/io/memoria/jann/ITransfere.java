package io.memoria.jann;

import io.memoria.legacyjann.Layer;
import io.memoria.legacyjann.utils.functors.IFunctionable;
import org.jblas.DoubleMatrix;

public interface ITransfere extends IFunctionable {

  public static final ITransfere COMPET = new ITransfere() {
    @Override
    public String toString() {
      return "COMPET";
    }

    @Override
    public DoubleMatrix transfere(Layer layer) {
      return TransfereUtils.compet(layer.getNetSum());
    }
  };

  public static final ITransfere HARDLIM = new ITransfere() {
    @Override
    public String toString() {
      return "HARDLIM";
    }

    @Override
    public DoubleMatrix transfere(Layer layer) {
      return TransfereUtils.hardlim(layer.getNetSum(), layer.getTheta());
    }
  };

  public static final ITransfere HARDLIMS = new ITransfere() {
    @Override
    public String toString() {
      return "HARDLIMS";
    }

    @Override
    public DoubleMatrix transfere(Layer layer) {
      return TransfereUtils.hardlims(layer.getNetSum(), layer.getTheta());
    }
  };

  public static final ITransfere LOGSIG = new ITransfere() {
    @Override
    public String toString() {
      return "LOGSIG";
    }

    @Override
    public DoubleMatrix transfere(Layer layer) {
      return TransfereUtils.logsig(layer.getNetSum());
    }
  };

  public static final ITransfere LOGSIGDIFF = new ITransfere() {
    @Override
    public String toString() {
      return "LOGSIGDIFF";
    }

    @Override
    public DoubleMatrix transfere(Layer layer) {
      return TransfereUtils.logsigDiff(layer.getNetSum());
    }
  };

  public static final ITransfere POSLIN = new ITransfere() {
    @Override
    public String toString() {
      return "POSLIN";
    }

    @Override
    public DoubleMatrix transfere(Layer layer) {
      return TransfereUtils.poslin(layer.getNetSum(), layer.getTheta());
    }
  };

  public static final ITransfere PURELIN = new ITransfere() {
    @Override
    public String toString() {
      return "PURELIN";
    }

    @Override
    public DoubleMatrix transfere(Layer layer) {
      return TransfereUtils.purelin(layer.getNetSum());
    }
  };

  public static final ITransfere SATLIN = new ITransfere() {
    @Override
    public String toString() {
      return "SATLIN";
    }

    @Override
    public DoubleMatrix transfere(Layer layer) {
      return TransfereUtils.satlin(layer.getNetSum());
    }
  };

  public static final ITransfere SATLINS = new ITransfere() {
    @Override
    public String toString() {
      return "SATLINS";
    }

    @Override
    public DoubleMatrix transfere(Layer layer) {
      return TransfereUtils.satlins(layer.getNetSum());
    }
  };

  public static final ITransfere TANSIG = new ITransfere() {
    @Override
    public String toString() {
      return "TANSIG";
    }

    @Override
    public DoubleMatrix transfere(Layer layer) {
      return TransfereUtils.tansig(layer.getNetSum());
    }
  };

  public static final ITransfere TANSIGDIFF = new ITransfere() {
    @Override
    public String toString() {
      return "TANSIGDIFF";
    }

    @Override
    public DoubleMatrix transfere(Layer layer) {
      return TransfereUtils.tansigDiff(layer.getNetSum());
    }
  };

  public DoubleMatrix transfere(Layer layer);
}
