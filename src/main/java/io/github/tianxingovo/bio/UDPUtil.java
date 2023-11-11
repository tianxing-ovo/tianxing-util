package io.github.tianxingovo.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

@Slf4j
public class UDPUtil {
    /**
     * 读数据
     */
    public static DatagramPacket read(DatagramSocket socket, String message) throws IOException {
        byte[] bytes = new byte[1024];
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
        // 阻塞,直到接收到数据报
        socket.receive(packet);
        log.info(message + ":{}", new String(bytes, 0, packet.getLength()));
        return packet;
    }

    /**
     * 写数据
     */
    public static void write(DatagramSocket socket, String data, InetAddress address, int port) throws Exception {
        byte[] bytes = data.getBytes();
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, port);
        socket.send(packet);
    }
}
