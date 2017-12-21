package cn.lhzs.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期操作辅助类
 *
 * @author ZHX
 */
public final class DateUtil {
	private DateUtil() {
	}

	/**
	 * 秒的毫秒数
	 */
	public final static long SECOND_MILLIS = 1000;

	/**
	 * 分钟的毫秒数
	 */
	public final static long MIN_MILLIS = 60 * SECOND_MILLIS;

	/**
	 * 小时的毫秒数
	 */
	public final static long HOUR_MILLIS = 60 * MIN_MILLIS;

	/**
	 * 天的毫秒数
	 */
	public final static long DAY_MILLIS = 24 * HOUR_MILLIS;

	/**
	 * 拼接的开始时间
	 */
	public final static String START_TIME = " 00:00:00";

	/**
	 * 拼接的结束时间
	 */
	public final static String END_TIME = " 23:59:59";

	static String PATTERN = "yyyy-MM-dd";

	/**
	 * yyyy-MM-dd
	 */
	public static final String DATE_PATTERN_YYYY_MM_DD = "yyyy-MM-dd";

	/**
	 * yyyyMMddHHmmss
	 */
	public static final String DATE_PATTERN_YYYY_MM_DD_HH_MM_SS = "yyyyMMddHHmmss";

	/**
	 * yyyyMMdd
	 */
	public static final String DATE_PATTERN_YYYYMMDD = "yyyyMMdd";

	/**
	 * yyyy-MM
	 */
	public static final String DATE_PATTERN_YYYY_MM = "yyyy-MM";

	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static final String DATE_PATTERN_YYYY_MM_DDHHMMSS = "yyyy-MM-dd HH:mm:ss";

	/**
	 * yyyy-MM-dd HH:mm
	 */
	public static final String DATE_PATTERN_YYYY_MM_DDHHMM = "yyyy-MM-dd HH:mm";

	/**
	 * yyyy年MM月dd日 HH:mm
	 */
	public static final String DATE_PATTERN_PUBULISH_TIME = "yyyy年MM月dd日 HH:mm";

	/**
	 * 格式化日期
	 * 
	 * @param date
	 * @return
	 */
	public static final String format(Object date) {
		return format(date, PATTERN);
	}

	public static final String formatDateTime(Object date) {
		return format(date, DATE_PATTERN_YYYY_MM_DDHHMMSS);
	}

	/**
	 * 格式化日期
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static final String format(Object date, String pattern) {
		if (date == null) {
			return null;
		}
		if (pattern == null) {
			return format(date);
		}
		return new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * 获取日期
	 * 
	 * @return
	 */
	public static final String getDate() {
		return format(new Date());
	}

	/**
	 * 获取日期时间
	 * 
	 * @return
	 */
	public static final String getDateTime() {
		return format(new Date(), DATE_PATTERN_YYYY_MM_DDHHMMSS);
	}

	/**
	 * 获取日期
	 * 
	 * @param pattern
	 * @return
	 */
	public static final String getDateTime(String pattern) {
		return format(new Date(), pattern);
	}

	/**
	 * 获取日期
	 *
	 * @return
	 */
	public static final Date getCurDate() {
		return new Date();
	}

