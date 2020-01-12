package cbuc.homestay.CommonEnum;

/**
 * @Explain:
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/1/2
 */
public enum StatusEnum {
    /**
     * @Explain
     * B：   已预订
     * WA：  待审核
     * WC：  待收货
     * WP：  待付款
     * WR:   未读
     * SA：  审核通过
     * SC：  已收货
     * SP：  已付款
     * E：   生效中
     * D：   已失效/取消订单/已下架
     * FA：  审核不通过
     * FR：  空闲中
     * YR：  已读
     * R：   已评价
     */
    B("B")
    , WA("WA")
    , WC("WC")
    , WP("WP")
    , WR("WR")
    , SA("SA")
    , SC("SC")
    , SP("SP")
    , E("E")
    , D("D")
    , FA("FA")
    , FR("FR")
    , YR("YR")
    , R("R");
    private String value;
    StatusEnum(String value) {this.value = value;}
    public String getValue() {return value;}
    public void setValue(String value) {this.value = value;}
}
