package com.zhougl.handler;

import java.util.*;

/**
 * Properties API 继承hashmap ，不是顺序读写的。
 * 重写Properties按顺序读写
 * @author zhangqy
 * @date 2016年6月14日下午2:47:43
 */
public class OrderedProperties extends Properties {
	private final LinkedHashSet<Object> keys = new LinkedHashSet<Object>();

	public Enumeration<Object> keys() {
		return Collections.<Object> enumeration(keys);
	}

	public Object put(Object key, Object value) {
		keys.add(key);
		return super.put(key, value);
	}

	public synchronized Object remove(Object key) {
		keys.remove(key);
		return super.remove(key);
	}

	public Set<Object> keySet() {
		return keys;
	}

	public Set<String> stringPropertyNames() {
		Set<String> set = new LinkedHashSet<String>();
		for (Object key : this.keys) {
			set.add((String) key);
		}
		return set;

	}
}
