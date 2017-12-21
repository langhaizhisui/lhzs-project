package cn.lhzs.common.util;

import java.text.DecimalFormat;

/**
 * 数字相关工具类，针对decimal格式
 */
public class NumberUtil {
	public static DecimalFormat decimalFormat1 = new DecimalFormat("###,##0.00");
	public static DecimalFormat decimalFormat2 = new DecimalFormat("###,##0.0000");

	/** 格式 如：10000,010 */
	public static final DecimalFormat PRICE_3DOT_FORMAT = new DecimalFormat("###,###");

	/** 价格格式 如：10000.01 */
	public static final DecimalFormat PRICE_2DOT_FORMAT = new DecimalFormat("#.##");

	/** 价格格式 如：10000.010 */
	public static final DecimalFormat PRICE_DOT3_FORMAT = new DecimalFormat("#.###");

	/** 价格格式 如：10,000.01 */
	public static final DecimalFormat PRICE_K2DOT_FORMAT = new DecimalFormat("¥#,###.##");

	public static String formatQty(double qty) {
		return decimalFormat2.format(qty);
	}

	public static double parseQty(String qtyStr) {
		try {
			return decimalFormat2.parse(qtyStr).doubleValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static String formatMoney(double money) {
		return decimalFormat1.format(money);
	}

	public static double parseMoney(String moneyStr) {
		try {
			return decimalFormat1.parse(moneyStr).doubleValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static void main(String[] args) {
		double d = 6d;
		System.out.println(PRICE_K2DOT_FORMAT.format(d));
	}
}
