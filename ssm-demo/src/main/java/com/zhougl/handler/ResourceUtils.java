package com.zhougl.handler;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

/**
 * @author LJHUA 2014-08-26
 * 增加多个配置文件支持
 */
public class ResourceUtils {
	private static final String defaultResourceFile = "application";
	private static Map<String, Properties> bundles = new HashMap<String, Properties>();
	private static Map<String,Map<String,String>> resource = new HashMap<String, Map<String,String>>();
	private static void loadResourceBundle(String resourceFile) {
		Properties properties = new Properties();
		//String path2 = ResourceUtils.class.getClassLoader().getResource(resourceFile+".properties").getPath();
		String path3 = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		InputStream is;
		try {
			is = new FileInputStream(path3+resourceFile+".properties");
			properties.load(is);
			if(is!=null) is.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		bundles.put(resourceFile, properties);
	}

	/**
	 * 获取配置文件参数
	 * @param name
	 * @return
	 */
	public static final String getConfigByName(String name) {
		return getConfigByName(name,defaultResourceFile);
	}
	
	/**
	 * 清空bundle,清空所有的properties缓存
	 * @return
	 */
	public static final Map<String, Properties> clear() {
		bundles.clear();
		return bundles;
	}

	public static final String getConfigByName(String name, String resourceFile) {
		if (bundles.get(resourceFile) == null) {
				loadResourceBundle(resourceFile);
		}
		Properties prop  = bundles.get(resourceFile);
		
		//存在则翻译
		if(prop.containsKey(name)){
			try {
				return new String(prop.getProperty(name).getBytes("ISO-8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		return name;//如果未找到，则返回原始值
	}
	
	public static List<Map<String,String>> getAllPropertiesWithDict(String resourceFile){
		
		if (bundles.get(resourceFile) == null) {
			loadResourceBundle(resourceFile);
		}
		Properties prop  = bundles.get(resourceFile);
		List<Map<String,String>> result = new ArrayList<Map<String,String>>();
		
		for(Object key : prop.keySet()) {
			
			try {
				Map<String,String> aKeyValue = new HashMap<String,String>();
				aKeyValue.put("dic_code", key+"");
				aKeyValue.put("dic_value", new String(prop.getProperty(key+"").getBytes("ISO-8859-1"),"UTF-8"));
				result.add(aKeyValue);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		return result;
		
	}

	public static List<Map<String,String>> getAllPropertiesWithDictContainCode(String resourceFile){

		if (bundles.get(resourceFile) == null) {
			loadResourceBundle(resourceFile);
		}
		Properties prop  = bundles.get(resourceFile);
		List<Map<String,String>> result = new ArrayList<Map<String,String>>();

		for(Object key : prop.keySet()) {

			try {
				Map<String,String> aKeyValue = new HashMap<String,String>();
				aKeyValue.put("dic_code", key+"");
				aKeyValue.put("dic_value", "["+key+"]"+new String(prop.getProperty(key+"").getBytes("ISO-8859-1"),"UTF-8"));
				result.add(aKeyValue);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		return result;

	}

	public static void main(String[] args) throws Exception{
		//System.out.println(ResourceUtils.getConfigByName("4","key/FDB_imssip_cdr#cdr_type"));
		//System.out.println(ResourceUtils.getConfigByName("tomcat_port"));
		//List<String> list = FileUtils.readLines(new File("E://SVNProject//ydvolte//src//key//FDB_imssip_cdr#cdr_type.properties"));
		Properties properties = new OrderedProperties();
		InputStream inputStream = ResourceUtils.class.getClassLoader().getResourceAsStream("key/FDB_imssip_cdr#cdr_type.properties");
		try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
            properties.load(bf);
            Set<Object> keys  = properties.keySet();
    		for(Object key:keys){
    			System.out.println(key+":"+properties.get(key));
    			
    		}
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
        	inputStream.close(); // 关闭流
        }
	}

	public static int getServerPort(HttpServletRequest request) {
		return request.getServerPort();
	}
	
	
	public static Map<String,String> getAll(String resourceFile) throws Exception{
		if (bundles.get(resourceFile) == null) {
			loadResourceBundle(resourceFile);
			
		}
		if(resource.get(resourceFile) ==null){
			loadResource(resourceFile);
		}
		return resource.get(resourceFile);
		
		//return result;
	}
	
	public static void loadResource(String resourceFile) throws Exception{
		Map<String,String> result = new LinkedHashMap<String, String>();
		Properties properties = new OrderedProperties();
		InputStream inputStream = ResourceUtils.class.getClassLoader().getResourceAsStream(resourceFile+".properties");
		try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            properties.load(bf);
            Set<Object> keys  = properties.keySet();
    		for(Object key:keys){
    			result.put(key.toString(), properties.get(key).toString());
    			//result.put(key.toString(), new String(properties.get(key).toString().getBytes("ISO-8859-1"),"UTF-8"));
    		}
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
        	inputStream.close(); // 关闭流
        }
		
		resource.put(resourceFile, result);
	}
	
}
