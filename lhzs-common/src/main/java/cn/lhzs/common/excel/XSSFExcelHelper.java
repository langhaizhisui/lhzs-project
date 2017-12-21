package cn.lhzs.common.excel;

import cn.lhzs.common.util.DateUtil;
import cn.lhzs.common.util.ReflectUtil;
import cn.lhzs.common.util.StringUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 基于POI实现的Excel工具类
 */
public class XSSFExcelHelper extends ExcelHelper {

	private static XSSFExcelHelper instance = null; // 单例对象
	private static FormulaEvaluator evaluator = null; // 公式计算

	private File file; // 操作文件

	/**
	 * 私有化构造方法
	 *
	 * @param file
	 *            文件对象
	 */
	private XSSFExcelHelper(File file) {
		super();
		this.file = file;
	}

	/**
	 * 获取单例对象并进行初始化
	 *
	 * @param file
	 *            文件对象
	 * @return 返回初始化后的单例对象
	 */
	public static XSSFExcelHelper getInstance(File file) {
		if (instance == null) {
			// 当单例对象为null时进入同步代码块
			synchronized (XSSFExcelHelper.class) {
				// 再次判断单例对象是否为null，防止多线程访问时多次生成对象
				if (instance == null) {
					instance = new XSSFExcelHelper(file);
				}
			}
		} else {
			// 如果操作的文件对象不同，则重置文件对象
			if (!file.equals(instance.getFile())) {
				instance.setFile(file);
			}
		}
		return instance;
	}

