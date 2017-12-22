package cn.lhzs.common.exception;

/**
 * 微信接口返回错误信息或异常
 * @author ZHX
 */
public class WechatException extends BaseException {

	public WechatException() {
	}

	public WechatException(String message) {
		super(message);
	}

	public WechatException(String message, Exception e) {
		super(message, e);
	}

}
