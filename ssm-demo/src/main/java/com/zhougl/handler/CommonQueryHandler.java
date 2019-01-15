package com.zhougl.handler;

import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*****************************
 ** CommonQueryHandler
 ** @author LJHUA 20150524
 *  
 *  1,you can you this class by default constructor method. such as : CommonQueryHandler runner = new CommonQueryHandler(); it will use the "aaa" defaultKey datasource
 *  
 *  2,you can you this class by input a datasource to constructor, such as : CommonQueryHandler runner = new CommonQueryHandler(DBPool.getDataSource("ds_key"));
 *  
 *  3,you can you this class by input a ds_key to constructor method . such as : CommonQueryHandler runner = new CommonQueryHandler("ds_key"); 
 *  	it will use the "aaa" defaultKey datasource
 *
 *******************************/
public class CommonQueryHandler extends QueryRunner {
	
	private static final String defaultKey = "aaa";//默认的数据库
	private String datasourceKey = defaultKey;//
	
	public CommonQueryHandler(){
		super(true);
	}
	
	public CommonQueryHandler(DataSource dataSource){
		super(dataSource,true);
	}
	
	public CommonQueryHandler(String ds_key){
		this.datasourceKey = ds_key;
	}
	
	//use streams read resultset, will use little memory.
	protected PreparedStatement prepareStatement(Connection conn, String sql)
			throws SQLException {
		//System.out.println("===============" +conn.getMetaData().getClass().getName());
		PreparedStatement ps = null;
		
		//only mysql database can set this config. so must judge before query.
		if("com.mysql.jdbc.JDBC4DatabaseMetaData".equals(conn.getMetaData().getClass().getName())){
			ps = conn.prepareStatement(sql,ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			ps.setFetchSize(Integer.MIN_VALUE);
		}else{
			ps = conn.prepareStatement(sql);
		}
		return ps;
	}
	
	//get connection from datasouce,or from ds_key
	protected Connection prepareConnection() throws SQLException {
		if (getDataSource() != null) {
			return getDataSource().getConnection();
		}else{
			//return connection by ds_key;
			return DBPool.getDataSource(datasourceKey).getConnection();
		}
	}
}
