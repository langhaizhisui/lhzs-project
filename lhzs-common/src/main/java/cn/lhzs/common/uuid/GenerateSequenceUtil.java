package cn.lhzs.common.uuid;

import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 当前日期生成随机数，yyyyMMddHHmmss + 0001（4位增长序列） 注：不适用分布式部署
 */
public class GenerateSequenceUtil {

	/**
	 * The FieldPosition.
	 */
	private static final FieldPosition HELPER_POSITION = new FieldPosition(0);

	/**
	 * This Format for format the number to special format.
	 */
	private final static NumberFormat numberFormat = new DecimalFormat("0000");

	/**
	 * This int is the sequence number ,the default value is 0.
	 */
	private static int seq = 0;

	private static final int MAX = 9999;

	private static final String DATE_PATTERN_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

	/**
	 * 根据格式化的日期来生成一个id
	 *
	 * @return String
	 */
	public static synchronized String generateSequenceNo(String pattern) {
		if (pattern == null || pattern.length() == 0) {
			pattern = DATE_PATTERN_YYYYMMDDHHMMSS;
		}
		Calendar rightNow = Calendar.getInstance();
		StringBuffer sb = new StringBuffer();
		Format dateFormat = new SimpleDateFormat(pattern);
		dateFormat.format(rightNow.getTime(), sb, HELPER_POSITION);
		numberFormat.format(seq, sb, HELPER_POSITION);
		if (seq == MAX) {
			seq = 0;
		} else {
			seq++;
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println(generateSequenceNo("2017-02-04 30:40"));
		}
	}
}
