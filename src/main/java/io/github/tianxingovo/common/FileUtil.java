package io.github.tianxingovo.common;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;
import java.util.List;

/**
 * 文件工具类
 */
@Slf4j
public class FileUtil {

    /**
     * 以字节为单位读取,处理二进制文件(如图像、音频)
     */
    public static String readByte(String file) {
        StringBuilder sb = new StringBuilder();
        try (FileInputStream is = new FileInputStream(file)) {
            byte[] bytes = new byte[1024]; //每次读取1024个字节
            int len;
            while ((len = is.read(bytes)) != -1) {
                sb.append(new String(bytes, 0, len));
            }
        } catch (FileNotFoundException e) {
            log.error("文件读取失败,找不到系统路径");
        } catch (IOException e) {
            log.error("文件读取失败,失败信息:{}", e.getMessage());
        }
        return sb.toString();
    }

    /**
     * 读取字符,处理文本文件
     */
    public static String readChar(String file) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String s;
            while ((s = reader.readLine()) != null) {
                sb.append(s).append("\n");
            }
        } catch (IOException e) {
            log.error("文件读取失败,失败信息:{}", e.getMessage());
        }
        return sb.toString();
    }

    /**
     * NIO读取
     */
    public static String readNIO(String file) {
        StringBuilder sb = new StringBuilder();
        try {
            List<String> list = Files.readAllLines(Paths.get(file));
            list.forEach(s -> sb.append(s).append("\n"));
        } catch (IOException e) {
            log.error("文件读取失败,失败信息:{}", e.getMessage());
        }
        return sb.toString();
    }

    /**
     * 以字节为单位写入
     */
    public static void writeByte(String file, String s) {
        //true:以追加的方式写入,默认会覆盖
        try (FileOutputStream os = new FileOutputStream(file, true)) {
            byte[] bytes = s.getBytes();
            os.write(bytes);
            os.flush();
            log.info("写入成功");
        } catch (FileNotFoundException e) {
            log.error("文件写入失败,找不到系统路径");
        } catch (IOException e) {
            log.error("文件写入失败,失败信息:{}", e.getMessage());
        }
    }

    /**
     * 以字符为单位写入
     */
    public static void write(String file, String s) {
        // true:以追加的方式写入,默认会覆盖
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(s);
            writer.flush();
            log.info("写入成功");
        } catch (FileNotFoundException e) {
            log.error("文件写入失败,找不到系统路径");
        } catch (IOException e) {
            log.error("文件写入失败,失败信息:{}", e.getMessage());
        }
    }

    /**
     * NIO写入
     */
    public static void writeNIO(String file, String s) {
        try {
            //APPEND:以追加的方式写入,默认会覆盖
            Files.write(Paths.get(file), s.getBytes(), StandardOpenOption.APPEND);
            log.info("写入成功");
        } catch (IOException e) {
            log.error("文件写入失败,失败信息:{}", e.getMessage());
        }
    }

    /**
     * 将文件内容转换为Base64字符串
     */
    @SneakyThrows
    public static String Base64String(String file) {
        byte[] bytes = Files.readAllBytes(Paths.get(file));
        return Base64.getEncoder().encodeToString(bytes);
    }
}
