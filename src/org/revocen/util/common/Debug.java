package org.revocen.util.common;

public class Debug {
	
	private static final Boolean DEBUG = true;
	public static String TAG = "TAG";
	public static String SEPARATOR = "===>";
	
	public static void i(String out){
		i(null, out);
	}
	
	public static void i(String tag, String out){
		i(tag,null, out);
	}
	
	public static void i(String tag, String separator, String out){
		i(tag,separator, new String [] {out});
	}
	
	public static void i(String tag, String separator, String [] args){ 
		if (DEBUG) {
			System.out.println("\r\n====================== Debug Log Start =======================");
			System.out.println("Date : " + DateUtils.getNowDateTime() + "\r\n");
			if (StringUtils.isBlank(tag)) {
				tag = TAG;
			}
			
			if (StringUtils.isBlank(separator)) {
				separator = SEPARATOR;
			}
			
			for (String temp : args) {
				System.out.println(tag + separator + temp);
			}
			
			System.out.println("\r\n====================== Debug Log End =======================");
		}
	}
	
	public static void main(String[] args) {
		i("log"," ===》 ", new String [] {"小明", "小红"});
	}
	
	public enum DebugModel{
		/**
		 * 正式版
		 */
		Formal(1),
		
		/**
		 * 演示版
		 */
		Demo(2),
		
		/**
		 * 测试-权限
		 */
		Test_Jurisdiction(3),
		
		/**
		 * 测试-非权限测试
		 */
		Test_Common(4),
		;
		
		private final int value;
		
		DebugModel(int value){
			this.value = value;
		}
		
		public static Boolean getDebug(DebugModel model){
			switch (model) {
			case Formal:
			case Demo:
				return false;

			default:
				return true;
			}
		}
	}
}
