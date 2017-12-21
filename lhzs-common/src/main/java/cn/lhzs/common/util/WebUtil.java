package cn.lhzs.common.util;

import java.io.BufferedReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import cn.lhzs.common.constant.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.WebUtils;

/**
 * Web层辅助类
 * @author ZHX
 */
public final class WebUtil {

	private static Logger logger = LoggerFactory.getLogger(WebUtil.class);

	private WebUtil() {
	}

	/**
	 * 获取指定Cookie的值
	 * 
	 * @param request 请求对象
	 * @param cookieName cookie名字
	 * @param defaultValue 缺省值
	 * @return
	 */
	public static final String getCookieValue(HttpServletRequest request, String cookieName, String defaultValue) {
		Cookie cookie = WebUtils.getCookie(request, cookieName);
		if (cookie == null) {
			return defaultValue;
		}
		return cookie.getValue();
	}

	/** 保存当前用户 */
	public static final void saveCurrentUser(Object user) {
		setSession(Constants.CURRENT_USER, user);
	}

	/** 保存当前用户 */
	public static final void saveCurrentUser(HttpServletRequest request, Object user) {
		setSession(request, Constants.CURRENT_USER, user);
	}

	/**
	 * 获取当前用户Id
	 */
	public static final Long getCurrentUser() {
		try {
			Subject currentUser = SecurityUtils.getSubject();
			if (null != currentUser) {
				Session session = currentUser.getSession();
				if (null != session) {
					return (Long) session.getAttribute(Constants.CURRENT_USER);
				}

			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
		return null;
	}

	/** 获取当前用户 */
	public static final Object getCurrentUser(HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			if (null != session) {
				return session.getAttribute(Constants.CURRENT_USER);
			}
		} catch (InvalidSessionException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 将一些数据放到ShiroSession中,以便于其它地方使用
	 *
	 * 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
	 */
	public static final void setSession(Object key, Object value) {
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			Session session = currentUser.getSession();
			if (null != session) {
				session.setAttribute(key, value);
			}
		}
	}

	/**
	 * 将一些数据放到ShiroSession中,以便于其它地方使用
	 *
	 * 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
	 */
	public static final void setSession(HttpServletRequest request, String key, Object value) {
		HttpSession session = request.getSession();
		if (null != session) {
			session.setAttribute(key, value);
		}
	}

	/** 移除当前用户 */
	public static final void removeCurrentUser(HttpServletRequest request) {
		request.getSession().removeAttribute(Constants.CURRENT_USER);
	}

	/**
	 * 获得国际化信息
	 *
	 * @param key
	 *            键
	 * @param request
	 * @return
	 */
	public static final String getApplicationResource(String key, HttpServletRequest request) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources", request.getLocale());
		return resourceBundle.getString(key);
	}

	/**
	 * 获得参数Map
	 *
	 * @param request
	 * @return
	 */
	public static final Map<String, Object> getParameterMap(HttpServletRequest request) {
		return WebUtils.getParametersStartingWith(request, null);
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> getParameter(HttpServletRequest request) {
		String str, wholeStr = "";
		try {
			BufferedReader br = request.getReader();
			while ((str = br.readLine()) != null) {
				wholeStr += str;
			}
			if (StringUtils.isNotBlank(wholeStr)) {
				return JSON.parseObject(wholeStr, Map.class);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return getParameterMap(request);
	}

	/** 获取客户端IP */
	public static final String getHost(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip != null && ip.indexOf(",") > 0) {
			// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
			ip = ip.substring(0, ip.indexOf(","));
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
			InetAddress inet = null;
			try { // 根据网卡取本机配置的IP
				inet = InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				logger.error(e.getMessage(), e);
			}
			ip = inet.getHostAddress();
		}
		return ip;
	}
}