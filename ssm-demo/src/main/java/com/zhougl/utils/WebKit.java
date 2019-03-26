package com.zhougl.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.CharEncoding;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.Cookie;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

public class WebKit {
	private static final String HTTP = "http";
	private static final String HTTPS = "https";
	public static final String host = "cgs.gzjd.gov.cn";
	public static final String CONTENT_TYPE_TEXT_JSON = "text/json";
	public static final String verifyCode = "4Xiqrw6A76HABV3n2Xhf0lcWcE4wvTc0";
	
	static BasicCookieStore cookieStore = null;
	static HttpClientContext context = null;
	static SSLConnectionSocketFactory sslsf = null;
	static PoolingHttpClientConnectionManager cm = null;

	static {
		try {
			init();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
	}
	
	public static void init() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException{
		SSLContextBuilder builder = new SSLContextBuilder();
        // 全部信任 不做身份鉴定
        builder.loadTrustMaterial(null, new TrustStrategy() {
            @Override
            public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                return true;
            }
        });
        
        sslsf = new SSLConnectionSocketFactory(builder.build(), new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.2"}, null, NoopHostnameVerifier.INSTANCE);
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register(HTTP, new PlainConnectionSocketFactory())
                .register(HTTPS, sslsf)
                .build();
		
        cm = new PoolingHttpClientConnectionManager(registry);
        cm.setMaxTotal(200);//max connection
	}

	public static String get(String url) throws Exception {
		BasicCookieStore cookieStore = new BasicCookieStore();
		CloseableHttpClient httpclient = httpsClient(cookieStore);

		try {
			HttpGet httpget = new HttpGet(url);

			httpget.setConfig(defaultRequestConfig());
			String responseBody = httpclient.execute(httpget, responseHandler());

			return responseBody;

		} finally {
			httpclient.close();
		}

	}

	public static String post(String url, String requestBody) throws Exception {
		BasicCookieStore cookieStore = new BasicCookieStore();
		CloseableHttpClient httpclient = httpsClient(cookieStore);

		try {
			HttpPost httppost = new HttpPost(url);
			httppost.setHeader("Content-Type", "application/json;charset=" + CharEncoding.UTF_8);
			httppost.setHeader("Accept", "application/json;charset=" + CharEncoding.UTF_8);

			StringEntity se = new StringEntity(requestBody, CharEncoding.UTF_8);
			se.setContentType(CONTENT_TYPE_TEXT_JSON);

			httppost.setEntity(se);

			httppost.setConfig(defaultRequestConfig());

			// System.out.println("Executing request " + httppost.getRequestLine());
			String responseBody = httpclient.execute(httppost, responseHandler());

			return responseBody;

		} finally {
			httpclient.close();
		}
	}

	public static String doPostForCookie(String url, Map<String, String> params) throws Exception {
		BasicCookieStore cookieStore = new BasicCookieStore();
		CloseableHttpClient httpclient = httpsClient(cookieStore);
		URIBuilder builder = new URIBuilder(url);
		Set<String> set = params.keySet();
		for (String key : set) {
			builder.setParameter(key, params.get(key));
		}
		try {
			URI uri = builder.build();
			HttpPost httppost = new HttpPost(uri.toString());
			httppost.setHeader("Content-Type", "application/json;charset=" + CharEncoding.UTF_8);
			httppost.setHeader("Accept", "application/json;charset=" + CharEncoding.UTF_8);
			httppost.setHeader("Host", host);

			StringEntity se = new StringEntity(JsonUtil.toJSONString(params), CharEncoding.UTF_8);
			se.setContentType(CONTENT_TYPE_TEXT_JSON);
			httppost.setEntity(se);

			httppost.setConfig(defaultRequestConfig());

			String responseBody = httpclient.execute(httppost, responseHandler());
			List<org.apache.http.cookie.Cookie> cookies = cookieStore.getCookies();
			List<Map<String,Object>> cookieList = new ArrayList<>();
			for (org.apache.http.cookie.Cookie cookie : cookies) {
				System.out.println("- " + cookie.toString());
				Map<String,Object> map = new HashMap<>(10);
				map.put("name",cookie.getName());
				map.put("value",cookie.getValue());
				map.put("version",cookie.getVersion());
				map.put("path",cookie.getPath());
				map.put("domain",cookie.getDomain());
				cookieList.add(map);
			}

			JSONObject jsonObject = JsonUtil.fromJson(responseBody);
			jsonObject.put("parameters",Base64Utils.java8Encode(JsonUtil.toJSONString(cookieList)));
//			jsonObject.put("parameters",cookieList);
			return JsonUtil.toJSONString(jsonObject);

		} finally {
			if (httpclient != null)
				httpclient.close();
		}
	}

	public static String doPost(String url, Map<String, String> params) throws Exception {
		BasicCookieStore cookieStore = new BasicCookieStore();
		BasicClientCookie cookie = new BasicClientCookie("arraycookie",
				"wangban2");
		cookie.setVersion(0);
		cookie.setDomain("cgs.gzjd.gov.cn");
		cookie.setPath("/");
		BasicClientCookie cookie1 = new BasicClientCookie("JSESSIONID",
				"4A3C53D713B0AB1ED484928FE84D4D38");
		cookie1.setVersion(0);
		cookie1.setDomain("cgs.gzjd.gov.cn");
		cookie1.setPath("/vehchangeappoint/");
		cookieStore.addCookie(cookie1);
		cookieStore.addCookie(cookie);
		System.out.println(cookieStore);
		CloseableHttpClient httpclient = httpsClient(cookieStore);

		URIBuilder builder = new URIBuilder(url);
		Set<String> set = params.keySet();
		for (String key : set) {
			builder.setParameter(key, params.get(key));
		}

		System.out.println("url===================="+builder);

		try {
			URI uri = builder.build();
			HttpPost httppost = new HttpPost(uri.toString());
			httppost.setHeader("Content-Type", "application/json;charset=" + CharEncoding.UTF_8);
			httppost.setHeader("Accept", "application/json;charset=" + CharEncoding.UTF_8);
//			httppost.setHeader("Host", host);

			StringEntity se = new StringEntity(JsonUtil.toJSONString(params), CharEncoding.UTF_8);
			se.setContentType(CONTENT_TYPE_TEXT_JSON);
			httppost.setEntity(se);

			httppost.setConfig(defaultRequestConfig());

			String responseBody = httpclient.execute(httppost, responseHandler());



			return responseBody;

		} finally {
			if (httpclient != null)
				httpclient.close();
		}
	}

	private static RequestConfig defaultRequestConfig() {
		RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(10000)
				.setConnectionRequestTimeout(60000).build();
		return defaultRequestConfig;
	}

	private static ResponseHandler<String> responseHandler() {
		// Create a custom response handler
		ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

			@Override
			public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
				
				int status = response.getStatusLine().getStatusCode();
                
                HttpEntity entity = response.getEntity();
                if (status >= 200 && status < 300) {
					System.out.println(response.getHeaders("Set-Cookie"));
					return entity != null ? EntityUtils.toString(entity,Consts.UTF_8) : null;
                } else {
                    //throw new ClientProtocolException("Unexpected response status: " + status);
                	throw new ClientProtocolException(entity != null ? EntityUtils.toString(entity,Consts.UTF_8) : "Unexpected response status: " + status);
                }
				
			}

		};

		return responseHandler;
	}
	
	private static CloseableHttpClient httpsClient(BasicCookieStore cookieStore)
			throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException {

		CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .setConnectionManager(cm)
                .setConnectionManagerShared(true)
                .setDefaultCookieStore(cookieStore)
                .build();
        
		return httpclient;

	}

	public static void main(String[] args) throws Exception {
		//String filePath = "/root/gzzw_api_dir/fast-cloud/gzitmsrest/BOOT-INF/classes/urlbody.properties";
		String filePath = "E:\\developtool\\eclipseworkspace\\fast-cloud\\gzitmsrest\\src\\main\\resources\\urlbody.properties";
		Properties pps = new Properties();
		String url = "";
		String jsonObj = "";
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(filePath));
			pps.load(in);
			url = pps.getProperty("url");
			jsonObj = pps.getProperty("jsonObj");

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("url:" + url);
		System.out.println("jsonObj:" + jsonObj);
		System.out.println("-------------------return--json--start---------------------");
		System.out.println(WebKit.post(url, jsonObj));
		System.out.println("-------------------return--json--end---------------------");

	}

}
