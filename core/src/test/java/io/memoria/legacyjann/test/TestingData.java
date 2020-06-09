package io.memoria.legacyjann.test;

import java.io.File;

public class TestingData {

  public static final String DATA_FOLDER = "src.test.java";

  public static String getPath(String folder, String fileName) {
    return toRelativePath(DATA_FOLDER + "." + folder, fileName);
  }

  public static String toRelativePath(String classPath, String fileName) {
    String[] bits = classPath.split("\\.");
    String path = "";
    for (int i = 0; i < bits.length; i++) {
      path += bits[i] + File.separator;
    }
    path += fileName;
    return path;
  }

  public static void main(String[] args) {
    String path = toRelativePath(DATA_FOLDER + ".ex1", "ex1data1.txt");
    System.out.println(path);
  }
}
