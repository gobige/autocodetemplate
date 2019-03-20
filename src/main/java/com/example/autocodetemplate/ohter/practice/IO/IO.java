package com.example.autocodetemplate.ohter.practice.IO;

import java.io.*;

public class IO {
    public static void main(String[] args) {
		try(OutputStream outputStream = new FileOutputStream("d:\\test.txt");
            InputStream inputStream = new FileInputStream("d:\\test2.txt");) {
			writeByteTofile(outputStream);

			byte[] b = readByteTofileByBuf(inputStream);

		  System.out.println(b[2] >= 0 ? b[2] : b[2] + 256);
		} catch (Exception e) {

		}

        try {
            OutputStream OS = new FileOutputStream("d:\\test.txt");

            OutputStreamWriter osw = new OutputStreamWriter(OS, "latin1");
            osw.write("杨超");
            osw.write("\r\n");
            osw.write("yates");
            osw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] readByteTofileByBuf(InputStream inputStream) throws IOException {
        int t;
        byte[] bytes = new byte[200];
        t = inputStream.read(bytes);
        return bytes;
    }

    public static byte[] readByteTofile(InputStream inputStream) throws IOException {
        int t;
        byte[] bytes = new byte[10];
        for (int i = 0; i < bytes.length; i++) {
            if ((t = inputStream.read()) == -1) {
                break;

            }
            bytes[i] = (byte) t;
        }
        return bytes;
    }

    public static void writeByteTofile(OutputStream out) throws IOException {
        byte[] b = new byte[100];
        b[99] = '!';
        b[1] = 'y';
        b[2] = (byte) 255;
        out.write(b);
        out.flush();
    }


}