	/**
	 * 日期计算
	 * 
	 * @param date
	 * @param field
	 * @param amount
	 * @return
	 */
	public static final Date addDate(Date date, int field, int amount) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(field, amount);
		return calendar.getTime();
	}

	/**
	 * 字符串转换为日期:不支持yyM[M]d[d]格式
	 * 
	 * @param date
	 * @return
	 */
	public static final Date stringToDate(String date) {
		if (date == null) {
			return null;
		}
		String separator = String.valueOf(date.charAt(4));
		String pattern = "yyyyMMdd";
		if (!separator.matches("\\d*")) {
			pattern = "yyyy" + separator + "MM" + separator + "dd";
			if (date.length() < 10) {
				pattern = "yyyy" + separator + "M" + separator + "d";
			}
		} else if (date.length() < 8) {
			pattern = "yyyyMd";
		}
		pattern += " HH:mm:ss.SSS";
		pattern = pattern.substring(0, Math.min(pattern.length(), date.length()));
		try {
			return new SimpleDateFormat(pattern).parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 字符串转换为日期:不支持yyM[M]d[d]格式
	 *
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static final Date stringToDate(String dateStr, String format) {
		if (dateStr == null) {
			return null;
		}
		Date date = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

		try {
			date = simpleDateFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}

	/**
	 * 间隔分钟数
	 *
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static final long getMinuteBetween(Date startDate, Date endDate) {
		return (endDate.getTime() - startDate.getTime()) / (1000 * 60);
	}

	/**
	 * 间隔小时数
	 *
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static final long getHourBetween(Date startDate, Date endDate) {
		return (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60);
	}

	/**
	 * 间隔天数
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static final long getDayBetween(Date startDate, Date endDate) {
		return (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24);
	}

	/**
	 * 间隔月
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static final Integer getMonthBetween(Date startDate, Date endDate) {
		if (startDate == null || endDate == null || !startDate.before(endDate)) {
			return null;
		}
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		int year1 = start.get(Calendar.YEAR);
		int year2 = end.get(Calendar.YEAR);
		int month1 = start.get(Calendar.MONTH);
		int month2 = end.get(Calendar.MONTH);
		int n = (year2 - year1) * 12;
		n = n + month2 - month1;
		return n;
	}

	/**
	 * 间隔月，多一天就多算一个月
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static final Integer getMonthBetweenWithDay(Date startDate, Date endDate) {
		if (startDate == null || endDate == null || !startDate.before(endDate)) {
			return null;
		}
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		int year1 = start.get(Calendar.YEAR);
		int year2 = end.get(Calendar.YEAR);
		int month1 = start.get(Calendar.MONTH);
		int month2 = end.get(Calendar.MONTH);
		int n = (year2 - year1) * 12;
		n = n + month2 - month1;
		int day1 = start.get(Calendar.DAY_OF_MONTH);
		int day2 = end.get(Calendar.DAY_OF_MONTH);
		if (day1 <= day2) {
			n++;
		}
		return n;
	}

	/**
	 * 当天最后时间
	 * 
	 * @return
	 */
	public static final Date getTodayLastTime() {
		Date now = getCurDate();
		String nowStr = format(now);
		String lastTimeStr = nowStr + END_TIME;
		Date lastTime = stringToDate(lastTimeStr);
		return lastTime;
	}

	/**
	 * 当天开始时间
	 * 
	 * @return
	 */
	public static final Date getTodayFirstTime() {
		Date now = getCurDate();
		String nowStr = format(now);
		String lastTimeStr = nowStr + START_TIME;
		Date lastTime = stringToDate(lastTimeStr);
		return lastTime;
	}

	/**
	 * 上周开始时间
	 * 
	 * @return
	 */
	public static final Date getLastWeekStartTime() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.WEEK_OF_MONTH, -1);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		String dateStr = format(cal.getTime()) + START_TIME;
		return stringToDate(dateStr);
	}

	/**
	 * 上周结束时间
	 * 
	 * @return
	 */
	public static final Date getLastWeekEndTime() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		String dateStr = format(cal.getTime()) + END_TIME;
		return stringToDate(dateStr);
	}

	/**
	 * 上月开始时间
	 * 
	 * @return
	 */
	public static Date getLastMonthStartTime() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		String dateStr = format(cal.getTime()) + START_TIME;
		return stringToDate(dateStr);
	}

	/**
	 * 上月结束时间
	 * 
	 * @return
	 */
	public static final Date getLastMonthEndTime() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DATE, -1);
		String dateStr = format(cal.getTime()) + END_TIME;
		return stringToDate(dateStr);
	}

	/**
	 * 某年某月开始时间
	 * 
	 * @return
	 */
	public static String getMonthStartTime(String month) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(stringToDate(month, DATE_PATTERN_YYYY_MM));
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return format(cal.getTime()) + START_TIME;
	}

	/**
	 * 某年某月结束时间
	 * 
	 * @param month
	 *            2017-01
	 * @return
	 */
	public static final String getMonthEndTime(String month) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(stringToDate(month, DATE_PATTERN_YYYY_MM));
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DATE, -1);
		return format(cal.getTime()) + END_TIME;
	}

	/**
	 * 获取时间的指定格式字符串
	 *
	 * @param date
	 * @param format
	 *            时间格式
	 * @return
	 */
	public static String formatDate(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 获取当前时间戳的string形式（当前时间戳只精确到秒）
	 *
	 * @return
	 */
	public static String getNowTimeStampStr() {
		long time = getCurDate().getTime();
		return String.valueOf(time / 1000);
	}
}
