package cn.lhzs.common.constant;

/**
 * Created by ZHX on 2017/9/3 0003.
 */
public enum ArticleTypeEnum {

    FURNITURE_DECORATION(1,"家居装修"),
    CHOOSE_GUIDE(2,"选购导购"),
    TOURIST_ATTRACTIONS(3,"旅游景点"),
    ENTERTAINING_ANECDOTES(4,"娱乐趣闻"),
    HEALTH_REGIMEN(5,"健康养生"),
    DAILY_LIFE(6,"日常生活"),
    BABYMUMS(7,"母婴育儿"),
    DIGITAL_TECHNOLOGY(8,"数码科技"),
    GAME_INFORMATION(9,"游戏资讯"),
    CLOTHING_SHOES(10,"服装鞋包"),
    BEAUTY_SKIN(11,"美妆护肤"),
    URBAN_HOUSE(12,"城市房产"),
    FINANCIAL_SERVICES(13,"金融理财"),
    TRIP_BY_AUTO(14,"汽车出行"),
    BRAND_HOT(15,"品牌热点"),
    LIFE_DESIGNER(16,"人生指南"),
    FOOD_RECIPES(17,"美食菜谱"),
    LUXURY_FASHION(18,"奢侈时尚");

    ArticleTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    private Integer code;
    private String name;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ArticleTypeEnum get(Integer code){
        ArticleTypeEnum[] articleCatalogEnums = values();
        for (int i = 0; i < articleCatalogEnums.length; i++) {
            ArticleTypeEnum articleCatalogEnum = articleCatalogEnums[i];
            if(articleCatalogEnum.getCode().equals(code)){
                return articleCatalogEnum;
            }
        }
        return null;
    }

    public static String getTypeCode(String name){
        ArticleTypeEnum[] articleCatalogEnums = values();
        for (int i = 0; i < articleCatalogEnums.length; i++) {
            ArticleTypeEnum articleCatalogEnum = articleCatalogEnums[i];
            if(articleCatalogEnum.getName().equals(name)){
                return articleCatalogEnum.getCode().toString();
            }
        }
        return null;
    }
}
