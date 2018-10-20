package com.example.autocodetemplate.ohter.practice.network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * BIO的一些例子
 */
public class BIOTest {
    public static void main(String[] args) throws Exception{
        new Thread(new Runnable() {
            @Override
            public void run() {
                BIOServer.start();
            }
        }).start();
        Thread.sleep(1000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                NIOClient nioClient = new NIOClient("192.168.1.120", 9527);
                nioClient.run();
                try {
                    Thread.sleep(1000);
                    nioClient.sendMes("hello world");
                } catch (Exception e) {

                }
            }
        }).start();
    }
}

final class BIOClient {
    public static final String SERVER_IP = "192.168.1.120";
    public static final int SERVER_PORT = 14399;

    // java 中的静态方法时公用的，多线程模式下也会造成阻塞
    public static void sendMes(String mes) {
        Socket socket = null;
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);
            clientInputOutStream(socket,mes);
        } catch (Exception e) {
            System.err.println("BIO客户端=》套接字创建失败-》" + e.getMessage());
            return;
        }finally {
            try {
                socket.close();
                System.out.println("BIO客户端=》socket流关闭");
            } catch (Exception e) {
                System.err.println("BIO客户端=》socket流关闭失败-》" + e.getMessage());
            }

        }
    }

    /**
     * 客户端获取流
     * @param socket
     * @param mes
     */
    public static void clientInputOutStream(Socket socket,String mes) {
        BufferedReader bufferedReader = null;
        PrintWriter printWriter =  null;
        try {
             bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             printWriter = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("BIO客户端=》发送数据");
            printWriter.println(mes);
            System.out.println("BIO客户端=》发送数据完成");
            // 客户端会一直等待服务端的回复或者断开连接的通知
            System.out.println("BIO客户端=》得到服务器答复：" + bufferedReader.readLine());
        }catch (Exception e) {
            System.err.println("BIO客户端=》io流创建失败-》" + e.getMessage());
            return;
        }finally {
            try {
                bufferedReader.close();
                printWriter.close();
                System.out.println("BIO客户端=》io流关闭");
            } catch (Exception e) {
                System.err.println("BIO客户端=》io流关闭失败-》" + e.getMessage());
            }
        }
    }
}




final class BIOServer {
    public static ServerSocket serverSocket;
    public static void   start() {
        serverSocket = SingleServerSocket.getInstance();

        while (true) {
            System.out.println("BIO服务器=》等待连接");
            try {
                Socket socket = serverSocket.accept();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        serverInputOutputStream(socket);
                    }
                }).start();
            } catch (Exception e) {
                System.err.println("BIO服务器=》socket套接字获取失败：-》" + e.getMessage());
                return;
            }
        }
    }

    /**
     * 服务端获取流
     * @param socket
     */
    public static void serverInputOutputStream(Socket socket) {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            String receiptMes;
            while (true) {
                try {
                    // 如果客户端不断开连接或自身服务端不断开连接，服务端会一直等待客户端发送数据
                    if ((receiptMes = in.readLine()) == null) {
                        break;
                    }
                    System.out.println("BIO服务器=》获取到数据:" + receiptMes);
                    Thread.sleep(8000);
                    out.println("我已收到你的数据:" + receiptMes);
                } catch (Exception e) {
                    System.err.println("BIO服务器=》流数据读取失败-》" + e.getMessage());
                    return;
                }
            }
        } catch (Exception e) {
            System.err.println("BIO服务器=》流获取失败-》" + e.getMessage());
            return;
        }finally {
            try {
                in.close();
                out.close();
                System.out.println("BIO服务器=》io流关闭");
            } catch (Exception e) {
                System.err.println("BIO服务器=》io流关闭失败-》" + e.getMessage());
            }
        }
    }
}



class SingleServerSocket {
    private SingleServerSocket(){};
    private static final int BIO_SERVER_PORT = 14399;

    private volatile static ServerSocket serverSocket = null;

    public static ServerSocket getInstance() {
        if (serverSocket == null) {
            synchronized (ServerSocket.class) {
                if (serverSocket == null) {
                    try {
                        serverSocket = new ServerSocket(BIO_SERVER_PORT);
                        InetAddress internetAddress = serverSocket.getInetAddress();
                        System.out.println("初始化BIO服务器成功，端口号：" + BIO_SERVER_PORT);
                    } catch (Exception e) {
                        System.err.println("初始化BIO服务器失败：-》" + e.getMessage());
                    }
                }
            }
        }

        return serverSocket;
    }
}