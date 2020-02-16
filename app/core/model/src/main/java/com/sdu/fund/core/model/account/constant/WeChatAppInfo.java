package com.wycs.c.server.constant;

import com.wycs.c.server.utils.EnvironmentUtil;
import javafx.util.Pair;

public class WeChatAppInfo {
    /**
     * 微信支付分配的公众账号ID（企业号corpid即为此appId）
     *
     */

    //线上C端小程序APPID
    public static final String C_APPID = "wxefe09473754782f3";
    public static final String C_APPSECRET = "34fa555fe9e859444c15046a8bb2ac38";
    //todo 修改为线上账号
    public static final String C_DEV_APPID = "wxdae600e884509ee9";
    public static final String C_DEV_APPSECRET = "f4197ea18ac83337154c0c6b990bada7";


    //线上商家端小程序APPID
    public static final String POI_APPID = "wx2f8aef9abfd2b22a";
    public static final String POI_APPSECRET = "9641c5a6e094688f0e355c9de526a7c1";
    //todo 修改为线上账号
    public static final String POI_DEV_APPID = "wxdae600e884509ee9";
    public static final String POI_DEV_APPSECRET = "f4197ea18ac83337154c0c6b990bada7";

    public static final String OFFICIAL_WECHAT_ACCOUNT_ID = "wxa083ae269928b251";



//    public static String getPoiAppid(){
//        if (EnvironmentUtil.isDev()){
//            return
//        }
//        return POI_APPID;
//    }
//
//    public static String getPoiAppsecret(){
//        return POI_APPSECRET;
//    }

    /**
     * 微信支付分配的商户号
     */
    public static final String MCHID = "1547324741";

    /* 连接超时时间，单位是毫秒 */
    public static int CONNECT_TIMEOUT_MS = 5 * 1000;
    /* 读超时时间，单位是毫秒 */
    public static int READ_TIMEOUT_MS = 5 * 1000;
    /* 调用WXPay接口时，与WXPay通信失败 */
    public static final String COMMUNICATION_FAILED = "COMMUNICATION_FAILED";
    /* WXPay统一下单失败 */
    public static final String UNIFIED_ORDER_FAILED = "UNIFIED_ORDER_FAILED";

    /* geo-hash范围精度 */
    public static final int GEOHASH_CHARACTER_PRECISION = 5;
    public static final int GEOHASH_BIT_PRECISION = 25;
    /* 查找合作商时，若查询个数小于GEOHASH_SEARCH_MINIMUM_NUMBER，则扩大范围查找周围8个格的合作商 */
    public static final int GEOHASH_SEARCH_MINIMUM_NUMBER =  50;


    /**
     * 微信支付服务商商户号相关信息
     */

    public static final String SPMCHID = "1561560431";
}
