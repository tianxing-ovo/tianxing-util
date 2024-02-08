package io.github.tianxingovo.zip;

import lombok.SneakyThrows;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * zip工具类
 */
public class ZipUtil {

    /**
     * 当前路径创建zip文件
     *
     * @param basePath     基础路径
     * @param fileNameList 文件名称列表
     * @param zipName      zip文件名称
     */
    @SneakyThrows
    public static void zip(String basePath, List<String> fileNameList, String zipName) {
        OutputStream os = Files.newOutputStream(Paths.get(basePath, zipName));
        ZipOutputStream zos = new ZipOutputStream(os, StandardCharsets.UTF_8);
        byte[] bytes = new byte[1024];
        int len;
        for (String fileName : fileNameList) {
            zos.putNextEntry(new ZipEntry(fileName));
            InputStream is = Files.newInputStream(Paths.get(basePath, fileName));
            while ((len = is.read(bytes)) > 0) {
                zos.write(bytes, 0, len);
            }
            zos.closeEntry();
        }
        zos.close();
    }

    /**
     * 解压zip文件到当前路径
     *
     * @param basePath 基础路径
     * @param zipName  zip文件名称
     */
    @SneakyThrows
    public static void unzip(String basePath, String zipName) {
        // 创建目录
        Path path = Paths.get(basePath, zipName.split("\\.")[0]);
        if (Files.isDirectory(path) && Files.exists(path)) {
            System.out.println("目录已存在");
        } else {
            Files.createDirectory(path);
        }
        byte[] bytes = new byte[1024];
        int len;
        ZipFile zipFile = new ZipFile(Paths.get(basePath, zipName).toFile());
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry zipEntry = entries.nextElement();
            InputStream is = zipFile.getInputStream(zipEntry);
            OutputStream os = Files.newOutputStream(path.resolve(zipEntry.getName()));
            while ((len = is.read(bytes)) > 0) {
                os.write(bytes, 0, len);
            }
        }
        zipFile.close();
    }
}
