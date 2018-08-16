package com.example.autocodetemplate.ohter.practice.concurrency;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * 数据库连接池 简单示例
 *
 * @author yates
 */
public class ConnectionPool {
    LinkedList<Connection> pool = new LinkedList<Connection>();

    public ConnectionPool(int initialSize) {
        if (initialSize > 0) {
            pool.addLast(ConnectionDriver.creatConnection());
        }
    }

    public void releaseConnection(Connection conn) {
        if (conn != null) {
            synchronized (pool) {
                pool.addLast(conn);
                pool.notifyAll();
            }
        }
    }

    public Connection fetchConnection(long mills) throws Exception {
        synchronized (pool) {
            if (mills <= 0) {
                if (pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();
            } else {
                long futrue = System.currentTimeMillis() + mills;
                long remainTimes = mills;

                while (pool.isEmpty() && remainTimes > 0) {
                    pool.wait(remainTimes);
                    remainTimes = futrue - System.currentTimeMillis();
                }
                Connection conn = null;
                if (!pool.isEmpty()) {
                    conn = pool.removeFirst();
                }
                return conn;
            }
        }
    }
}

/**
 * 模拟代理connection
 *
 * @author yates
 */
class ConnectionDriver {
    static class ConnectionHandler implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("commit")) {
                TimeUnit.MILLISECONDS.sleep(100);
            }
            return null;
        }
    }

    public static final Connection creatConnection() {
        return (Connection) Proxy.newProxyInstance(ConnectionDriver.class.getClassLoader(), new Class<?>[]{Connection.class}, new ConnectionHandler());
    }

}
