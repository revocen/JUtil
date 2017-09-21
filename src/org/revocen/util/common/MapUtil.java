package org.revocen.util.common;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapUtil {

	public static final String GET = "get";
	public static final String SET = "set";

	public static <T> T convertToEntity(Class<T> clazz, Map<String, Object> map) {
		T res = null;
		try {
			Constructor<T> constructor = clazz.getConstructor();
			res = constructor.newInstance();
			List<String> keys = getMapKeys(map);
			Field fields[] = clazz.getDeclaredFields();

			for (int i = 0; i < fields.length; i++) {
				String fieldName = fields[i].getName();
				if (keys.contains(fieldName)) {
					Object o = map.get(fieldName);
					String methodName = SET + StringUtils.captureName(fieldName);
					Method method = clazz.getDeclaredMethod(methodName, o.getClass());
					method.invoke(res, o);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	public static <T> List<T> convertToEntity(Class<T> clazz, List<Map<String, Object>> lst) {
		List<T> res = new ArrayList<T>();
		try {
			for (Map<String, Object> map : lst) {
				res.add(convertToEntity(clazz, map));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}
	
	public static List<String> getMapKeys(Map<String,Object> map) {
		List<String> result = new ArrayList<String>();
		Set<String> keys = map.keySet();
		java.util.Iterator<String> iterator = keys.iterator();
		while (iterator.hasNext()) {
			String one = iterator.next();
			result.add(one);
		}
		return result;
	}
	
	public static List<String> getMapKeys(List<Map<String,Object>> lst) {
		List<String> result = new ArrayList<String>();		
		for (Map<String,Object> map : lst) {			
			Set<String> keys = map.keySet();			
			java.util.Iterator<String> iterator = keys.iterator();
			while (iterator.hasNext()) {
				String one = iterator.next();
				if (result.contains(one)) {
					continue;
				}
				result.add(one);
			}			
		}
		return result;
	}
}
