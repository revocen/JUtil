package org.revocen.util.common;

public class UperCase {

	public static String[] CHINESE_UPER = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
	public static String[] UNIT = {"拾", "佰", "仟", "万", "拾", "佰", "仟", "亿" };

	public static String numToUpper(String num) {
		String u[] = { "〇", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
		char[] str = num.toCharArray();
		String rstr = "";
		for (int i = 0; i < str.length; i++) {
			rstr = rstr + u[Integer.parseInt(str[i] + "")];
		}
		return rstr;
	}

	public static String monthToUppder(String month) {
		if (Integer.parseInt(month) < 10) {
			return numToUpper(month);
		} else if (Integer.parseInt(month) == 10) {
			return "十";
		} else {
			return "十" + numToUpper((Integer.parseInt(month) - 10) + "");
		}
	}

	public static String dayToUppder(String day) {
		if (Integer.parseInt(day) < 20) {
			return monthToUppder(day);
		} else {
			char[] str = String.valueOf(day).toCharArray();
			if (str[1] == '0') {
				return numToUpper(str[0] + "") + "十";
			} else {
				return numToUpper(str[0] + "") + "十" + numToUpper(str[1] + "");
			}
		}
	}

	public static String GetUperNum(String str) {
		String uperStr = "";
		for (int i = 0; i < str.length(); i++) {
			if (i + 1 <= str.length()) {

				uperStr += getChineseNumMapping(str.substring(i, i + 1));
				switch (str.length() - i - 1) {
				case 0:
					break;
				case 1:
					uperStr += "拾";
					break;
				case 2:
					uperStr += "佰";
					break;
				case 3:
					uperStr += "仟";
					break;
				case 4:
					uperStr += "万";
					break;
				case 5:
					uperStr += "拾万";
					break;
				case 6:
					uperStr += "佰万";
					break;
				case 7:
					uperStr += "千万";
					break;
				}
			}
		}
		// if (uperStr.StartsWith("壹拾"))
		// {
		// uperStr = uperStr.TrimStart('壹');
		// }
		uperStr = uperStr.replace("零拾", "零");
		uperStr = uperStr.replace("零佰", "零");
		uperStr = uperStr.replace("零仟", "零");
		uperStr = uperStr.replace("零万", "零");
		while (uperStr.contains("零零")) {
			uperStr = uperStr.replace("零零", "零");
		}
		if (uperStr.length() > 1) {
			uperStr = uperStr.replace("零", "");
			// uperStr = uperStr.trim("零".toCharArray());
		}
		return uperStr;
	}

	public static String getChineseNumMapping(String str) {
		if ("0".equals(str)) {

			return "零";

		} else if ("1".equals(str)) {

			return "壹";

		} else if ("2".equals(str)) {

			return "贰";

		} else if ("3".equals(str)) {

			return "叁";

		} else if ("4".equals(str)) {

			return "肆";

		} else if ("5".equals(str)) {

			return "伍";

		} else if ("6".equals(str)) {

			return "陆";

		} else if ("7".equals(str)) {

			return "柒";

		} else if ("8".equals(str)) {

			return "捌";

		} else if ("9".equals(str)) {

			return "玖";
		}

		return "";
	}

	/**
	 * 获取千亿内浮点数的中文大写
	 * @param target
	 * @return
	 * @throws Exception
	 */
	public static String toChineseDecimal(String target) throws Exception {

		int a = target.indexOf('.');
		if (a == -1) {
			return toChineseNumIntegerPart(target);
		}
		String[] temp1 = new String[2];
		temp1[0] = target.substring(0, a);
		temp1[1] = target.substring(a + 1);

		String result1 = toChineseNumIntegerPart(temp1[0]); // 整数部分
		String decimalNum = temp1[1];

		char[] array = decimalNum.toCharArray();

		// 获取最后一位有效数字
		int effectiveIndex = -1;
		for (int i = array.length - 1; i >= 0; i--) {
			char temp = array[i];
			if (temp == '0') {
				continue;
			} else {
				effectiveIndex = i;
				break;
			}
		}

		// 小数部分全为0
		if (effectiveIndex == -1) {
			return result1;
		}

		StringBuffer decimalPartResult = new StringBuffer("点");
		String effectiveString = "";
		// 截取有效数字位
		if (effectiveIndex == 0) {
			// 第一位为有效数字
			effectiveString = temp1[1].substring(0, 1);
		} else {
			effectiveString = temp1[1].substring(0, effectiveIndex + 1);
		}

		char[] effArray = effectiveString.toCharArray();
		for (int i = 0; i < effArray.length; i++) {
			char temp = effArray[i];
			decimalPartResult.append(getChineseNumMapping(temp + ""));
		}
		return result1 + decimalPartResult.toString();
	}

	/**
	 * 获取千亿内整数的中文大写
	 * @param integerInput
	 * @return
	 * @throws Exception
	 */
	public static String toChineseNumIntegerPart(String target) throws Exception {
		// 1000,0234,5678
		target = target.replace(",", "");
		String numStr = target + "";
		if (numStr.length() > 12) { // 1000亿
			throw new Exception("超出计算范围");
		}

		String[] nums = new String[3];

		if (numStr.length() > 8) {
			int index1 = numStr.length()- 4;
			nums[2] = numStr.substring(index1, numStr.length());
			
			int index2 = numStr.length()- 8;
			nums[1] = numStr.substring(index2, index1);
			
			if (index2 == 0) {
				nums[0] = numStr.substring(0, 1);
			}else {
				nums[0] = numStr.substring(0, index2);
			}
		} else if (numStr.length() > 4) {
			int index = numStr.length()- 4;
			nums[2] = numStr.substring(index, numStr.length());
			
			if (index == 0) {
				nums[1] = numStr.substring(0, 1);
			}else {
				nums[1] = numStr.substring(0, index);
			}
		} else {
			nums[2] = numStr;
		}

		String[] results = new String[3];

		// 亿
		results[0] = get4DigitChinese(nums[0]);
		// 万
		results[1] = get4DigitChinese(nums[1]);
		// 个
		results[2] = get4DigitChinese(nums[2]);

		String result = "";
		if (StringUtils.isNotBlank(results[0])) {
			result += results[0] + "亿";
		}

		if (StringUtils.isNotBlank(results[1])) {
			result += results[1] + "万";
		}

		result += results[2];

		return result;
	}

	/**
	 * 获取4位整数的中文大写
	 * @param integerInput
	 * @return
	 */
	public static String get4DigitChinese(String target) {
		if (StringUtils.isBlank(target)) {
			return "";
		}
		int num = Integer.parseInt(target);
		String numStr = num + "";
		char[] array = numStr.toCharArray();
		int zeroLen = 0;
		for (int i = array.length - 1; i >= 0; i--) {
			char a = array[i];
			if (a == '0') {
				zeroLen++;
			} else {
				break;
			}
		}

		StringBuffer result = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			char a = array[i];
			String chineseNum = getChineseNumMapping(a + "");
			int index = array.length - i - 1;
			String unit = "";
			if (a != '0' && index > 0) {
				unit = UNIT[index - 1];
			}

			result.append(chineseNum + unit);
		}

		String outStr = "";
		if (zeroLen == 0) {
			outStr = result.toString();

		} else {
			outStr = result.toString().substring(0, result.length() - zeroLen);

		}
		return outStr.replace("零零", "零");
	}
}