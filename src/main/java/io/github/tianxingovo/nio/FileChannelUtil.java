package io.github.tianxingovo.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class FileChannelUtil {

    private static final String mode = "rw";

    public static String read(String name) throws IOException {
        RandomAccessFile file = new RandomAccessFile(name, mode);
        FileChannel fileChannel = file.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int len;
        StringBuilder sb = new StringBuilder();
        while ((len = fileChannel.read(buffer)) != -1) {
            // 写模式 -> 读模式
            buffer.flip();
            sb.append(new String(buffer.array(), 0, len));
            buffer.clear();
        }
        file.close();
        return sb.toString();
    }

    public static void write(String name, String data) throws IOException {
        RandomAccessFile file = new RandomAccessFile(name, mode);
        FileChannel fileChannel = file.getChannel();
        fileChannel.write(ByteBuffer.wrap(data.getBytes()));
        file.close();
    }

    public static void transferTo(String srcName, String destName) throws IOException {
        RandomAccessFile srcFile = new RandomAccessFile(srcName, mode);
        RandomAccessFile destFile = new RandomAccessFile(destName, mode);
        FileChannel srcFileChannel = srcFile.getChannel();
        FileChannel destFileChannel = destFile.getChannel();
        srcFileChannel.transferTo(0, srcFileChannel.size(), destFileChannel);
        srcFile.close();
        destFile.close();
    }

    public static void transferFrom(String srcName, String destName) throws IOException {
        RandomAccessFile srcFile = new RandomAccessFile(srcName, mode);
        RandomAccessFile destFile = new RandomAccessFile(destName, mode);
        FileChannel srcFileChannel = srcFile.getChannel();
        FileChannel destFileChannel = destFile.getChannel();
        destFileChannel.transferFrom(srcFileChannel, 0, srcFileChannel.size());
        srcFile.close();
        destFile.close();
    }
}