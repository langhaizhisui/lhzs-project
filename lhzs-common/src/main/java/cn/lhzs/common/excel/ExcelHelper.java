package cn.lhzs.common.excel;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Excel工具抽象类
 */
public abstract class ExcelHelper {
	/**
	 * 对象序列化版本号名称
	 */
	public static final String UID = "serialVersionUID";

	public static void main(String[] args) {
		// List<Employee> employees = new ArrayList<Employee>();
		// employees.add(new Employee(1000, "Jones", 40, "Manager", 2975,true));
		// employees.add(new Employee(1001, "Blake", 40, "Manager",
		// 2850,false));
		// employees.add(new Employee(1002, "Clark", 40, "Manager", 2450,true));
		// employees.add(new Employee(1003, "Scott", 30, "Analyst",
		// 3000,false));
		// employees.add(new Employee(1004, "King", 50, "President",
		// 5000,true));
		// String[] titles = new String[]{"工号", "姓名", "年龄", "职称", "薪资（美元）",
		// "入职时间"};
		// String[] fieldNames = new String[]{"id", "name", "age", "job",
		// "salery", "addtime"};
		// try {
		// File file1 = new File("E:\\JXL2003.xls");
		// ExcelHelper eh1 = JxlExcelHelper.getInstance(file1);
		// eh1.writeExcel(Employee.class, employees);
		// eh1.writeExcel(Employee.class, employees, fieldNames, titles);
		// List<Employee> list1 = eh1.readExcel(Employee.class, fieldNames,
		// true);
		// System.out.println("-----------------JXL2003.xls-----------------");
		// for (Employee user : list1) {
		// System.out.println(user);
		// }
		// File file2 = new File("E:\\POI2003.xls");
		// eh2.writeExcel(Employee.class, employees);
		// eh2.writeExcel(Employee.class, employees, fieldNames, titles);
		// List<Employee> list2 = eh2.readExcel(Employee.class, fieldNames,
		// true);
		// System.out.println("-----------------POI2003.xls-----------------");
		// for (Employee user : list2) {
		// System.out.println(user);
		// }
		// File file3 = new File("E:\\POI2007.xlsx");
		// eh3.writeExcel(Employee.class, employees);
		// eh3.writeExcel(Employee.class, employees, fieldNames, titles);
		// List<Employee> list3 = eh3.readExcel(Employee.class, fieldNames,
		// true);
		// System.out.println("-----------------POI2007.xls-----------------");
		// for (Employee user : list3) {
		// System.out.println(user);
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		Object result = null;
		BigDecimal bigDecimal = new BigDecimal("20");
		System.out.println(result);
	}

	/**
	 * 将指定excel文件中的数据转换成数据列表
	 *
	 * @param clazz
	 *            数据类型
	 * @param sheetNo
	 *            工作表编号
	 * @param hasTitle
	 *            是否带有标题
	 * @return 返回转换后的数据列表
	 * @throws Exception
	 */
	public <T> List<T> readExcel(Class<T> clazz, int sheetNo, boolean hasTitle) throws Exception {
		Field[] fields = clazz.getDeclaredFields();
		String[] fieldNames = new String[fields.length];
		for (int i = 0; i < fields.length; i++) {
			String fieldName = fields[i].getName();
			fieldNames[i] = fieldName;
		}
		return readExcel(clazz, fieldNames, sheetNo, hasTitle);
	}

	/**
	 * 将指定excel文件中的数据转换成数据列表
	 *
	 * @param clazz
	 *            数据类型
	 * @param fieldNames
	 *            属性列表
	 * @param hasTitle
	 *            是否带有标题
	 * @return 返回转换后的数据列表
	 * @throws Exception
	 */
	public <T> List<T> readExcel(Class<T> clazz, String[] fieldNames, boolean hasTitle) throws Exception {
		return readExcel(clazz, fieldNames, 0, hasTitle);
	}

	/**
	 * 抽象方法：将指定excel文件中的数据转换成数据列表，由子类实现
	 *
	 * @param clazz
	 *            数据类型
	 * @param fieldNames
	 *            属性列表
	 * @param sheetNo
	 *            工作表编号
	 * @param hasTitle
	 *            是否带有标题
	 * @return 返回转换后的数据列表
	 * @throws Exception
	 */
	public abstract <T> List<T> readExcel(Class<T> clazz, String[] fieldNames, int sheetNo, boolean hasTitle)
			throws Exception;

	/**
	 * 指定类型为统计项 写入数据到指定excel文件中
	 *
	 * @param clazz
	 * @param dataModel
	 * @param <T>
	 * @throws Exception
	 */
	public <T> void writeStatisticsExcel(Class<T> clazz, T dataModel) throws Exception {
		Field[] fields = clazz.getDeclaredFields();
		String[] fieldNames = new String[fields.length];
		for (int i = 0; i < fields.length; i++) {
			String fieldName = fields[i].getName();
			fieldNames[i] = fieldName;
		}
		writeStatisticsExcel(clazz, dataModel, fieldNames, fieldNames, true, "");
	}

