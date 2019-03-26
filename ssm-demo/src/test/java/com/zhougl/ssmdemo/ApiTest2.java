package com.zhougl.ssmdemo;

import java.net.URLEncoder;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import com.zhougl.utils.JsonUtil;
import com.zhougl.utils.WebKit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * 公积金单元测试
 * 
 * @author ruanzm
 *
 */
@RunWith(SpringJUnit4ClassRunner.class) // 使用junit4进行测试
// ------------如果加入以下代码，所有继承该类的测试类都会遵循该配置，也可以不加，在测试类的方法上///控制事务，参见下一个实例
// 这个非常关键，如果不加入这个注解配置，事务控制就会完全失效！
// @Transactional
// 这里的事务关联到配置文件中的事务控制器（transactionManager =
// "transactionManager"），同时//指定自动回滚（defaultRollback = true）。这样做操作的数据才不会污染数据库！
// @TransactionConfiguration(transactionManager = "transactionManager",
// defaultRollback = true)
// ------------
public class ApiTest2 {

	static Logger logger = LoggerFactory.getLogger(ApiTest2.class);
	// static String base_url = "https://10.194.252.196:15003/";
	// static String base_url = "https://master-2:15003/";
	static String base_url = "https://10.197.20.33:15003/";
	// static String base_url = "https://host-10-197-20-39:15003/";
	// static String base_url = "https://localhost:15003/";

	/*
	 * 车辆异地转籍-校验车辆信息
	 */
	@Test
	public void applywizard() throws Exception {
		String url = "http://cgs.gzjd.gov.cn/vehchangeappoint/service/apply/wizard";
		//?fdjh=0466&yzm=AABB&step=1&jdzcl=0&biztype=0167&hpzl=02&hphm=AS4R44&province=粤
		Map<String, String> req = new HashMap<>();
		req.put("fdjh","0466");
		req.put("yzm","AABB");
		req.put("step","1");
		req.put("jdzcl","0");
		req.put("biztype","0167");
		req.put("hpzl","02");
		req.put("hphm","AS4R44");
		req.put("province","粤");
		
		logger.info("url={}", url);
		logger.info("jsonObj={}", JsonUtil.toJSONString(req));

		String result = WebKit.doPostForCookie(url, req);

		logger.info("result={}", result);
	}
	/*
	 * 车辆异地转籍-获取可预约日期接口
	 */
	@Test
	public void applywizard2() throws Exception {

		byte[] cookieList = Base64.getDecoder().decode("VzN0d1lYUm9QUzkyWldoamFHRnVaMlZoY0hCdmFXNTBMeXdnWkc5dFlXbHVQV05uY3k1bmVtcGtMbWR2ZGk1amJpd2dibUZ0WlQxS1UwVlRVMGxQVGtsRUxDQjJZV3gxWlQwMlF6SkNNVU5GUmprM09UazVPVFF3UmpoRVJrVkRPVGN3T1RjM01qZzJSaXdnZG1WeWMybHZiajB3ZlN3Z2UzQmhkR2c5THl3Z1pHOXRZV2x1UFdObmN5NW5lbXBrTG1kdmRpNWpiaXdnYm1GdFpUMWhjbkpoZVdOdmIydHBaU3dnZG1Gc2RXVTlkMkZ1WjJKaGJqRXNJSFpsY25OcGIyNDlNSDFk".getBytes("UTF-8"));

		String s = new String(cookieList);
		System.out.println(s);
		String url = "http://cgs.gzjd.gov.cn/vehchangeappoint/service/apply/wizard";
		//?step=3&token=vqWr4Nw0yOO7h0vtywHymBi7hQcCNs7UzEoVLs3E6vU=
		Map<String, String> req = new HashMap<>();
		req.put("step","3");
		req.put("token","vqWr4Nw0yOO7h0vtywHymIcCejOiPGuagDYjvO3DqE4=");
		
		logger.info("url={}", url);
		logger.info("jsonObj={}", JsonUtil.toJSONString(req));
		
//		String result = WebKit.doPost(url, req);
		
//		logger.info("result={}", result);
	}

}
