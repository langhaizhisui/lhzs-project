package cn.lhzs.common.aop.log;

/**
 * @descp 日志操作枚举
 * @author ZHX
 */
public enum OpEnum {
    SELECT(1, "查询"),
    ADD(2, "新增"),
    EDIT(3, "修改"),
    DEL(4, "删除"),
    EXPORT(5, "导出"),
    LOGIN(6, "登录"),
    UPLOAD(7,"上传"),
    LOGIN_OUT(8, "登出");

    private int code;
    private String descp;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }

    OpEnum(int code, String descp){
        this.code = code;
        this.descp = descp;
    }

    public static  String getCnDescp(int code){
        for ( OpEnum op: values()) {
            if(op.getCode() == code)
                return op.getDescp();
        }
        return "未知";
    }

}