	public abstract <T> void writeStatisticsExcel(Class<T> clazz, T dataModel, String[] fieldNames, String[] titles,
			boolean isDelete, String sheetName) throws Exception;

	/**
	 * 写入数据到指定excel文件中
	 *
	 * @param clazz
	 *            数据类型
	 * @param dataModels
	 *            数据列表
	 * @throws Exception
	 */
	public <T> void writeExcel(Class<T> clazz, List<T> dataModels) throws Exception {
		Field[] fields = clazz.getDeclaredFields();
		String[] fieldNames = new String[fields.length];
		for (int i = 0; i < fields.length; i++) {
			String fieldName = fields[i].getName();
			fieldNames[i] = fieldName;
		}
		writeExcel(clazz, dataModels, fieldNames, fieldNames, true, "");
	}

	/**
	 * 写入数据到指定excel文件中
	 *
	 * @param clazz
	 *            数据类型
	 * @param dataModels
	 *            数据列表
	 * @param fieldNames
	 *            属性列表
	 * @throws Exception
	 */
	public <T> void writeExcel(Class<T> clazz, List<T> dataModels, String[] fieldNames) throws Exception {
		writeExcel(clazz, dataModels, fieldNames, fieldNames, true, "");
	}

	/**
	 * 抽象方法：写入数据到指定excel文件中，由子类实现
	 *
	 * @param clazz
	 *            数据类型
	 * @param dataModels
	 *            数据列表
	 * @param fieldNames
	 *            属性列表
	 * @param titles
	 *            标题列表
	 * @throws Exception
	 */
	public abstract <T> void writeExcel(Class<T> clazz, List<T> dataModels, String[] fieldNames, String[] titles,
			boolean isDelete, String sheetName) throws Exception;

	/**
	 * 写入数据到指定excel文件中
	 *
	 * @param clazz
	 *            数据类型
	 * @param dataModels
	 *            数据列表
	 * @param fieldNames
	 *            属性列表
	 * @param outputStream
	 *            Excel文件输出流
	 * @throws Exception
	 */
	public <T> void writeExcel(Class<T> clazz, List<T> dataModels, String[] fieldNames, String[] titleNames, OutputStream outputStream) throws Exception {

		writeExcel(clazz, dataModels, fieldNames, titleNames, true, "", outputStream);
	}

	/**
	 * 抽象方法：写入数据到指定excel文件中，由子类实现
	 *
	 * @param clazz
	 *            数据类型
	 * @param dataModels
	 *            数据列表
	 * @param fieldNames
	 *            属性列表
	 * @param titles
	 *            标题列表
	 * @param outputStream
	 *            Excel文件输出流
	 * @throws Exception
	 */
	public abstract <T> void writeExcel(Class<T> clazz, List<T> dataModels, String[] fieldNames, String[] titles,
										boolean isDelete, String sheetName, OutputStream outputStream) throws Exception;

	/**
	 * 判断属性是否为日期类型
	 *
	 * @param clazz
	 *            数据类型
	 * @param fieldName
	 *            属性名
	 * @return 如果为日期类型返回true，否则返回false
	 */
	protected <T> boolean isDateType(Class<T> clazz, String fieldName) {
		boolean flag = false;
		try {
			Field field = clazz.getDeclaredField(fieldName);
			Object typeObj = field.getType().newInstance();
			flag = typeObj instanceof Date;
		} catch (Exception e) {
			// 把异常吞掉直接返回false
		}
		return flag;
	}

	/**
	 * 根据类型将指定参数转换成对应的类型
	 *
	 * @param value
	 *            指定参数
	 * @param type
	 *            指定类型
	 * @return 返回类型转换后的对象
	 */
	protected <T> Object parseValueWithType(String value, Class<?> type) {
		Object result = null;
		try { // 根据属性的类型将内容转换成对应的类型
			if (Boolean.TYPE == type || Boolean.class == type) {
				result = Boolean.parseBoolean(value);
			} else if (Byte.TYPE == type || Byte.class == type) {
				result = Byte.parseByte(value);
			} else if (Short.TYPE == type || Short.class == type) {
				result = Short.parseShort(value);
			} else if (Integer.TYPE == type || Integer.class == type) {
				result = Integer.parseInt(value);
			} else if (Long.TYPE == type || Long.class == type) {
				result = Long.parseLong(value);
			} else if (Float.TYPE == type || Float.class == type) {
				result = Float.parseFloat(value);
			} else if (Double.TYPE == type || Double.class == type) {
				result = Double.parseDouble(value);
			} else if (BigDecimal.class == type) {
				result = new BigDecimal(value);
			} else {
				result = (Object) value;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 把异常吞掉直接返回null
		}
		return result;
	}

}
