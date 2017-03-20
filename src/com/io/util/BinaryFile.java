package com.io.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * to read the binary file
 *  
 */
public class BinaryFile {

	/* the parameter is a file */
	public static byte[] read(File file) throws IOException {
		BufferedInputStream bf = new BufferedInputStream(new FileInputStream(
				file));
		try {
			byte[] data = new byte[bf.available()];
			bf.read(data);
			return data;
		} finally {
			bf.close();
		}
	}

	/* the @param is the path of a file */
	public static byte[] read(String file) throws IOException {
		return read(new File(file).getAbsoluteFile());
	}
}