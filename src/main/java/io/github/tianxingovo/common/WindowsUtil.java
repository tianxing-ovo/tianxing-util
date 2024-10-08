package io.github.tianxingovo.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WindowsUtil {

    /**
     * 执行dos命令并打印到控制台
     *
     * @param command 命令
     */
    public static void exec(String command) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"));
            String line;
            while ((line = br.readLine()) != null) {
               System.out.println(line);
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
