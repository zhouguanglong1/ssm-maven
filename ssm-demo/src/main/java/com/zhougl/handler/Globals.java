package com.zhougl.handler;


/**
 * 系统中全局常量名称
 * @author liujinghua
 * @DATE 2014-05-15 早上11:30
 */
public class Globals {
	
	/****系统模块使用的全局变量开始************************************/
	public static final String Syssendi_system_title="sendi_system_title";
	public static final String Syssendi_sys_config_para="sendi_sys_config_para";
	public static final String Syssendi_sys_proload_info="sendi_sys_proload_info";
	public static final String Syssendi_sys_host_address_ip="sendi_sys_host_address_ip";
	public static final String Syssendi_sys_host_name_hostname="sendi_sys_host_name_hostname";
	public static final String SysActionLogConf="PreLoadActionLogConf";//用户操作Action日志配置
	public static final String Scheduler = "scheduler";
	public static final String UserButtonOperations = "userButtonOperations";//用户功能权限信息
	public static final String EhCacheName = "dictcache";//缓存的名称
	public static final String DefaultCharset = "UTF-8";//编码格式
	//public static final String Port = ResourceUtils.getConfigByName("tomcat_port");//全局应用端口变量
	//保存session中
	public static final String Sesusername="username";
	public static final String Sesuserid="userid";
	public static final String Sesrolename="rolename";
	public static final String Sesroleid="roleid";
	public static final String REGRIONSTR = "regionstr";
	
	public static final String Userid="admin";//角色管理过滤
	public static final String Roleid="0";//用户管理过滤
	public static final String OnlineUsers = "onlineUsers";//在线人员
	public static final String System_Msg = "system_msg";//推送类型：系统消息
	public static final String Login_User = "login_user";//推送类型：用户上线
	public static final String Logout_User = "logout_user";//推送类型：用户退出下线
	public static final String Forced_Off_Line = "forced_off_line";//推送类型：强制下线
	/****系统模块使用的全局变量开始************************************/
	
	/****业务模块使用的全局变量开始************************************/
	
	
	//不进行权限过滤的URL,已迁移至excludeUrls.xml里面配置 edited by liujh 20150517
	/*public static List<String> excludeUrls = new ArrayList<String>();
	static {
		excludeUrls.add("/onlineCgReportController.do");//动态报表
		excludeUrls.add("/onlineCgReportExcelController.do");//动态报表导出
		
		excludeUrls.add("/commonDataController.do");//公共数据接口开放
		
	}*/
	
	//数据源KEY配置
	public static final String LOCAL="basicdb";
	public static final String jthxwg="jthxwg";
	public static final String basicdb="basicdb";
	public static final String voltedata="voltedata";
	public static final String nbiot = "nbiot";
	public static final String smsweb = "smsweb";
	public static final String webdb = "webdb";
    public static final String thing2thing = "thing2thing";
    /****业务模块使用的全局变量结束************************************/
}
