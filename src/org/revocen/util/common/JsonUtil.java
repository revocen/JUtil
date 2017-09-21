package org.revocen.util.common;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONTokener;

public class JsonUtil {

	/**
	 * 
	 * @param ja
	 *            json数组
	 * @param field
	 *            要排序的key
	 * @param isAsc
	 *            是否升序
	 */
	@SuppressWarnings("unchecked")
	public static void sort(JSONArray ja, final String field, boolean isAsc) {
		Collections.sort(ja, new Comparator<JSONObject>() {
			
			public int compare(JSONObject o1, JSONObject o2) {
				//这里所有非数字字段均已中文首字母排序
				Comparator comparator = Collator.getInstance(java.util.Locale.CHINA);
				Object f1 = o1.get(field);
				Object f2 = o2.get(field);
				if (f1 instanceof Number && f2 instanceof Number) {
					return ((Number) f1).intValue() - ((Number) f2).intValue();
				} else {
					return comparator.compare(f1.toString(), f2.toString());
				}
			}
		});
		if (!isAsc) {
			Collections.reverse(ja);
		}
	}
	
	public static JSONArray str2JSONArray(String str){
		JSONArray results = new JSONArray();
		Object obj = new JSONTokener(str).nextValue();
		if(obj instanceof JSONObject){
			results.add(obj);
		}else if(obj instanceof JSONArray){
			results = (JSONArray) obj;
		}
		return results;
	}
}
