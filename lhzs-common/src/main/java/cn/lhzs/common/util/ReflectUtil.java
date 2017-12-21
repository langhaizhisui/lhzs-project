package cn.lhzs.common.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射工具类
 */
public class ReflectUtil {

	/**
	 * 反射调用指定构造方法创建对象
	 *
	 * @param clazz
	 *            对象类型
	 * @param argTypes
	 *            参数类型
	 * @param args
	 *            构造参数
	 * @return 返回构造后的对象
	 * @throws Exception
	 */
	public static <T> T invokeConstructor(Class<T> clazz, Class<?>[] argTypes, Object[] args) throws Exception {
		Constructor<T> constructor = clazz.getConstructor(argTypes);
		return constructor.newInstance(args);
	}

	/**
	 * 反射调用指定对象属性的getter方法
	 *
	 * @param <T>
	 *            泛型
	 * @param target
	 *            指定对象
	 * @param fieldName
	 *            属性名
	 * @return 返回调用后的值
	 * @throws Exception
	 */
	public static <T> Object invokeGetter(T target, String fieldName) throws Exception {
		// 如果属性名为xxx，则方法名为getXxx
		String methodName = "get" + StringUtil.firstCharUpperCase(fieldName);
		Method method = target.getClass().getMethod(methodName);
		return method.invoke(target);
	}

	/**
	 * 反射调用指定对象属性的setter方法
	 *
	 * @param <T>
	 *            泛型
	 * @param target
	 *            指定对象
	 * @param fieldName
	 *            属性名
	 * @param args
	 *            参数列表
	 * @throws Exception
	 */
	public static <T> void invokeSetter(T target, String fieldName, Object args) throws Exception {
		// 如果属性名为xxx，则方法名为setXxx
		String methodName = "set" + StringUtil.firstCharUpperCase(fieldName);
		Class<?> clazz = target.getClass();
		Field field = null;
		try {
			field = clazz.getDeclaredField(fieldName);
		} catch (NoSuchFieldException e) {
			field = clazz.getSuperclass().getDeclaredField(fieldName);
		}
		Method method = clazz.getMethod(methodName, field.getType());
		method.invoke(target, args);
	}

	// public static void main(String[] args) {
	// try {
	// Class<Employee> clazz = Employee.class;
	// Employee user = ReflectUtil.invokeConstructor(clazz,
	// new Class<?>[]{long.class, String.class, int.class, String.class,
	// double.class,boolean.class}, new Object[]{1001,
	// "Linux", 30, "123", 20.55,true});
	// System.out.println(user);
	// ReflectUtil.invokeSetter(user, "salery", 2055);
	// System.out.println(user);
	// Object ret = ReflectUtil.invokeGetter(user, "salery");
	// System.out.println(ret);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

}
