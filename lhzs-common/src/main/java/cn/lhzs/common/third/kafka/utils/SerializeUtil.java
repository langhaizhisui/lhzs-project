package cn.lhzs.common.third.kafka.utils;

import org.apache.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeUtil {

	private static final transient Logger logger = Logger.getLogger(SerializeUtil.class);
	/**
	 * 序列化
	 * @param object
	 * @return
	 */
	public static byte[] serialize(Object object) {
		byte[] bytes = null ;
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			bytes = baos.toByteArray();
		} catch (Exception e) {
			logger.error(e,e);
		}finally {
			try {
				oos.close();
				baos.close();
			} catch (IOException e) {
				logger.error(e,e);
			}

		}
		return bytes;
	}

	/**
	 * 反序列化
	 * @param bytes
	 * @return
	 */
	public static Object deserialize(byte[] bytes){
		Object obj = null;
		ByteArrayInputStream bais = null;
		ObjectInputStream ois = null ;
		try{
			bais = new ByteArrayInputStream(bytes) ;
			ois = new ObjectInputStream(bais);
			obj = ois.readObject() ;
		}catch (Exception e) {
			logger.error(e,e);
		}finally {
			try {
				bais.close();
				ois.close();
			} catch (IOException e) {
				logger.error(e,e);
			}
		}
		return obj;
	}

}
