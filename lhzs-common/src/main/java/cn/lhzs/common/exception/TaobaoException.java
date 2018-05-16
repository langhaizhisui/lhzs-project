package cn.lhzs.common.exception;

/**
 * 淘宝接口返回错误信息或异常
 * @author ZHX
 */
public class TaobaoException extends BaseException {

	public TaobaoException() {
	}

	public TaobaoException(String message) {
		super(message);
	}

	public TaobaoException(String message, Exception e) {
		super(message, e);
	}

}