	/**
	 * 获取单例对象并进行初始化
	 *
	 * @param filePath
	 *            文件路径
	 * @return 返回初始化后的单例对象
	 */
	public static XSSFExcelHelper getInstance(String filePath) {
		return getInstance(new File(filePath));
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	@Override
	public <T> List<T> readExcel(Class<T> clazz, String[] fieldNames, int sheetNo, boolean hasTitle) throws Exception {
		List<T> dataModels = null;
		// 获取excel工作簿
		XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
		evaluator = workbook.getCreationHelper().createFormulaEvaluator();
		XSSFSheet sheet = workbook.getSheetAt(sheetNo);
		int start = sheet.getFirstRowNum() + (hasTitle ? 1 : 0); // 如果有标题则从第二行开始
		int lastRowNun = sheet.getLastRowNum();
		if (lastRowNun > 0) {
			dataModels = new ArrayList<>(lastRowNun);
		}
		for (int i = start; i <= lastRowNun; i++) {
			XSSFRow row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			// 生成实例并通过反射调用setter方法
			T target = clazz.newInstance();
			for (int j = 0; j < fieldNames.length; j++) {
				String fieldName = fieldNames[j];
				if (fieldName == null || UID.equals(fieldName)) {
					continue; // 过滤serialVersionUID属性
				}
				// 获取excel单元格的内容
				XSSFCell cell = row.getCell(j);
				if (cell == null) {
					continue;
				}

				// 如果属性是日期类型则将内容转换成日期对象
				if (isDateType(clazz, fieldName)) {
					// 如果属性是日期类型则将内容转换成日期对象
					short format = cell.getCellStyle().getDataFormat();
					Date date = null;
					if (HSSFDateUtil.isCellDateFormatted(cell)) {
						date = cell.getDateCellValue();
					} else if (format == 14 || format == 31 || format == 57 || format == 58) {
						// 日期
						date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(cell.getNumericCellValue());
					} else if (format == 20 || format == 32) {
						// 时间
						date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(cell.getNumericCellValue());
					}
					ReflectUtil.invokeSetter(target, fieldName, date);
				} else {
					String content = getCellContent(cell);
					Field field = null;
					try {
						field = clazz.getDeclaredField(fieldName);
					} catch (NoSuchFieldException e) {
						// 如果没有则查找父类属性
						field = clazz.getSuperclass().getDeclaredField(fieldName);
					}
					ReflectUtil.invokeSetter(target, fieldName, parseValueWithType(content, field.getType()));
				}
			}
			dataModels.add(target);
		}
		return dataModels;
	}

	@Override
	public <T> void writeExcel(Class<T> clazz, List<T> dataModels, String[] fieldNames, String[] titles,
			boolean isDelete, String sheetName) throws Exception {
		XSSFWorkbook workbook = getWorkBook(clazz, dataModels, fieldNames, titles, isDelete, sheetName);

		// 将数据写到磁盘上
		FileOutputStream fos = new FileOutputStream(file);
		try {
			workbook.write(new FileOutputStream(file));
		} finally {
			if (fos != null) {
				fos.close(); // 不管是否有异常发生都关闭文件输出流
			}
		}
	}

	private <T> XSSFWorkbook getWorkBook(Class<T> clazz, List<T> dataModels, String[] fieldNames, String[] titles, boolean isDelete, String sheetName) throws Exception {
		// 检测文件是否存在，如果存在则修改文件，否则创建文件
		XSSFWorkbook workbook = getWorkBook(isDelete);
		// 根据当前工作表数量创建相应编号的工作表
		if (StringUtil.isBlank(sheetName)) {
			sheetName = DateUtil.format(new Date(), "yyyyMMddHHmmssSS");
		}
		XSSFSheet sheet = workbook.createSheet(sheetName);
		XSSFRow headRow = sheet.createRow(0);
		// 添加表格标题
		for (int i = 0; i < titles.length; i++) {
			XSSFCell cell = headRow.createCell(i);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(titles[i]);
			// 设置字体加粗
			XSSFCellStyle cellStyle = workbook.createCellStyle();
			XSSFFont font = workbook.createFont();
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			cellStyle.setFont(font);
			// 设置自动换行
			cellStyle.setWrapText(true);
			cell.setCellStyle(cellStyle);
			// 设置单元格宽度
			sheet.setColumnWidth(i, titles[i].length() * 1000);
		}
		// 添加表格内容
		for (int i = 0; i < dataModels.size(); i++) {
			T target = dataModels.get(i);
			XSSFRow row = sheet.createRow(i + 1);
			// 遍历属性列表
			for (int j = 0; j < fieldNames.length; j++) {
				// 通过反射获取属性的值域
				String fieldName = fieldNames[j];
				if (fieldName == null || UID.equals(fieldName)) {
					continue; // 过滤serialVersionUID属性
				}
				Object result = ReflectUtil.invokeGetter(target, fieldName);
				XSSFCell cell = row.createCell(j);
				cell.setCellValue(StringUtil.toString(result));
				// 如果是日期类型则进行格式化处理
				if (isDateType(clazz, fieldName)) {
					cell.setCellValue(DateUtil.format((Date) result));
				}
			}
		}
		return workbook;
	}

	@Override
	public <T> void writeExcel(Class<T> clazz, List<T> dataModels, String[] fieldNames, String[] titles, boolean isDelete, String sheetName, OutputStream outputStream) throws Exception {
        getWorkBook(clazz, dataModels, fieldNames, titles, isDelete, sheetName).write(outputStream);
	}

	@Override
	protected <T> Object parseValueWithType(String value, Class<?> type) {
		// 由于Excel2007的numeric类型只返回double型，所以对于类型为整型的属性，要提前对numeric字符串进行转换
		if (Byte.TYPE == type || Short.TYPE == type || Short.TYPE == type || Long.TYPE == type) {
			value = String.valueOf((long) Double.parseDouble(value));
		}
		return super.parseValueWithType(value, type);
	}

	/**
	 * 获取单元格的内容
	 *
	 * @param cell
	 *            单元格
	 * @return 返回单元格内容
	 */
	private String getCellContent(XSSFCell cell) {
		StringBuffer buffer = new StringBuffer();
		switch (cell.getCellType()) {
		case XSSFCell.CELL_TYPE_NUMERIC: // 数字
			buffer.append(cell.getNumericCellValue());
			break;
		case XSSFCell.CELL_TYPE_BOOLEAN: // 布尔

			break;
		case XSSFCell.CELL_TYPE_FORMULA: // 公式
			CellValue cellValue = evaluator.evaluate(cell);
			buffer.append(cellValue.getNumberValue());
			break;
		case XSSFCell.CELL_TYPE_STRING: // 字符串
			buffer.append(cell.getStringCellValue());
			break;
		case XSSFCell.CELL_TYPE_BLANK: // 空值
		case XSSFCell.CELL_TYPE_ERROR: // 故障

		default:
			break;
		}
		return buffer.toString();
	}

	private XSSFWorkbook getWorkBook(boolean isDelete) throws Exception {
		XSSFWorkbook workbook = null;
		if (file.exists()) {
			if (isDelete) {
				file.delete();
				workbook = new XSSFWorkbook();
			} else {
				FileInputStream fis = new FileInputStream(file);
				workbook = new XSSFWorkbook(fis);
			}
		} else {
			workbook = new XSSFWorkbook();
		}
		return workbook;
	}

	@Override
	public <T> void writeStatisticsExcel(Class<T> clazz, T dataModel, String[] fieldNames, String[] titles,
			boolean isDelete, String sheetName) throws Exception {

	}
}
