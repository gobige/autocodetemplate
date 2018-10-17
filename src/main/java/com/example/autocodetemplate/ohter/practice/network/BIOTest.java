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
                int i = 10;
                while (true) {
                    if (i < 0) {
                        break;
                    }
                    BIOClient.sendMes("我向你发送数字：" + i);
                    i--;
                }
            }
        }).start();
    }
}

final class BIOClient {
    public static final String SERVER_IP = "192.168.1.120";
    public static final int SERVER_PORT = 14399;

    public static void sendMes(String mes) {
        Socket socket = null;
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);
            inputOutStream(socket,mes);
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

    public static void inputOutStream(Socket socket,String mes) {
        BufferedReader bufferedReader = null;
        PrintWriter printWriter =  null;
        try {
             bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             printWriter = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("BIO客户端=》发送数据");
            printWriter.println(mes);
            System.out.println("BIO客户端=》发送数据完成");
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


    public static void serverInputOutputStream(Socket socket) {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            String receiptMes;
            System.out.println("BIO服务器=》获取到数据:");
            while (true) {
                try {
                    if ((receiptMes = in.readLine()) == null) {
                        break;
                    }
                    System.out.println(receiptMes);
                    out.println("BIO服务器=》我已收到你的数据:" + receiptMes);
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