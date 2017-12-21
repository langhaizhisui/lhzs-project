package cn.lhzs.common.result;

/**
 * Created by IBA-EDV on 2017/9/15.
 */
public class ResponseResultGenerator {

    /**
     * 生成请求成功结果
     * @return 返回响应对象
     */
    public static ResponseResult generatorSuccessResult() {
        return getResponseResult(ResponseCode.OK);
    }

    /**
     * 生成携带数据的成功结果
     * @return 返回响应对象
     */
    public static ResponseResult generatorSuccessResult(Object data) {
        return getResponseResult(ResponseCode.OK).setData(data);
    }

    /**
     * 生成请求失败结果
     * @return 返回响应对象
     */
    public static ResponseResult generatorFailResult() {
        return getResponseResult(ResponseCode.FAIL);
    }

    /**
     * 生成自定义信息的失败结果
     * @return 返回响应对象
     */
    public static ResponseResult generatorFailResult(String message) {
        ResponseCode.FAIL.setDescp(message);
        return getResponseResult(ResponseCode.FAIL);
    }

    /**
     * 生成自定义对象的失败结果
     * @return 返回响应对象
     */
    public static ResponseResult generatorFailResult(ResponseCode responseCode) {
        return getResponseResult(responseCode);
    }

    private static ResponseResult getResponseResult(ResponseCode responseCode) {
        return new ResponseResult()
                .setCode(responseCode.getCode())
                .setMsg(responseCode.getDescp());
    }

    public static ResponseResult generatorLoginResult() {
        return getResponseResult(ResponseCode.LOGIN);
    }

    public static ResponseResult generatorUnLoginResult() {
        return getResponseResult(ResponseCode.UNLOGIN);
    }
}
