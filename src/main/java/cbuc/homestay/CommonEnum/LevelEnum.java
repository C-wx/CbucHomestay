package cbuc.homestay.CommonEnum;

/**
 * @Explain:
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/1/2
 */
public enum LevelEnum {
    /**
     * @Explain
     * GOLD：    金牌商家
     * SLIVER：  银牌商家
     * COPPER：  铜牌商家
     * NORMAL：  普通商家
     */
    GOLD("GOLD"), SLIVER("SLIVER"), COPPER("COPPER"), NORMAL("NORMAL");
    private String value;
    LevelEnum(String value) {this.value = value;}
    public String getValue() {return value;}
    public void setValue(String value) {this.value = value;}
}
