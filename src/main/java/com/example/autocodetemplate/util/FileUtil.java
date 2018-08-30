package com.example.autocodetemplate.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static final int BUFFER_SIZE = 4096;

    /**
     * 获取磁盘文件输入流转换为string输出
     * @param path
     * @return
     * @throws IOException
     */
    public static String fileInputStreamToString(String path) throws IOException {
        File file = new File(path);

        FileInputStream fis = new FileInputStream(file);
        byte[] buf = new byte[BUFFER_SIZE];
        StringBuffer sb = new StringBuffer();
        while ((fis.read(buf)) != -1) {
            sb.append(new String(buf));
            buf = new byte[BUFFER_SIZE];
        }

        return sb.toString();
    }

    /**
     * 输出文件
     *
     * @param content
     * @param file
     */
    public static void outputFile(String content, File file) {
        try {
            FileUtil.isExistOrCreateFile(file);
        } catch (Exception e) {
            logger.info("创建文件失败,{}", e.getMessage());
        }

        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e) {
            logger.info("写入文件失败，{}", e.getMessage());
        }
    }

    /**
     * 判断路径是否存在
     *
     * @param filepath
     * @return
     */
    public static boolean isExist(String filepath) {
        File file = new File(filepath);
        return file.exists();
    }

    /**
     * 获取文件编码格式
     *
     * @param fileName
     * @return
     * @throws Exception
     */
    public static String getFileEncoding(String fileName) throws Exception {
        BufferedInputStream bin = new BufferedInputStream(new FileInputStream(fileName));
        int p = (bin.read() << 8) + bin.read();
        String code = null;
        switch (p) {
            case 0xefbb:
                code = "UTF-8";
                break;
            case 0xfffe:
                code = "Unicode";
                break;
            case 0xfeff:
                code = "UTF-16BE";
                break;
            default:
                code = "GBK";
        }

        return code;
    }

    /**
     * 拷贝文件到指定的路径 (使用了自定义的CopyFileUtil工具类)
     *
     * @param fileURL
     * @param targetFile
     */
    public void copy(String fileURL, String targetFile) {
        URLConnection urlconn = null;
        try {
            URL url = new URL(fileURL);
            urlconn = url.openConnection();
            InputStream in = urlconn.getInputStream();

            File newfile = new File(targetFile);
            FileOutputStream fos = new FileOutputStream(newfile);
            CopyFileUtil.copy(in, fos);//使用了自定义的FileUtil工具类
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 复制文件
     *
     * @param sourceFile
     * @param targetFile
     * @throws IOException
     */
    public static void copyFile(File sourceFile, File targetFile)
            throws IOException {
        // 新建文件输入流并对它进行缓冲
        FileInputStream input = new FileInputStream(sourceFile);
        BufferedInputStream inBuff = new BufferedInputStream(input);
        // 新建文件输出流并对它进行缓冲
        FileOutputStream output = new FileOutputStream(targetFile);
        BufferedOutputStream outBuff = new BufferedOutputStream(output);
        // 缓冲数组
        byte[] b = new byte[1024 * 5];
        int len;
        while ((len = inBuff.read(b)) != -1) {
            outBuff.write(b, 0, len);
        }
        // 刷新此缓冲的输出流
        outBuff.flush();
        // 关闭流
        inBuff.close();
        outBuff.close();
        output.close();
        input.close();
    }

    /**
     * 复制文件夹
     *
     * @param sourceDir
     * @param targetDir
     * @throws IOException
     */
    public static void copyDirectiory(String sourceDir, String targetDir)
            throws IOException {
        // 新建目标目录
        (new File(targetDir)).mkdirs();
        // 获取源文件夹当前下的文件或目录
        File[] file = (new File(sourceDir)).listFiles();
        for (int i = 0; i < file.length; i++) {
            if (file[i].isFile()) {
                // 源文件
                File sourceFile = file[i];
                // 目标文件
                File targetFile = new File(new File(targetDir)
                        .getAbsolutePath()
                        + File.separator + file[i].getName());
                if (sourceFile.getName().indexOf(".vax") < 0)
                    copyFile(sourceFile, targetFile);
            }
            if (file[i].isDirectory()) {
                // 准备复制的源文件夹
                String dir1 = sourceDir + "/" + file[i].getName();
                // 准备复制的目标文件夹
                String dir2 = targetDir + "/" + file[i].getName();
                copyDirectiory(dir1, dir2);
            }
        }
    }

    /**
     * 得到文件的扩展名
     *
     * @param f
     * @return
     */
    public static String getFileExtension(File f) {
        if (f != null) {
            String filename = f.getName();
            int i = filename.lastIndexOf('.');
            if (i > 0 && i < filename.length() - 1) {
                return filename.substring(i + 1).toLowerCase();
            }
        }
        return null;
    }

    /**
     * 得到文件名(排除文件扩展名)
     *
     * @param f
     * @return
     */
    public static String getFileNameWithoutExt(File f) {
        if (f != null) {
            String filename = f.getName();
            int i = filename.lastIndexOf('.');
            if (i > 0 && i < filename.length() - 1) {
                return filename.substring(0, i);
            }
        }
        return null;
    }

    /**
     * 改变文件的扩展名
     *
     * @param fileNM
     * @param ext
     * @return
     */
    public static String changeFileExt(String fileNM, String ext) {
        int i = fileNM.lastIndexOf('.');
        if (i >= 0)
            return (fileNM.substring(0, i) + ext);
        else
            return fileNM;
    }

    /**
     * 得到文件的全路径
     *
     * @param filePath
     * @return
     */
    public static String getFileNameWithFullPath(String filePath) {
        int i = filePath.lastIndexOf('/');
        int j = filePath.lastIndexOf("\\");
        int k;
        if (i >= j) {
            k = i;
        } else {
            k = j;
        }
        int n = filePath.lastIndexOf('.');
        if (n > 0) {
            return filePath.substring(k + 1, n);
        } else {
            return filePath.substring(k + 1);
        }
    }


    /**
     * 判断目录是否存在
     *
     * @param strDir
     * @return
     */
    public static boolean existsDirectory(String strDir) {
        File file = new File(strDir);
        return file.exists() && file.isDirectory();
    }

    /**
     * 判断文件是否存在
     *
     * @param strDir
     * @return
     */
    public static boolean existsFile(String strDir) {
        File file = new File(strDir);
        return file.exists();
    }

    /**
     * 存在或创建文件
     *
     * @return
     */
    public static boolean isExistOrCreateFile(File file) throws Exception {
        if (!file.exists()) {
            String dirPath = file.getParent();
            isExistOrCreateDir(dirPath);
            file.createNewFile();
        }

        return true;
    }

    /**
     * 存在或创建目录
     *
     * @param dirPath
     * @return
     */
    public static boolean isExistOrCreateDir(String dirPath) {
        File file = new File(dirPath);
        if (!existsDirectory(dirPath)) {
            file.mkdirs();
        }

        return true;
    }

    /**
     * 得到文件的大小
     *
     * @param fileName
     * @return
     */
    public static int getFileSize(String fileName) {

        File file = new File(fileName);
        FileInputStream fis = null;
        int size = 0;
        try {
            fis = new FileInputStream(file);
            size = fis.available();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

}