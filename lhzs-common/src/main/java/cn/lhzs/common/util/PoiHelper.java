package cn.lhzs.common.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by IBA-EDV on 2017/5/12.
 */
public class PoiHelper {
    public static List<?> readExcel(String fileName, InputStream inputStream, String type) {
        return null;
    }

    public static Object getFieldMethod(Object pojo, String filed) throws Exception {
        StringBuilder builder = new StringBuilder("get");
        builder.append(filed.substring(0, 1).toUpperCase());
        builder.append(filed.substring(1));
        Method method = pojo.getClass().getMethod(builder.toString());
        return method.invoke(pojo);
    }

    public static <T> void setFieldMethod(Object pojo, String filed, Class<T> valType, T val) throws Exception {
        StringBuilder builder = new StringBuilder("set");
        builder.append(filed.substring(0, 1).toUpperCase());
        builder.append(filed.substring(1));
        Method method = pojo.getClass().getMethod(builder.toString(), valType);
        method.invoke(pojo, val);
    }

    public static Sheet initSheet(InputStream inputStream, String fileName) throws IOException {
        Workbook workbook = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        if (".xls".equals(fileType.trim().toLowerCase())) {
            workbook = new HSSFWorkbook(inputStream);// 创建 Excel 2003 工作簿对象
        } else if (".xlsx".equals(fileType.trim().toLowerCase())) {
            workbook = new XSSFWorkbook(inputStream);//创建 Excel 2007 工作簿对象
        }

        return workbook.getSheetAt(0);
    }
}
