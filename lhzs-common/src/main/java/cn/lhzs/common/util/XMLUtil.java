package cn.lhzs.common.util;

import cn.lhzs.common.constant.Constants;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.sax.SAXResult;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLUtil {
	
	private static final Logger logger = Logger.getLogger(XMLUtil.class);
	@SuppressWarnings("unchecked")
	public static Map<String,String> getMapFromXML(String xmlString) throws Exception {
    	Map<String, String> map = new HashMap<String, String>();
    	StringReader read = null;
    	try{
    		read = new StringReader(xmlString);
    		// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
    		InputSource source = new InputSource(read);
    		// 创建一个新的SAXBuilder
    		SAXBuilder sb = new SAXBuilder();
    		// 通过输入源构造一个Document
    		Document doc = (Document) sb.build(source);
    		Element root = doc.getRootElement();// 指向根节点
    		List<Element> es = root.getChildren();
    		if (es != null && es.size() != 0) {
    			for (Element element : es) {
    				map.put(element.getName(), element.getValue());
    			}
    		}
    	}catch (Exception ex){
    		throw new Exception("解析xml失败！" + ex.getMessage());
    		
    	}finally{
    		if ( null != read){
    			read.close();
    			read = null;
    		}
    	}
    	
    	return map;

    }
	
	@SuppressWarnings("unchecked")
	public static Map<String,String> getMapFromInputStreamXML(InputStream in,String encoding) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
    	//BufferedReader br = new BufferedReader(new InputStreamReader(in, encoding));
		
		InputStreamReader inr = null;
		BufferedReader br = null;
		try{
			inr = new InputStreamReader(in, encoding);
			br = new BufferedReader(inr);
			// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
			InputSource source = new InputSource(br);
			// 创建一个新的SAXBuilder
			SAXBuilder sb = new SAXBuilder();
			// 通过输入源构造一个Document
			Document doc = (Document) sb.build(source);
			Element root = doc.getRootElement();// 指向根节点
			List<Element> es = root.getChildren();
			if (es != null && es.size() != 0) {
				for (Element element : es) {
					map.put(element.getName(), element.getValue());
					logger.debug("name=" + element.getName() + ",value=" + element.getValue());
				}
			}
    	}catch (Exception ex){
    		throw new Exception("解析xml失败！" + ex.getMessage());
    	}finally{
    		
    		if ( null != br){
    			br.close();
    			br = null;
    		}
    		if ( null != inr){
    			inr.close();
    			inr = null;
    		}
    		
//    		if ( null != in){
//    			in.close();
//    			in = null;
//    		}
    	}
    	return map;

    }
	
	@SuppressWarnings("unchecked")
	public static JSONObject getJSONObjectFromXML(String xmlString) throws Exception {
    	JSONObject result = new JSONObject();
    	StringReader read = null;
    	try{
    		read = new StringReader(xmlString);
    		// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
    		InputSource source = new InputSource(read);
    		// 创建一个新的SAXBuilder
    		SAXBuilder sb = new SAXBuilder();
    		// 通过输入源构造一个Document
    		Document doc = (Document) sb.build(source);
    		Element root = doc.getRootElement();// 指向根节点
    		List<Element> children = root.getChildren();
    		if( children.size() > 0 ){
    			result.put(root.getName(), getChildrenElementAsJSONObject(children));
    		}else{
    			JSONObject json = new JSONObject();
    			json.put(root.getName(), root.getValue());
    			result.put(root.getName(), json);
    		}
    	}catch (Exception ex){
    		throw new Exception("解析xml失败！" + ex.getMessage());
    		
    	}finally{
    		if ( null != read){
    			read.close();
    			read = null;
    		}
    	}
    	
    	return result;

    }

	@SuppressWarnings("unchecked")
	private static JSONObject getChildrenElementAsJSONObject(List<Element> children){
		JSONObject json = new JSONObject();
		
		for( Element child : children ){
			if( child.getChildren().size() == 0 ){
				json.put(child.getName(), child.getValue());
				continue;
			}
			
			JSONObject obj = getChildrenElementAsJSONObject(child.getChildren());
			
			Object o = json.get(child.getName());
			
			if( o == null ){
				json.put(child.getName(), obj);
			}else if( o instanceof JSONArray){
				((JSONArray)o).add(obj);
			}else if( o instanceof JSONObject){
				JSONArray array = new JSONArray();
				array.add(o);
				array.add(obj);
				json.put(child.getName(), array);
			}
		}
		
		return json;
	}
	
	// ready for pt 测试 
	

	/**
	 * xml转成java对像
	 * @param xmlFile
	 * @param clazz
	 * @return
	 * @throws JAXBException
	 */
	public static <T> T xml2Obj(File xmlFile, Class<T> clazz) throws JAXBException{
		T obj = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Object o = jaxbUnmarshaller.unmarshal(xmlFile);
			obj = clazz.cast(o);
		} catch (JAXBException e) {
			throw new JAXBException("xml转换java对像失败！" + e.getMessage(),e);
		}
		return obj;
	}
	
	/**
	 * xml转成java对像
	 * @param xmlInputStream
	 * @param clazz
	 * @return
	 * @throws JAXBException
	 */
	public static <T> T xml2Obj(InputStream xmlInputStream, Class<T> clazz) throws JAXBException {
		T obj = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Object o = jaxbUnmarshaller.unmarshal(xmlInputStream);
			obj = clazz.cast(o);
		} catch (JAXBException e) {
			throw new JAXBException("xml转换java对像失败！" + e.getMessage(),e);
		}
		return obj;
	}
	
	
	/**
	 * java对像转成xml格式的
	 * @param obj
	 * @return
	 * @throws JAXBException
	 */
	public static OutputStream obj2Xml(Object obj,OutputStream os) throws JAXBException {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller(); 
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
            jaxbMarshaller.marshal(obj, os); 
		 
		} catch (JAXBException e) {
			throw new JAXBException("java转换xml失败！" + e.getMessage(),e);
		}
		return os;
	}
	
 
	
	/**
	 *  java对像转成xml格式的
	 * @param obj
	 * @param file
	 * @return
	 * @throws JAXBException
	 */
	public static File obj2Xml(Object obj,File file) throws JAXBException {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller(); 
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
            jaxbMarshaller.marshal(obj, file); 
		} catch (JAXBException e) {
			throw new JAXBException("java转换xml失败！" + e.getMessage(),e);
		}
		return file;
	}
	
	/**
	 * JavaBean转换为xml
	 * 
	 * @param obj
	 * @param encoding
	 * @return
	 */
	public static String convertToXml(Object obj, String encoding) {
		String result = null;
		try {
			JAXBContext context = JAXBContext.newInstance(obj.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);

			StringWriter writer = new StringWriter();
			marshaller.marshal(obj, writer);
			result = writer.toString();
		} catch (Exception e) {
            System.out.println(e);
		}

		return result;
	}
	
	/**
	 * xml转换为JavaBean
	 * 
	 * @param xml
	 * @param c
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T converyToJavaBean(String xml, Class<T> c) {
		T t = null;
		try {
			JAXBContext context = JAXBContext.newInstance(c);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			t = (T) unmarshaller.unmarshal(new StringReader(xml));
		} catch (Exception e) {
			System.out.println(e);
		}
		return t;
	}
	
	public static <T> String ojbectToXmlWithCDATA(Class<T> clazz, T obj,String[] cdataArrays,String charSet) throws Exception {  

        JAXBContext context = JAXBContext.newInstance(clazz);  
        // configure an OutputFormat to handle CDATA
        OutputFormat of = new OutputFormat();
        of.setCDataElements(cdataArrays);
        of.setPreserveSpace(true);
        of.setIndenting(true);
        // create the serializer
        ByteArrayOutputStream op = new ByteArrayOutputStream();  
        XMLSerializer serializer = new XMLSerializer(op, of);  
        SAXResult result = new SAXResult(serializer.asContentHandler());  
        Marshaller mar = context.createMarshaller(); 
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
        mar.marshal(obj, result);  

        return op.toString(StringUtil.isEmpty(charSet)? Constants.UTF8:charSet);
    }
}
