package cbuc.homestay.CommonEnum;

/**
 * @Explain: 商户等级枚举
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/1/2
 */
public enum LevelEnum {
    /**
     * 超级管理员
     */
    ADMIN("ADMIN"),
    /**
     * 金牌商家
     */
    GOLD("GOLD"),
    /**
     * 银牌商家
     */
    SLIVER("SLIVER"),
    /**
     * 铜牌商家
     */
    COPPER("COPPER"),
    /**
     * 普通商家
     */
    NORMAL("NORMAL"),
    /**
     * 商家
     */
    MERCHANT("MERCHANT"),
    /***
     * 用户
     */
    USER("USER");
    private String value;

    LevelEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
