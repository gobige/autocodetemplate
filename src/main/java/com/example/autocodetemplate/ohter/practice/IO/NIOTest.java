package com.example.autocodetemplate.ohter.practice.IO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * NIO的一些例子
 */
public class NIOTest {
    public static void main(String[] args) throws Exception{
        NIOServer nioServer = new NIOServer(9527);
        nioServer.run();

//        Thread.sleep(1000);
//        NIOClient nioClient = new NIOClient("192.168.1.120", 9527);
//        nioClient.run();


//        int i = 10;
//        while (true) {
//            if (i < 0) {
//                break;
//            }
//            nioClient.sendMes("hello world" + i);
//            Thread.sleep(10000);
//            i--;
//        }
    }
}

final class NIOServer {
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private boolean started;


    NIOServer(int port) {
        try {
            // 创建选择器
            selector = Selector.open();
            // 创建通道
            serverSocketChannel = ServerSocketChannel.open();
            // 非阻塞模式
            serverSocketChannel.configureBlocking(false);
            // 绑定端口
            serverSocketChannel.socket().bind(new InetSocketAddress(port),1024);
            // 注册选择器
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            // 标记服务器已开启
            started = true;
            System.out.println("NIO服务器准备就绪");
        } catch (Exception e) {
            System.err.println("NIO服务器创建开启失败：" + e.getMessage());
            return;
        }
    }

    public void stop() {
        started = false;
    }

    public void run() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (started) {
                    // 唤醒选择器
                    try {
                        selector.select(4000);
                    } catch (IOException e) {
                        System.err.println("NIO服务器-》select唤醒失败" + e);
                        return;
                    }

                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    System.out.println(System.currentTimeMillis() + "NIO服务器-》遍历连接通道，数量：" + selectionKeys.size());
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    SelectionKey selectionKey = null;

                    while (iterator.hasNext()) {
                        selectionKey = iterator.next();
                        iterator.remove();
                        handleSelect(selectionKey);
                    }
                }

                if (selector != null) {
                    try {
                        selector.close();
                    } catch (Exception e) {
                        System.err.println("NIO服务器-》关闭选择器失败：" + e.getMessage());
                        return;
                    }
                }
            }
        }, "nioserver").start();
    }


    private void handleSelect(SelectionKey selectionKey) {
        if (selectionKey.isValid()) {
            System.out.println("NIO服务器-》key验证通过（channel,selctor没有close，key没有cancel）");
            // 新接入请求处理
            if (selectionKey.isAcceptable()) {
                System.out.println("NIO服务器-》处理新接入请求");
                ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                try {
                    // 接受通道套接字的连接请求
                    SocketChannel channel = serverSocketChannel.accept();
                    // 设置非阻塞
                    channel.configureBlocking(false);
                    // 注册selector已读
                    channel.register(selector, SelectionKey.OP_READ);
                } catch (IOException e) {
                    System.err.println("NIO服务器-》接入请求处理失败" + e);
                    return;
                }
            }

            // 读数据
            if (selectionKey.isReadable()) {
                System.out.println("NIO服务器-》处理新请求数据");
                SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                // 创建缓存区
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                int byteNum = 0;
                try {
                    // 通道读取数据到缓存区
                    byteNum = socketChannel.read(byteBuffer);
                    System.out.println("NIO服务器-》读取数据到缓存区成功，数量：" + byteNum);
                } catch (IOException e) {
                    System.err.println("NIO服务器-》读取数据到缓存区失败" + e);
                    return;
                }

                if (byteNum > 0) {
                    byteBuffer.flip();
                    byte[] bytes = new byte[byteBuffer.remaining()];
                    // 将缓存区数据读取到字节数组中
                    byteBuffer.get(bytes);

                    String encodeDataStr = null;
                    try {
                        // 编码字节
                        encodeDataStr = new String(bytes, "UTF-8");
                    } catch (IOException e) {
                        System.err.println("NIO服务器-》对客户端获取字节数据编码处理失败" + e);
                        return;
                    }
                    System.out.println("NIO服务器-》获取客户端数据：" + encodeDataStr);

                    responseClient(socketChannel,"我已获取到你发的数据【"+encodeDataStr+"】");
                } else if (byteNum < 0) {
                    selectionKey.cancel();
                    try {
                        socketChannel.close();
                        System.out.println("NIO服务器-》读取不到数据，关闭通道");
                    } catch (IOException e) {
                        System.err.println("NIO服务器-》关闭通道失败" + e);
                        return;
                    }
                }
            }
        }
    }

    private void responseClient(SocketChannel socketChannel, String responseMes) {
        byte[] bytes = responseMes.getBytes();
        ByteBuffer wirteBuffer = ByteBuffer.allocate(bytes.length);
        wirteBuffer.put(bytes);
        wirteBuffer.flip();
        try {
            socketChannel.write(wirteBuffer);
            System.out.println("NIO服务器-》对客户端发送字节数据");
        } catch (IOException e) {
            System.err.println("NIO服务器-》对客户端发送字节数据处理失败" + e);
            return;
        }
    }
}

