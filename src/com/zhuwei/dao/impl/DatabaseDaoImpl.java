package com.zhuwei.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.zhuwei.dao.DatabaseDao;
import com.zhuwei.domain.Database;
import com.zhuwei.exception.DaoException;
import com.zhuwei.utils.JdbcUtils;

public class DatabaseDaoImpl implements DatabaseDao {
	
	/* (non-Javadoc)
	 * @see com.zhuwei.dao.impl.DatabaseDao#add(com.zhuwei.domain.Database)
	 */
	public void add(Database db){
		try {
			QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "insert into mydatabase(id, name, path, backuptime) values(?, ?, ?, ?)";
			Object[] params = new Object[]{db.getId(), db.getName(), db.getPath(), db.getBackuptime()};
			qr.update(sql, params);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.zhuwei.dao.impl.DatabaseDao#find(java.lang.String)
	 */
	public Database find(String id){
		try {
			QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from mydatabase where id = ?";
			return qr.query(sql, new BeanHandler(Database.class), id);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.zhuwei.dao.impl.DatabaseDao#getAll()
	 */
	public List<Database> getAll(){
		try {
			QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from mydatabase order by backuptime desc ";
			return qr.query(sql, new BeanListHandler(Database.class));
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	
	/* (non-Javadoc)
	 * @see com.zhuwei.dao.impl.DatabaseDao#delete(java.lang.String)
	 */
	public void delete(String id){
		try {
			QueryRunner qr = new QueryRunner();
			String sql = "delete from mydatabase where id=?";
			qr.update(JdbcUtils.getConnection(),sql, id);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	public int getTotalRecord(){
		try {
			QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select count(*) from mydatabase";
			long totalrecord =  qr.query(sql, new ScalarHandler());
			return (int) totalrecord;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
}
