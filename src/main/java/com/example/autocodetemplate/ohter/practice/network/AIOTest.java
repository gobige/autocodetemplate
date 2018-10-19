package com.example.autocodetemplate.ohter.practice.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
public class AIOTest {
    public static void main(String[] args) throws Exception{
        AIOServer aioServer = new AIOServer(9527);
        aioServer.start();
//        Thread.sleep(1000);
//        AIOClient aioClient = new AIOClient("localhost", 9327);
//        aioClient.start();
//        aioClient.sendMsg("hello,good night!!!");
    }
}

final class AIOServer {
    public CountDownLatch latch;
    public AsynchronousServerSocketChannel channel;
    public static int connectCount = 0;
    AIOServer(int port) {
        try {
            channel = AsynchronousServerSocketChannel.open();
            channel.bind(new InetSocketAddress(port));
        } catch (IOException e) {
            System.err.println("AIO服务端-》创建开启失败：" + e.getMessage());
            return;
        }
    }

    public void start() {
        latch = new CountDownLatch(1);
        channel.accept(this,new ServerAcceptHandler());
    }
}

// 处理客户端连接请求
final class ServerAcceptHandler implements CompletionHandler<AsynchronousSocketChannel, AIOServer> {
    @Override
    public void completed(AsynchronousSocketChannel channel, AIOServer aioServer) {
        AIOServer.connectCount++;
        System.out.println("当前客户端连接请求数为:" + AIOServer.connectCount);

        aioServer.channel.accept(aioServer, this);

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        channel.read(byteBuffer, byteBuffer, new ServerReadHandler(channel));
    }

    @Override
    public void failed(Throwable exc, AIOServer attachment) {
        exc.printStackTrace();
        attachment.latch.countDown();
    }
}

final class ServerReadHandler implements CompletionHandler<Integer, ByteBuffer> {
    private AsynchronousSocketChannel channel;
    ServerReadHandler(AsynchronousSocketChannel channel) {
        this.channel = channel;
    }

    @Override
    public void completed(Integer result, ByteBuffer buffer) {
        buffer.flip();
        byte[] mes = new byte[buffer.remaining()];
        try {
            String mesStr = new String(mes, "UTF-8");
            System.out.println("AIO服务端-》获取到客户端消息：" + mesStr);
        } catch (IOException e) {
            System.err.println("AIO服务端-》客户端消息编码失败");
        }

        doResponse("我已经收到你的消息了");
    }

    private void doResponse(String mes) {
        byte[] bytes = mes.getBytes();
        ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
        byteBuffer.put(bytes);
        byteBuffer.flip();
        channel.write(byteBuffer, byteBuffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer buffer) {
                if (buffer.hasRemaining()) {
                    channel.write(buffer, buffer, this);
                } else {
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                    channel.read(readBuffer, readBuffer, new ServerReadHandler(channel));
                }
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                try {
                    channel.close();
                } catch (IOException e) {
                    System.err.println("AIO服务端-》写过程中通道关闭失败");
                }
            }
        });
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        try {
            channel.close();
        } catch (IOException e) {
            System.err.println("AIO服务端-》读过程中通道关闭失败");
        }
    }
}

final class AIOClient {
    private static String host;
    private static int port;
    private static AsyncClientHandler asyncClientHandler;

    AIOClient(String host,int port) {
        this.host = host;
        this.port = port;
    }

    public void start() {
        if (asyncClientHandler == null) {
            asyncClientHandler = new AsyncClientHandler(host,port);
            new Thread(asyncClientHandler, "AIOClient").start();
        }
    }

    public void sendMsg(String str) {
        asyncClientHandler.sendMsg(str);
    }
}

class AsyncClientHandler implements CompletionHandler<Void, AsyncClientHandler>, Runnable {

    private static String host;
    private static AsynchronousSocketChannel clientChannel;
    private static int port;
    private static CountDownLatch latch;

    AsyncClientHandler(String host,int port) {
        this.host = host;
        this.port = port;

        try {
            clientChannel = AsynchronousSocketChannel.open();
        } catch (IOException e) {
            System.err.println("AIO客户端-》创建开启失败：" + e.getMessage());
            return;
        }
    }

    @Override
    public void run() {
        latch = new CountDownLatch(1);

        clientChannel.connect(new InetSocketAddress(host, port),this,this);

        try {
            latch.await();
        } catch (InterruptedException e) {
            System.err.println("AIO客户端-》异步处理器，线程计数器等待失败，：" + e.getMessage());
            return;
        }

        try {
            clientChannel.close();
        } catch (IOException e) {
            System.err.println("AIO客户端-》客户端通道关闭失败，：" + e.getMessage());
            return;
        }
    }

    @Override
    public void completed(Void result, AsyncClientHandler attachment) {
        System.out.println("AIO客户端-》客户端成功连接到服务器");
    }

    @Override
    public void failed(Throwable exc, AsyncClientHandler attachment) {
        System.err.println("AIO客户端-》连接服务器失败...");
        exc.printStackTrace();

        try {
            clientChannel.close();
            latch.countDown();
        } catch (IOException e) {
            System.err.println("AIO客户端-》关闭连接通道关闭失败，：" + e.getMessage());
            return;
        }
    }

    public void sendMsg(String msg) {
        byte[] req = msg.getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
        writeBuffer.put(req);
        writeBuffer.flip();
        clientChannel.write(writeBuffer, writeBuffer, new writeHandler(clientChannel,latch));
    }
}

class writeHandler implements CompletionHandler<Integer, ByteBuffer> {
    private AsynchronousSocketChannel clientChannel;
    private CountDownLatch latch;

    writeHandler(AsynchronousSocketChannel socketChannel, CountDownLatch latch) {
        this.clientChannel = socketChannel;
        this.latch = latch;
    }

    @Override
    public void completed(Integer result, ByteBuffer buffer) {
        if (buffer.hasRemaining()) {
            clientChannel.write(buffer, buffer, this);
        } else {
            ByteBuffer readBuf = ByteBuffer.allocate(1024);
            clientChannel.read(readBuf, readBuf, new readHandler(clientChannel,latch));
        }
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        System.err.println("AIO客户端-》数据发送失败...");
        exc.printStackTrace();

        try {
            clientChannel.close();
            latch.countDown();
        } catch (IOException e) {
            System.err.println("AIO客户端-》关闭连接通道关闭失败，：" + e.getMessage());
            return;
        }
    }
}

class readHandler implements CompletionHandler<Integer, ByteBuffer> {
    private AsynchronousSocketChannel clientChannel;
    private CountDownLatch latch;

    readHandler(AsynchronousSocketChannel clientChannel, CountDownLatch latch) {
        this.clientChannel = clientChannel;
        this.latch = latch;
    }

    @Override
    public void completed(Integer result, ByteBuffer buffer) {
        buffer.flip();
        byte[] bytes = new byte[buffer.remaining()];
//        buffer.get(bytes);

        String str = "";
        try {
            str = new String(bytes, "UTF-8");
            System.out.println("AIO客户端-》收到服务器结果" + str);
        } catch (IOException e) {
            System.err.println("AIO客户端-》服务器解析数据失败，：" + e.getMessage());
            return;
        }
    }


    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        System.err.println("数据读取失败...");
        try {
            clientChannel.close();
            latch.countDown();
        } catch (IOException e) {

    }
}
}

