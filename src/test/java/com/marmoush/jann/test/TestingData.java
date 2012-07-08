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
package com.marmoush.jann.test;

import java.io.File;

public class TestingData {

    public static final String DATA_FOLDER = "src.test.java.data";

    
    public static String getPath(String folder, String fileName) {
	return toRelativePath(DATA_FOLDER + "." + folder, fileName);
    }

    public static void main(String[] args) {
	String path = toRelativePath(DATA_FOLDER + ".ex1", "ex1data1.txt");
	System.out.println(path);
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
}
