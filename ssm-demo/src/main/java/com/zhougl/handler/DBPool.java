package com.zhougl.handler;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DBPool {

	public static Map<String,DruidDataSource> dss = new HashMap<String,DruidDataSource>();
	
	public static void loadDataSource(String ds_key){
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			String driverClassName=  null;
			String url=  null;
			String username=  null;
			String password=  null;
			String validationQuery=  null;
			if(Globals.basicdb.equals(ds_key)){
				driverClassName=  ResourceUtils.getConfigByName("datasourceA.driverClassName");
				url=   ResourceUtils.getConfigByName("datasourceA.url");
				username= ResourceUtils.getConfigByName("datasourceA.username");
				password= ResourceUtils.getConfigByName("datasourceA.password");
				validationQuery=  "select 1";
			}else if(Globals.webdb.equals(ds_key)){
				driverClassName=  ResourceUtils.getConfigByName("datasourceB.driverClassName");
				url=   ResourceUtils.getConfigByName("datasourceB.url");
				username= ResourceUtils.getConfigByName("datasourceB.username");
				password= ResourceUtils.getConfigByName("datasourceB.password");
				validationQuery=  "select 1";
			}else if(Globals.thing2thing.equals(ds_key)){
				driverClassName=  ResourceUtils.getConfigByName("datasourceC.driverClassName");
				url=   ResourceUtils.getConfigByName("datasourceC.url");
				username= ResourceUtils.getConfigByName("datasourceC.username");
				password= ResourceUtils.getConfigByName("datasourceC.password");
				validationQuery=  "select 1";
			}else if(Globals.test.equals(ds_key)){
				driverClassName=  ResourceUtils.getConfigByName("datasourceD.driverClassName");
				url=   ResourceUtils.getConfigByName("datasourceD.url");
				username= ResourceUtils.getConfigByName("datasourceD.username");
				password= ResourceUtils.getConfigByName("datasourceD.password");
				validationQuery=  "select 1";
			}else{
				
				conn = getConnection();
				String strSql = "select * from sys_datasource where ds_key = '"+ds_key+"'";
				pstmt = conn.prepareStatement(strSql);
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					
					driverClassName=  rs.getString("driverClassName");
					url=  rs.getString("url");
					username=  rs.getString("username");
					password=  rs.getString("password");
					validationQuery=  rs.getString("validationQuery");
					
				}
				
			}

			try (DruidDataSource ds = new DruidDataSource()) {
				ds.setDriverClassName(driverClassName);
				ds.setUrl(url);
				ds.setUsername(username);
				ds.setPassword(password);
				ds.setInitialSize(1);
				ds.setMaxActive(20);
				ds.setMinIdle(1);
				ds.setMaxWait(60000);
				ds.setValidationQuery(validationQuery);
				ds.setTestOnBorrow(false);
				ds.setTestOnReturn(false);
				ds.setTestWhileIdle(true);
				ds.setTimeBetweenEvictionRunsMillis(60000);
				ds.setMinEvictableIdleTimeMillis(300000);
				// 设置连接是否 超时丢弃
				ds.setRemoveAbandoned(false);
				// 设置2个小时
				ds.setRemoveAbandonedTimeout(14400);
				ds.setLogAbandoned(true);
				ds.setFilters("stat");
				dss.put(ds_key, ds);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
				//logger.error(e.getMessage(),e);
		} finally {
			try {if(rs!=null) rs.close();}catch (SQLException e) {e.printStackTrace();}
			try {if(pstmt!=null) pstmt.close();}catch (SQLException e) {e.printStackTrace();}
			try {if(conn!=null) conn.close();}catch (SQLException e) {e.printStackTrace();}
		}
		
	}
	
	//获取连接
	private static  Connection getConnection() throws Exception{
		Class.forName(ResourceUtils.getConfigByName("datasourceA.driverClassName")).newInstance();
		String url = ResourceUtils.getConfigByName("datasourceA.url");
		String user = ResourceUtils.getConfigByName("datasourceA.username");
		String password = ResourceUtils.getConfigByName("datasourceA.password");
		Connection conn = DriverManager.getConnection(url, user, password);
		return conn;
	}
	
	public static DruidDataSource getDataSource(String ds_key){
		
		if(dss.get(ds_key)==null ) loadDataSource(ds_key);
		return dss.get(ds_key);
	}

	public static String getDriverClass(String ds_key){
		
		if(dss.get(ds_key)==null ) loadDataSource(ds_key);
		
		return getDataSource(ds_key).getDriverClassName();
		
	}
	
	/**
	 * 直接覆盖原有的数据
	 * @author zhangqy
	 * @date 2016年4月18日上午11:33:44
	 */
	public static DruidDataSource changeDataSource(String ds_key){
		loadDataSource(ds_key);
		return dss.get(ds_key);
	}
}
