package com.zhougl.utils;

import sun.misc.BASE64Decoder;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @author zhougl 2019/1/8
 **/
public class Base64Utils {
	// 不换行的jdk8的编码方式
	public static String java8Encode(String s) {
		if (s == null)
			return null;
		String encode = "";
		try {
			encode = new String(Base64.getEncoder().encode(s.getBytes("UTF-8")), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encode;
	}

	// 不换行的jdk8的解码方式
	public static String java8Decode(String s) {
		if (s == null)
			return null;
		String decode = "";
		try {
			decode = new String(Base64.getDecoder()
					.decode(s.replaceAll("\r", "").replaceAll("\n", "").replaceAll(" ", "").getBytes()), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return decode;
	}

	// 自动换行的编码方式
	public static String encode(String s) {
		if (s == null)
			return null;
		String res = "";
		try {
			res = new sun.misc.BASE64Encoder().encode(s.getBytes("GBK"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return res;
	}

	// 自动换行的解码方式
	public static String decode(String s) {
		if (s == null)
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(s);
			return new String(b, "GBK");
		} catch (Exception e) {
			return null;
		}
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		String s = "哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈";
		// System.out.println(java8Encode(s));
		System.out.println("使用jdk1.8的base64解码结果:" + java8Decode(
				"PGNvbnRyYWN0Um9vdD48cG9saWN5SW5mb3M+PHBvbGljeUluZm8+PGNsYXNzQ29kZT48L2NsYXNzQ29kZT48cmVjZWl2ZU9iaj5wdHJhY2U8L3JlY2VpdmVPYmo+PHRpdGxlPuW5v+S4nOecgeesrOS4gOeOr+Wig+S/neaKpOedo+Wvn+e7hOS6pOWKnuW5v+W3nuW4gueOr+Wig+S/oeiuv+S7tuiwg+afpeWkhOeQhuaDheWGteWFrOekuuihqO+8iOesrOS4gOiHs+WNgeS4gOaJuSDmiKroh7MyMDE45bm0MTLmnIgyNOaXpTEy5pe277yJPC90aXRsZT48dXJsPnd3dy5nei5nb3YuY24vZ3pnb3YvenRfc2hqYmhkY194eGdrLzIwMTgxMi83ZTI3NjQwMjQ4ZDg0OTMxYjg3MTRlY2EzOGYzMDM0My5zaHRtbDwvdXJsPjxyZWNlaXZlSWQ+MzcxMTAyMTk4NDA3MTAyOTMwPC9yZWNlaXZlSWQ+PC9wb2xpY3lJbmZvPjwvcG9saWN5SW5mb3M+PC9jb250cmFjdFJvb3Q+"));
		System.out.println("------------------------------------------");
		System.out.println("使用sun的base64解码结果:" + decode(
				"PGNvbnRyYWN0Um9vdD48cG9saWN5SW5mb3M+PHBvbGljeUluZm8+PGNsYXNzQ29kZT48L2NsYXNzQ29kZT48cmVjZWl2ZU9iaj5wdHJhY2U8L3JlY2VpdmVPYmo+PHRpdGxlPuW5v+S4nOecgeesrOS4gOeOr+Wig+S/neaKpOedo+Wvn+e7hOS6pOWKnuW5v+W3nuW4gueOr+Wig+S/oeiuv+S7tuiwg+afpeWkhOeQhuaDheWGteWFrOekuuihqO+8iOesrOS4gOiHs+WNgeS4gOaJuSDmiKroh7MyMDE45bm0MTLmnIgyNOaXpTEy5pe277yJPC90aXRsZT48dXJsPnd3dy5nei5nb3YuY24vZ3pnb3YvenRfc2hqYmhkY194eGdrLzIwMTgxMi83ZTI3NjQwMjQ4ZDg0OTMxYjg3MTRlY2EzOGYzMDM0My5zaHRtbDwvdXJsPjxyZWNlaXZlSWQ+MzcxMTAyMTk4NDA3MTAyOTMwPC9yZWNlaXZlSWQ+PC9wb2xpY3lJbmZvPjwvcG9saWN5SW5mb3M+PC9jb250cmFjdFJvb3Q+"));
	}
}
