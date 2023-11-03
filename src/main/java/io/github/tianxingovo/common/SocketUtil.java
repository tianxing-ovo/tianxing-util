package io.github.tianxingovo.common;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 网络编程工具类
 */
@Slf4j
public class SocketUtil {

    public static void process(Socket socket) throws IOException {
        InputStream is = socket.getInputStream();
        byte[] bytes = new byte[1024];
        // 阻塞等待客户端向I/O流通道中写数据
        int len = is.read(bytes);
        log.info("收到客户端的数据:{}", new String(bytes, 0, len));
        // 返回信息给客户端
        OutputStream os = socket.getOutputStream();
        os.write("success".getBytes());
        // 将缓冲区的数据立即写入到目标流中,而不需要等待缓冲区填满或达到特定条件
        os.flush();
    }
}
