package cn.lhzs.common.aop.log;

/**
 * @descp 操作成功或失败枚举
 * @author ZHX.
 */
public enum OpResultEnum {
    NO(0), YES(1);

    private  int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    OpResultEnum(int code){
        this.code = code;
    }
}
