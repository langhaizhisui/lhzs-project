package cn.lhzs.common.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	public static final String SEPARATOR = ".";

	public static final String EMPTY = "";

	private StringUtil() {
	}

	/**
	 * 判断是否空json
	 * @param jsonValue
	 * @return
	 */
	public static Boolean isEmptyJson(final String jsonValue) {
		return (null == jsonValue || Objects.equals("", jsonValue) || Objects.equals("[]", jsonValue));
	}

	/**
	 * 比较两个字符串
	 * 
	 * @param str1
	 * @param str2
	 * @return boolean
	 */
	public static boolean equals(String str1, String str2) {
		return str1 == null ? false : str2 == null ? true : str1.equals(str2);
	}

	/**
	 * 比较两个字符串，忽略大小写
	 * 
	 * @param str1
	 * @param str2
	 * @return boolean
	 */
	public static boolean equalsIgnoreCase(String str1, String str2) {
		return str1 == null ? false : str2 == null ? true : str1.equalsIgnoreCase(str2);
	}

	/**
	 * 将指定对象转换成字符串
	 *
	 * @param obj
	 *            指定对象
	 * @return 转换后的字符串
	 */
	public static String toString(Object obj) {
		StringBuffer buffer = new StringBuffer();
		if (obj != null) {
			buffer.append(obj);
		}
		return buffer.toString();
	}

	/**
	 * 判断指定字符串是否等于null或空字符串
	 *
	 * @param str
	 *            指定字符串
	 * @return 如果等于null或空字符串则返回true，否则返回false
	 */
	public static boolean isBlank(String str) {
		return str == null || EMPTY.equals(str.trim());
	}

	/**
	 * 判断指定字符串是否不等于null和空字符串
	 *
	 * @param str
	 *            指定字符串
	 * @return 如果不等于null和空字符串则返回true，否则返回false
	 */
	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	/**
	 * 判断是否字符串为空，“” = false, " "=true
	 * 
	 * @param str
	 *            指定字符串
	 * @return boolean
	 */
	public static boolean isEmpty(String str) {
		return StringUtils.isEmpty(str);
	}

	/**
	 * 判断是否字符串不为空
	 * 
	 * @param str
	 *            指定字符串
	 * @return boolean
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 根据默认分隔符获取字符串前缀
	 *
	 * @param str
	 *            指定字符串
	 * @return 返回前缀字符串
	 */
	public static String getPrefix(String str) {
		return getPrefix(str, SEPARATOR);
	}

	/**
	 * 根据指定分隔符获取字符串前缀
	 *
	 * @param str
	 *            指定字符串
	 * @param separator
	 *            指定分隔符
	 * @return 返回字符串前缀
	 */
	public static String getPrefix(String str, String separator) {
		String prefix = "";
		if (StringUtil.isNotBlank(str) && StringUtil.isNotBlank(separator)) {
			int pos = str.indexOf(separator);
			if (pos > 0) {
				prefix = str.substring(0, pos);
			}
		}
		return prefix;
	}

	/**
	 * 根据默认分隔符获取字符串后缀
	 *
	 * @param str
	 *            指定字符串
	 * @return 返回字符串后缀
	 */
	public static String getSuffix(String str) {
		return getSuffix(str, SEPARATOR);
	}

	/**
	 * 根据指定分隔符获取字符串后缀
	 *
	 * @param str
	 *            指定字符串
	 * @param separator
	 *            指定分隔符
	 * @return 返回字符串后缀
	 */
	public static String getSuffix(String str, String separator) {
		String suffix = "";
		if (StringUtil.isNotBlank(str) && StringUtil.isNotBlank(separator)) {
			int pos = str.lastIndexOf(separator);
			if (pos > 0) {
				suffix = str.substring(pos + 1);
			}
		}
		return suffix;
	}

	/**
	 * 根据指定字符串和重复次数生成新字符串
	 *
	 * @param str
	 *            指定字符串
	 * @param repeatCount
	 *            重复次数
	 * @return 返回生成的新字符串
	 */
	public static String newString(String str, int repeatCount) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < repeatCount; i++) {
			buf.append(str);
		}
		return buf.toString();
	}

	/**
	 * 隐藏字符串指定位置的字符
	 *
	 * @param str
	 *            指定字符串
	 * @param index
	 *            起始位置
	 * @param length
	 *            字符长度
	 * @return 返回隐藏字符后的字符串
	 */
	public static String hideChars(String str, int index, int length) {
		return hideChars(str, index, length, true);
	}

	/**
	 * 隐藏字符串指定位置的字符
	 *
	 * @param str
	 *            指定字符串
	 * @param start
	 *            起始位置
	 * @param end
	 *            结束位置
	 * @param confusion
	 *            是否混淆隐藏的字符个数
	 * @return 返回隐藏字符后的字符串
	 */
	public static String hideChars(String str, int start, int end, boolean confusion) {
		StringBuffer buf = new StringBuffer();
		if (StringUtil.isNotBlank(str)) {
			int startIndex = Math.min(start, end);
			int endIndex = Math.max(start, end);
			// 如果起始位置超出索引范围则默认置为0
			if (startIndex < 0 || startIndex > str.length()) {
				startIndex = 0;
			}
			// 如果结束位置超出索引范围则默认置为字符串长度
			if (endIndex < 0 || endIndex > str.length()) {
				endIndex = str.length();
			}
			String temp = newString("*", confusion ? 4 : endIndex - startIndex);
			buf.append(str).replace(startIndex, endIndex, temp);
		}
		return buf.toString();
	}

	/**
	 * 将指定字符串转换成小写
	 *
	 * @param str
	 *            指定字符串
	 * @return 返回转换后的小写字符串
	 */
	public static String toLowerCase(String str) {
		StringBuffer buffer = new StringBuffer(str);
		for (int i = 0; i < buffer.length(); i++) {
			char c = buffer.charAt(i);
			buffer.setCharAt(i, Character.toLowerCase(c));
		}
		return buffer.toString();
	}

	/**
	 * 将指定字符串转换成大写
	 *
	 * @param str
	 *            指定字符串
	 * @return 返回转换后的大写字符串
	 */
	public static String toUpperCase(String str) {
		StringBuffer buffer = new StringBuffer(str);
		for (int i = 0; i < buffer.length(); i++) {
			char c = buffer.charAt(i);
			buffer.setCharAt(i, Character.toUpperCase(c));
		}
		return buffer.toString();
	}

	/**
	 * 将指定字符串转换成驼峰命名方式
	 *
	 * @param str
	 *            指定字符串
	 * @return 返回驼峰命名方式
	 */
	public static String toCalmelCase(String str) {
		StringBuffer buffer = new StringBuffer(str);
		if (buffer.length() > 0) {
			// 将首字母转换成小写
			char c = buffer.charAt(0);
			buffer.setCharAt(0, Character.toLowerCase(c));
			Pattern p = Pattern.compile("_\\w");
			Matcher m = p.matcher(buffer.toString());
			while (m.find()) {
				String temp = m.group(); // 匹配的字符串
				int index = buffer.indexOf(temp); // 匹配的位置
				// 去除匹配字符串中的下划线，并将剩余字符转换成大写
				buffer.replace(index, index + temp.length(), temp.replace("_", "").toUpperCase());
			}
		}
		return buffer.toString();
	}

	/**
	 * 将指定字符串转换成匈牙利命名方式
	 *
	 * @param str
	 *            指定字符串
	 * @return 转换后的匈牙利命名方式
	 */
	public static String toHungarianCase(String str) {
		StringBuffer buffer = new StringBuffer(str);
		if (buffer.length() > 0) {
			Pattern p = Pattern.compile("[A-Z]");
			Matcher m = p.matcher(buffer.toString());
			while (m.find()) {
				String temp = m.group(); // 匹配的字符串
				int index = buffer.indexOf(temp); // 匹配的位置
				// 在匹配的字符串前添加下划线，并将其余字符转换成大写
				buffer.replace(index, index + temp.length(), (index > 0 ? "_" : "") + temp.toLowerCase());
			}
		}
		return buffer.toString();
	}

	/**
	 * 将指定字符串首字母转换成大写字母
	 *
	 * @param str
	 *            指定字符串
	 * @return 返回首字母大写的字符串
	 */
	public static String firstCharUpperCase(String str) {
		StringBuffer buffer = new StringBuffer(str);
		if (buffer.length() > 0) {
			char c = buffer.charAt(0);
			buffer.setCharAt(0, Character.toUpperCase(c));
		}
		return buffer.toString();
	}

	/**
	 * 将指定数组转换成字符串
	 *
	 * @param obj
	 *            指定数组
	 * @return 返回转换后的字符串
	 */
	public static String array2String(Object[] obj) {
		StringBuffer buffer = new StringBuffer();
		if (obj != null) {
			for (int i = 0; i < obj.length; i++) {
				buffer.append(obj[i]).append(",");
			}
		}
		buffer.deleteCharAt(buffer.length() - 1);
		return buffer.toString();
	}

	/**
	 * 字符串合并
	 * 
	 * @param array
	 * @param separator
	 * @return String
	 */
	public static String join(final Object[] array, final String separator) {
		if (array == null) {
			return null;
		}
		return join(array, separator, 0, array.length);
	}

	/**
	 * 字符串合并
	 * 
	 * @param array
	 * @param separator
	 * @param startIndex
	 * @param endIndex
	 * @return String
	 */
	public static String join(final Object[] array, String separator, final int startIndex, final int endIndex) {
		if (array == null) {
			return null;
		}
		if (separator == null) {
			separator = EMPTY;
		}
		final int noOfItems = endIndex - startIndex;
		if (noOfItems <= 0) {
			return EMPTY;
		}

		final StringBuilder buf = new StringBuilder(noOfItems * 16);

		for (int i = startIndex; i < endIndex; i++) {
			if (i > startIndex) {
				buf.append(separator);
			}
			if (array[i] != null) {
				buf.append(array[i]);
			}
		}
		return buf.toString();
	}

	/**
	 * 多参数是否含有空字符串
	 * 
	 * @param args
	 * @return boolean
	 */
	public static boolean isContentBlank(String... args) {
		boolean ret = false;
		for (String string : args) {
			if (isBlank(string)) {
				ret = true;
				break;
			}
		}
		return ret;
	}

	public static boolean isNumber(String str){
        Matcher isNum = Pattern.compile("[0-9]*").matcher(str);
        return isNum.matches();
	}

	private static boolean checkPwdLength(String pwd) {
		String regex = "\\S{6,16}";
		return pwd.matches(regex);
	}
	public static  boolean checkPwd(String str) {
		String regex = ".*[a-zA-Z]+.*";
		String regex2 = ".*[0-9]+.*";
        return  str.matches(regex) && str.matches(regex2);
	}

	public static  boolean pwdIsLegal(String pwd) {
	    if (checkPwdLength(pwd) && !isNumber(pwd) && checkPwd(pwd)) return true;
	    return false;
    }
	/**
	 * 属性json串：[{"attrid-name":"730-味道","valid-name":"587-香蕉味"}]，拼接成名称串：味道/香蕉味,...
	 * @param skuJson
	 * @return
	 */
	public static String joinSkuJsonValName(String skuJson) {
		if (StringUtil.isBlank(skuJson)) {
			return "";
		}
		String rst = "";
		JSONArray skuArry = JSONArray.parseArray(skuJson);
		if (!skuArry.isEmpty()) {
			List strList = new ArrayList();
			for(int i = 0; i < skuArry.size(); i++){
				JSONObject json = (JSONObject) skuArry.get(i);
				String attridName = json.getString("attrid-name");
				String validName = json.getString("valid-name");
				int aEndIndex = attridName.indexOf("-") > -1 ? attridName.indexOf("-") : attridName.length();
				int vEndIndex = validName.indexOf("-") > -1 ? validName.indexOf("-") : validName.length();
				String attName = attridName.substring(aEndIndex +1);
				String valName = validName.substring(vEndIndex + 1);
				StringBuilder sb = new StringBuilder();
				sb.append(attName).append("/").append(valName);
				if (StringUtil.isNotBlank(sb.toString())) {
					strList.add(sb.toString());
				}
			}
			Collections.sort(strList);
			rst = StringUtils.join(strList.iterator(),",");
		}
		return rst;
	}

	public static String getRandomStringByLength(int length) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

}
