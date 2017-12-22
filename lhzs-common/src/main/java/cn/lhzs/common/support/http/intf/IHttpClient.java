package cn.lhzs.common.support.http.intf;

import java.util.Map;


public interface IHttpClient {

	String get(String url, String encoding) throws Exception;
	
	String get(String url, Map<String, String> params, String encoding) throws Exception;
	
	String get(String url, Map<String, String> params, Map<String, String> headers, String encoding) throws Exception;
	
	String post(String url, String encoding) throws Exception;
	
	String post(String url, Map<String, String> params, String encoding) throws Exception;
	
	String post(String url, String params, String encoding) throws Exception;
	
	<T> String postXml(String url, T obj, String encoding) throws Exception;
	
	public String postJson(String url, String json, String encoding) throws Exception;
	
	public String postJson(String url, String json, Map<String, String> headers, String encoding) throws Exception;
	
	String send(String url, Map<String, String> map, String encoding) throws Exception;
	
}