final class NIOClient {
    private String servereIp;
    private int serverPort;
    private Selector selector;
    private SocketChannel socketChannel;
    private volatile boolean started;

    NIOClient(String servereIp, int serverPort) {
        this.servereIp = servereIp;
        this.serverPort = serverPort;
        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            started = true;
            System.out.println("NIO客户端-》准备就绪");
        } catch (Exception e) {
            System.err.println("NIO客户端-》创建开启失败：" + e.getMessage());
            return;
        }
    }

    public void stop() {
        started = false;
    }

    public void sendMes(String string) throws Exception{
        socketChannel.register(selector, SelectionKey.OP_READ);
        responseServer(socketChannel, string);

    }

    public void run() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                doconnect();
                while (started) {
                    // 唤醒选择器
                    try {
                        selector.select(4000);
                    } catch (Exception e) {
                        System.err.println("NIO客户端-》select唤醒失败" + e);
                        return;
                    }

                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    System.out.println(System.currentTimeMillis() + "NIO客户端-》遍历连接通道，数量：" + selectionKeys.size());
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    SelectionKey selectionKey = null;

                    while (iterator.hasNext()) {
                        selectionKey = iterator.next();
                        handleSelect(selectionKey);
                    }
                }

                if (selector != null) {
                    try {
                        selector.close();
                        System.out.println("NIO客户端-》关闭选择器成功：");
                    } catch (Exception e) {
                        System.err.println("NIO客户端-》关闭选择器失败：" + e.getMessage());
                        return;
                    }
                }
            }
        }).start();

    }

    private void handleSelect(SelectionKey selectionKey) {
        if (selectionKey.isValid()) {
            System.out.println("NIO客户端-》key验证通过（channel,selctor没有close，key没有cancel）");
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            // 新接入请求处理
            if (selectionKey.isConnectable()) {
                System.out.println("NIO客户端-》处理新接入请求");
                boolean finshConn = false;
                try {
                    finshConn = socketChannel.finishConnect();
                } catch (IOException e) {
                    System.err.println("NIO客户端-》服务器连接失败" + e);
                    return;
                }
                if (finshConn) {
                    System.out.println("NIO客户端-》服务器连接成功");
                } else {
                    return;
                }
            }
            // 读数据
            if (selectionKey.isReadable()) {
                System.out.println("NIO客户端-》处理数据请求");
                // 创建缓存区
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                int byteNum = 0;
                try {
                    // 通道读取数据到缓存区
                    byteNum = socketChannel.read(byteBuffer);
                    System.out.println("NIO客户端-》读取数据到缓存区成功，数量：" + byteNum);
                } catch (IOException e) {
                    System.err.println("NIO客户端-》读取数据到缓存区失败" + e);
                    return;
                }

                if (byteNum > 0) {
                    byteBuffer.flip();
                    byte[] bytes = new byte[byteBuffer.remaining()];
                    // 将缓存区数据读取到字节数组中
                    byteBuffer.get(bytes);

                    String encodeDataStr = null;
                    try {
                        // 编码字节
                        encodeDataStr = new String(bytes, "UTF-8");
                    } catch (IOException e) {
                        System.err.println("NIO客户端-》对服务端获取字节数据编码处理失败" + e);
                        return;
                    }
                    System.out.println("NIO客户端-》获取服务端数据：" + encodeDataStr);
                } else if (byteNum < 0) {
                    selectionKey.cancel();
                    try {
                        socketChannel.close();
                        System.out.println("NIO客户端-》读取不到数据，关闭通道成功");
                    } catch (IOException e) {
                        System.err.println("NIO客户端-》关闭通道失败" + e);
                        return;
                    }
                }
            }
        }
    }


    private void responseServer(SocketChannel socketChannel, String responseMes) {
        byte[] bytes = responseMes.getBytes();
        ByteBuffer wirteBuffer = ByteBuffer.allocate(bytes.length);
        wirteBuffer.put(bytes);
        wirteBuffer.flip();
        try {
            socketChannel.write(wirteBuffer);
            System.out.println("NIO客户端-》对服务端发送字节数据处理成功");
        } catch (IOException e) {
            System.err.println("NIO客户端-》对服务端发送字节数据处理失败" + e);
            return;
        }
    }

    private void doconnect() {
        boolean connectSuc = false;
        try {
            connectSuc = socketChannel.connect(new InetSocketAddress(servereIp, serverPort));
        } catch (IOException e) {
            System.err.println("NIO客户端-》创建服务端连接失败：" + e.getMessage());
            return;
        }
        if (connectSuc) {
            System.out.println("NIO客户端-》创建服务端连接成功");
        } else {
            try {
                SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_CONNECT);
                System.out.println("NIO客户端-》注册服务端连接成功" + selectionKey);
            } catch (IOException e) {
                System.err.println("NIO客户端-》注册服务端连接失败：" + e.getMessage());
                return;
            }
        }
    }
}