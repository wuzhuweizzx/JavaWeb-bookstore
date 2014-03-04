package com.zhuwei.utils;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class JdbcUtils {
	
	private static DataSource ds = null;
	private static ThreadLocal<Connection> t = new ThreadLocal<Connection>();
	
	static {
		ds = new ComboPooledDataSource("mysql");	
	}
	
	public static DataSource getDataSource(){
		return ds;
	}
	
	public static Connection getConnection() {
		try {
			Connection conn = t.get();
			
			if(conn == null){
				conn = ds.getConnection();
				t.set(conn);
			}	
			return conn;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void startTransaction(){
		try {
			Connection conn = getConnection();
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void commit(){
		try {
			Connection conn = t.get();
			if(conn != null){
				conn.commit();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void rollback(){
		try {
			Connection conn = t.get();
			if(conn != null){
				conn.rollback();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void close(){
		try {
			Connection conn = t.get();
			if(conn != null){
				conn.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			t.remove();
		}
	}

}
