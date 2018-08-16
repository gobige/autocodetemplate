package com.example.autocodetemplate.ohter.practice.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 单文件服务器
 *
 * @author yates
 */
public class SingleFileHttpServer {
    private final byte[] content;
    private final byte[] header;
    private final String encoding;
    private final int port;
    private static final Logger log = Logger.getLogger("SingleFileHttpServer");

    public SingleFileHttpServer(String data, String enCoding, String mimeType, int port) throws UnsupportedEncodingException {
        this(data.getBytes(enCoding), enCoding, mimeType, port);
    }

    public SingleFileHttpServer(byte[] data, String enCoding, String mimeType, int port) {
        this.content = data;
        this.port = port;
        this.encoding = enCoding;
        String header = "HTTP/1.0 200 OK\r\n" +
                "Server: yatesFile 2.0\r\n" +
                "Content-length: " + this.content.length + "\r\n" +
                "Content-type: " + mimeType + ";charset=" + enCoding + "\r\n\r\n";
        this.header = header.getBytes(Charset.forName("US-ASCII"));

    }

    public void start() {
        ExecutorService pool = Executors.newFixedThreadPool(90);
        try (ServerSocket server = new ServerSocket(this.port)) {
            log.info("accept connection: " + server.getLocalPort() + "\n\r ready to sent file content");
            log.info("file content" + new String(this.content, encoding));

            try {
                while (true) {
                    Socket connection = server.accept();
                    pool.submit(new HTTPHandler(connection));
                }
            } catch (IOException e) {
                log.log(Level.WARNING, "exception accepting connection", e);
            } catch (RuntimeException e) {
                log.log(Level.SEVERE, "unexpected error", e);
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "could not start server", e);
        }

    }

    public static void main(String[] args) {
        String enCoding = "utf-8";
        int port;
        try {
            port = Integer.valueOf(args[1]);
            if (port < 1 || port > 65535) {
                port = 80;
            }
        } catch (RuntimeException e) {
            port = 80;
        }


        try {
            Path path = Paths.get("d:\\twocolorhistory.txt");
            byte[] data = Files.readAllBytes(path);
            String mimeType = URLConnection.getFileNameMap().getContentTypeFor("d:\\twocolorhistory.txt");
            SingleFileHttpServer server = new SingleFileHttpServer(data, enCoding, mimeType, port);
            server.start();
        } catch (IOException e) {
            log.severe(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            log.log(Level.WARNING, "ArrayIndexOutOfBounds", e);
        }

    }

    private class HTTPHandler implements Callable<Void> {
        private final Socket connection;

        public HTTPHandler(Socket connection) {
            this.connection = connection;
        }

        @Override
        public Void call() throws Exception {
            try {
                InputStream is = this.connection.getInputStream();
                OutputStream os = this.connection.getOutputStream();

                StringBuilder sb = new StringBuilder();

                int isc;
                while ((isc = is.read()) != -1 && isc != '\r' && isc != '\n') {
                    sb.append((char) isc);
                }
                System.out.println(sb.toString());
                if (sb.indexOf("HTTP/") != -1) {
                    os.write(header);
                }
                os.write(content);
                os.flush();
            } catch (Exception e) {
                log.log(Level.SEVERE, "error waiting client", e);
            } finally {
                this.connection.close();
            }

            return null;
        }

    }
}
