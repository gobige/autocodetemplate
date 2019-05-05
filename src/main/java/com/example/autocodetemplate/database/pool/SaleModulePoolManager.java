//package com.example.autocodetemplate.database.pool;
//
// import org.apache.commons.dbcp.DriverManagerConnectionFactory;
//import org.apache.commons.dbcp.PoolableConnectionFactory;
//import org.apache.commons.dbcp.PoolingDriver;
//import org.apache.commons.pool.ObjectPool;
//import org.apache.commons.pool.impl.GenericObjectPool;
//import org.apache.log4j.Logger;
//import java.sql.*;
//
//public class SaleModulePoolManager {
//    private static Class<?> driverClass = null;
//    private static ObjectPool connectionPool = null;
//
//    private static Logger log = Logger.getLogger(SaleModulePoolManager.class.getName());
//    private static String dbName ;
//	private static String driver ;
//	private static String url ;
//	private static  String username ;
//	private static  String password ;
//	private static int initSize ;
//	private static int maxIdle ;
//	private static int minIdle ;
//	private static int maxWait ;
//	private static int maxActive ;
//
//	static{
//		try {
////			parseConfigFile();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
////	public static void parseConfigFile() throws Exception{
////		SAXReader reader = new SAXReader();
////		Document doc =  reader.read(SaleModulePoolManager.class.getResourceAsStream("/db_sale_module.xml"));
////		Element root = doc.getRootElement();
////		dbName = root.elementText("dbName");
////		driver = root.elementText("driverClass");
////		url = root.elementText("url");
////		username = root.elementText("username");
////		password = root.elementText("password");
////		password = DES.decrypt(password);
////		initSize = 10 ;
////		try{
////			initSize = Integer.parseInt(root.elementText("initSize"));
////		}catch(Exception e){}
////		maxIdle = 20 ;
////		try{
////			maxIdle = Integer.parseInt(root.elementText("maxIdle"));
////		}catch(Exception e){}
////		minIdle = 5 ;
////		try{
////			minIdle = Integer.parseInt(root.elementText("minIdle"));
////		}catch(Exception e){}
////		maxActive = 100 ;
////		try{
////			maxActive = Integer.parseInt(root.elementText("maxActive"));
////		}catch(Exception e){}
////		maxWait = 1000 ;
////		try{
////			maxWait = Integer.parseInt(root.elementText("maxWait"));
////		}catch(Exception e){}
////		log.info("driverClass:"+driver);
////		log.info("url:"+url);
////		log.info("initSize:"+initSize);
////		log.info("maxIdle:"+maxIdle);
////		log.info("minIdle:"+minIdle);
////		log.info("maxActive:"+maxActive);
////		log.info("maxWait:"+maxWait);
////	}
//    public SaleModulePoolManager(){
//    }
//
//    private static synchronized void initDataSource() {
//        if (driverClass == null) {
//            try {
//                driverClass = Class.forName(driver);
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//
//    public static void StartPool() {
//        initDataSource();
//        if (connectionPool != null) {
//            ShutdownPool();
//        }
//        try {
//        	GenericObjectPool.Config config = new GenericObjectPool.Config();
//            config.lifo = false;
//            config.maxActive = maxActive;
//            config.maxIdle = maxIdle;
//            config.minIdle = minIdle;
//            config.maxWait = maxWait;
//
//            connectionPool = new GenericObjectPool(null,config);
//            org.apache.commons.dbcp.ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(url, username, password);
//            new PoolableConnectionFactory(connectionFactory, connectionPool, null, null, false, true);
//            Class.forName("org.apache.commons.dbcp.PoolingDriver");
//            PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
//            driver.registerPool("mysql_sale_module", connectionPool);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public static void ShutdownPool() {
//        try {
//            PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
//            driver.closePool("mysql_sale_module");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public static Connection getConnection() {
//        Connection conn = null;
//        if(connectionPool == null)
//            StartPool();
//        try {
//            conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:mysql_sale_module");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return conn;
//    }
//
//
//    public static Connection getConnection(String name){
//        return getConnection();
//    }
//
//    public static void freeConnection(Connection conn){
//        if(conn != null){
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static void freeConnection (String name,Connection con){
//        freeConnection(con);
//    }
//    public static void close(ResultSet rs ,PreparedStatement pstmt,Connection conn){
//		if(rs!=null){
//			try{
//				rs.close();
//			}catch(Exception e){}
//		}
//		if(pstmt!=null){
//			try{
//				pstmt.close();
//			}catch(Exception e){}
//		}
//		if(conn!=null){
//			try{
//				conn.close();
//			}catch(Exception e){}
//		}
//	}
//
//
//    public static String getPassword() {
//		return password;
//	}
//	public static void setPassword(String password) {
//		SaleModulePoolManager.password = password;
//	}
//	public static String getUsername() {
//		return username;
//	}
//	public static void setUsername(String username) {
//		SaleModulePoolManager.username = username;
//	}
//
//    public static void main(String[] args) {
//       System.out.println(SaleModulePoolManager.getConnection());
//
//    }
//	public static GenericObjectPool getConnectionPool() {
//		return (GenericObjectPool)connectionPool;
//	}
//	public static String getDbName() {
//		return dbName;
//	}
//
//}
//
