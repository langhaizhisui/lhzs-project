package cn.lhzs.common.third.kafka.utils;

import java.io.Serializable;

/**
 *
* @ClassName:MessageBean.java
* @Description:消息发送统一载体
* @author:  ZHX
 */
public class MessageBean implements Serializable {

	private static final long serialVersionUID = 7274480556247623532L;

	private String id;
	
	private String name;
	
	private Object content;
	
	private String datetimes;
	
	public String getId() {
	
		return id;
	}

	public void setId(String id) {
	
		this.id = id;
	}

	public String getName() {
	
		return name;
	}

	public void setName(String name) {
	
		this.name = name;
	}

	public Object getContent() {
	
		return content;
	}

	public void setContent(Object content) {
	
		this.content = content;
	}

	public String getDatetimes() {
	
		return datetimes;
	}

	public void setDatetimes(String datetimes) {
	
		this.datetimes = datetimes;
	}

    public MessageBean() {
    }

    public MessageBean(Object content) {
        this.content = content;
    }
}
