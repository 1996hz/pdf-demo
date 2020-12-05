package com.pdf.demo.word2pdf;

import java.io.*;

public class FileUtils {

    /**
     * 复制文件
     *
     * @param srcFilePath  源文件路径
     * @param destFilePath 目标文件路径
     */
    public static void copyFile(String srcFilePath, String destFilePath) throws IOException {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(srcFilePath));
            bos = new BufferedOutputStream(new FileOutputStream(destFilePath));
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("从源文件：" + srcFilePath + " ----到目标文件： " + destFilePath + "复制文件出错");
        } finally {
            if (bos != null) {
                bos.flush();
            }
            if (bis != null) {
                bis.close();
            }
            if (bos != null) {
                bos.close();
            }
        }
    }

    /**
     * 将src文件夹复制至dest文件夹下
     *
     * @param src  源目录
     * @param dest 目标目录 （必须存在）
     */
    public static void copyDir(File src, File dest) throws IOException {
        File newDir = new File(dest, src.getName());
        if (!newDir.exists()) {
            newDir.mkdir();
        }
        File[] files = src.listFiles();
        for (File file : files) {
            if (!file.isDirectory()) {
                copyFile(file.getAbsolutePath(), newDir.getAbsolutePath() + file.getName());
            } else {
                copyDir(file, newDir);
            }
        }
    }

    /**
     * 获取该路径的同级路径
     *
     * @param path
     * @return boolean
     */
    public static String getPeerPath(String path) {
        if (StringUtils.isNullOrEmpty(path)) {
            return "";
        }
        if (!path.contains("/") && path.contains("\\")) {
            return path = path.substring(0, path.lastIndexOf("\\"));
        }
        return path = path.substring(0, path.lastIndexOf("/"));

    }

    /**
     * 判断是否为word文档格式
     *
     * @param fileName
     * @return boolean
     */
    public static boolean isWord(String fileName) {
        System.out.println("fileName" + fileName);
        String fileFromat = fileName.substring(fileName.lastIndexOf("."));
        if (".doc".equals(fileFromat) || ".docx".equals(fileFromat)) {
            return true;
        }
        return false;
    }

}
