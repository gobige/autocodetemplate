package com.example.autocodetemplate.util;

import org.apache.http.HttpRequest;
import org.apache.http.client.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 * Simple utility methods for file and stream copying.
 * All copy methods use a block size of 4096 bytes,
 * and close all affected streams when done.
 *
 * <p>Mainly for use within the framework,
 * but also useful for application code.
 *
 * @author John Summer
 */
public abstract class CopyFileUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static final int BUFFER_SIZE = 4096;

    /**
     * judge whether it's exist
     *
     * @return boolean
     */
    public static boolean isExist(String filepath) {
        File file = new File(filepath);
        return file.exists();
    }

    public static void copy(String fileURL, String targetFile) {
        URLConnection urlconn = null;
        try {
            URL url = new URL(fileURL);
            urlconn = url.openConnection();
            InputStream in = urlconn.getInputStream();

            File newfile = new File(targetFile);
            FileOutputStream fos = new FileOutputStream(newfile);
            FileCopyUtils.copy(in, fos);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Copy the contents of the given input File to the given output File.
     *
     * @param in  the file to copy from
     * @param out the file to copy to
     * @return the number of bytes copied
     * @throws IOException in case of I/O errors
     */
    public static int copy(File in, File out) throws IOException {
        Assert.notNull(in, "No input File specified");
        Assert.notNull(out, "No output File specified");
        return copy(new BufferedInputStream(new FileInputStream(in)),
                new BufferedOutputStream(new FileOutputStream(out)));
    }

    /**
     * Copy the contents of the given byte array to the given output File.
     *
     * @param in  the byte array to copy from
     * @param out the file to copy to
     * @throws IOException in case of I/O errors
     */
    public static void copy(byte[] in, File out) throws IOException {
        Assert.notNull(in, "No input byte array specified");
        Assert.notNull(out, "No output File specified");
        ByteArrayInputStream inStream = new ByteArrayInputStream(in);
        OutputStream outStream = new BufferedOutputStream(new FileOutputStream(out));
        copy(inStream, outStream);
    }

    /**
     * Copy the contents of the given input File into a new byte array.
     *
     * @param in the file to copy from
     * @return the new byte array that has been copied to
     * @throws IOException in case of I/O errors
     */
    public static byte[] copyToByteArray(File in) throws IOException {
        Assert.notNull(in, "No input File specified");
        return copyToByteArray(new BufferedInputStream(new FileInputStream(in)));
    }

    /**
     * Copy the contents of the given InputStream to the given OutputStream.
     * Closes both streams when done.
     *
     * @param in  the stream to copy from
     * @param out the stream to copy to
     * @return the number of bytes copied
     * @throws IOException in case of I/O errors
     */
    public static int copy(InputStream in, OutputStream out) throws IOException {
        Assert.notNull(in, "No InputStream specified");
        Assert.notNull(out, "No OutputStream specified");
        try {
            int byteCount = 0;
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
                byteCount += bytesRead;
            }
            out.flush();
            return byteCount;
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                logger.warn("Could not close InputStream", ex);
            }
            try {
                out.close();
            } catch (IOException ex) {
                logger.warn("Could not close OutputStream", ex);
            }
        }
    }

    /**
     * Copy the contents of the given byte array to the given OutputStream.
     * Closes the stream when done.
     *
     * @param in  the byte array to copy from
     * @param out the OutputStream to copy to
     * @throws IOException in case of I/O errors
     */
    public static void copy(byte[] in, OutputStream out) throws IOException {
        Assert.notNull(in, "No input byte array specified");
        Assert.notNull(out, "No OutputStream specified");
        try {
            out.write(in);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                logger.warn("Could not close OutputStream", ex);
            }
        }
    }

    /**
     * Copy the contents of the given InputStream into a new byte array.
     * Closes the stream when done.
     *
     * @param in the stream to copy from
     * @return the new byte array that has been copied to
     * @throws IOException in case of I/O errors
     */
    public static byte[] copyToByteArray(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream(BUFFER_SIZE);
        copy(in, out);
        return out.toByteArray();
    }

    /**
     * Copy the contents of the given Reader to the given Writer.
     * Closes both when done.
     *
     * @param in  the Reader to copy from
     * @param out the Writer to copy to
     * @return the number of characters copied
     * @throws IOException in case of I/O errors
     */
    public static int copy(Reader in, Writer out) throws IOException {
        Assert.notNull(in, "No Reader specified");
        Assert.notNull(out, "No Writer specified");
        try {
            int byteCount = 0;
            char[] buffer = new char[BUFFER_SIZE];
            int bytesRead = -1;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
                byteCount += bytesRead;
            }
            out.flush();
            return byteCount;
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                logger.warn("Could not close Reader", ex);
            }
            try {
                out.close();
            } catch (IOException ex) {
                logger.warn("Could not close Writer", ex);
            }
        }
    }

    /**
     * Copy the contents of the given String to the given output Writer.
     * Closes the write when done.
     *
     * @param in  the String to copy from
     * @param out the Writer to copy to
     * @throws IOException in case of I/O errors
     */
    public static void copy(String in, Writer out) throws IOException {
        Assert.notNull(in, "No input String specified");
        Assert.notNull(out, "No Writer specified");
        try {
            out.write(in);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                logger.warn("Could not close Writer", ex);
            }
        }
    }

    /**
     * Copy the contents of the given Reader into a String.
     * Closes the reader when done.
     *
     * @param in the reader to copy from
     * @return the String that has been copied to
     * @throws IOException in case of I/O errors
     */
    public static String copyToString(Reader in) throws IOException {
        StringWriter out = new StringWriter();
        copy(in, out);
        return out.toString();
    }

    public static void main(String[] args) throws IOException{
        File file1 = new File("c:/暂存/getset.txt");
        File file2 = new File("c:/暂存/copy.txt");
         try {
            CopyFileUtil.copyFileByChannel(file1, file2);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        int test = 221_121_121;
    }

    /**
     * 基于NIO的transform和to方式拷贝文件，比input/outputStream要快些
     * @param source
     * @param target
     * @throws IOException
     */
    public static void copyFileByChannel(File source, File target) throws IOException {
        try (FileChannel inputChannel = new FileInputStream(source).getChannel();
             FileChannel outputChannel = new FileOutputStream(target).getChannel();) {

            for (long count = inputChannel.size(); count > 0; ) {
                long transfered = inputChannel.transferTo(inputChannel.position(), count, outputChannel);
                inputChannel.position(inputChannel.position() + transfered);
                count -= transfered;
            }
        }
    }

}
