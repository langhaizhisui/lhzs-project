package cn.lhzs.common.constant;

/**
 * Created by ZHX on 2018/1/7.
 */
public enum ShopEnum {

    SITE_TMSC("1","天猫商城"),
    SITE_JDSC("2","京东商城"),
    SITE_SNYG("3","苏宁易购"),
    SITE_YHD("4","一号店"),
    SITE_DDW("5","当当网"),
    SITE_GMZX("6","国美在线"),
    SITE_TBD("7","淘宝店"),

    TYPE_QJD("G","旗舰店"),
    TYPE_ZYD("W","自营店"),
    TYPE_ZMD("T","专卖店/专营店");

    ShopEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ShopEnum get(String code){
        ShopEnum[] articleCatalogEnums = values();
        for (int i = 0; i < articleCatalogEnums.length; i++) {
            ShopEnum articleCatalogEnum = articleCatalogEnums[i];
            if(articleCatalogEnum.getCode().equals(code)){
                return articleCatalogEnum;
            }
        }
        return null;
    }

    public static String getCode(String name){
        ShopEnum[] shopEnums = values();
        for (int i = 0; i < shopEnums.length; i++) {
            ShopEnum shopEnum = shopEnums[i];
            if(shopEnum.getName().equals(name)){
                return shopEnum.getCode().toString();
            }
        }
        return null;
    }
}
