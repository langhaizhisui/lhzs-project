package cn.lhzs.common.third.kafka;

import java.util.HashMap;
import java.util.Map;

/**
 * Kafka Topic
 */
public enum KafkaTopicEnum {

    REPORT_BROWSE_HISTORY_TOPIC("report_browse_history", "日志记录browseHistory");



    KafkaTopicEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private static Map<String, String> map = new HashMap<>();

    static {
        if (map == null) {
            map = new HashMap<>();
        }
        for (KafkaTopicEnum e : KafkaTopicEnum.values()) {
            map.put(e.getCode(), e.getDesc());
        }
    }
    private String code;

    private String desc;

    public String getCode() {
//        String testcode = code + (isTestEnvironment() ? "_test":"");
        String testcode = code;
        return testcode;
    }

    public static String[] getCodes() {
        return map.keySet().toArray(new String[0]);
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static String getName(String index) {
        String name = map.get(index);
        return name == null ? "" : name;
    }

}
