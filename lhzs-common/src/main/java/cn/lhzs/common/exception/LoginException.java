package cn.lhzs.common.exception;

/**
 * 登录异常
 * @author ZHX
 */
public class LoginException extends BaseException {
	public LoginException() {
	}

	public LoginException(String message) {
		super(message);
	}

	public LoginException(String message, Exception e) {
		super(message, e);
	}

}
