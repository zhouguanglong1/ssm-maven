package com.zhougl.handler;


import java.util.HashMap;
import java.util.Map;

public class QueryRunnerUtils {
	
	private static Map<String,CommonQueryHandler> runners = new HashMap<String,CommonQueryHandler>();
	
	public static CommonQueryHandler getRunner(String ds_key){
		
		if(runners.get(ds_key) == null){
			runners.put(ds_key, new CommonQueryHandler(DBPool.getDataSource(ds_key)));
		}
		
		return runners.get(ds_key);
	}
	
	public static CommonQueryHandler getDefaultRunner(){
		String ds_key = Globals.basicdb;
		if(runners.get(ds_key) == null){
			runners.put(ds_key, new CommonQueryHandler(DBPool.getDataSource(ds_key)));
		}
		
		return runners.get(ds_key);
	}
	
	/**
	 * 修改数据源后，刷新数据源覆盖原有的数据源配置
	 * @author zhangqy
	 * @date 2016年4月18日上午11:31:08
	 */
	public static void changRunner(String ds_key){
		runners.put(ds_key, new CommonQueryHandler(DBPool.changeDataSource(ds_key)));
	}

}
