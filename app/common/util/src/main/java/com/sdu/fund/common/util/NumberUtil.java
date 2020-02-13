package com.sdu.fund.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * @program: fundproduct
 * @description:
 * @author: anonymous
 * @create: 2020/2/8 15:57
 **/
public class NumberUtil {

    /**
     *
     * @Description：7*0.01*100/100 保留四位小数,截取4位
     * @Date：2019/1/17 下午4:49
     * @Author：ChengJian
     * @UpdateRemark:
     * @Version:1.0
     *
     */
    public static double getDouble_toNum_no4no5(double number,int num){
        // setScale(1)表示保留一位小数，默认用四舍五入方式
        // setScale(1,BigDecimal.ROUND_DOWN)直接删除多余的小数位，如2.35会变成2.3
        // num 代表保留几位小数
        return new BigDecimal(number).setScale(num, BigDecimal.ROUND_DOWN).doubleValue();
    }

    /**
     *
     * @Description：setScale(1,BigDecimal.ROUND_UP)进位处理，不管后为是1还是6，都进1
     * @Date：2019/1/17 下午4:59
     * @Author：ChengJian
     * @UpdateRemark:
     * @Version:1.0
     *
     */
    public static double getDouble_toNum_isincr(double number,int num){
        return new BigDecimal(number).setScale(num, BigDecimal.ROUND_UP).doubleValue();
    }

    /**
     *
     * @Description：正常四舍五入
     * @Date：2019/1/28 下午5:37
     * @Author：ChengJian
     * @UpdateRemark:
     * @Version:1.0
     *
     */
    public static double getDouble_toNum_is45(double number,int num){
        return new BigDecimal(number).setScale(num, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    /**
     *
     * @Description：获取两个整数的double值 isNpoint：保留很多小数点
     * @Date：2019/1/24 上午9:54
     * @Author：ChengJian
     * @UpdateRemark:
     * @Version:1.0
     *
     */
    public static double getDouble_division_isNpoint(int num1,int num2){
        double result = num1*0.01*100/num2;
        return result;
    }

    /**
     *
     * @Description：两外一种方法，和上面方式不同，截取两位小数
     * @Date：2019/1/24 上午10:03
     * @Author：ChengJian
     * @UpdateRemark:
     * @Version:1.0
     *
     */
    public static double getDouble_to2_no4no5(double num){
        DecimalFormat df = new DecimalFormat("#0.##");
        df.setRoundingMode(RoundingMode.FLOOR);
        return Double.parseDouble(df.format(num));
    }


    /**
     *
     * @Description：//四舍五入转换成整数
     * @Date：2019/1/24 上午10:18
     * @Author：ChengJian
     * @UpdateRemark:
     * @Version:1.0
     *
     */
    public static int getInteger(double num){
        DecimalFormat df2 = new DecimalFormat("######0");
        return Integer.parseInt(df2.format(num));
    }

    /**
     *
     * @Description：提供精确的加法运算
     * @Date：2019/1/28 上午10:38
     * @Author：ChengJian
     * @UpdateRemark:
     * @Version:1.0
     *
     */
    public static double add_2_double(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     *
     * @Description：提供精确的减法运算
     * @Date：2019/1/28 上午10:39
     * @Author：ChengJian
     * @UpdateRemark:
     * @Version:1.0
     *
     */
    public static double substract_2_double(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     *
     * @Description：提供精确的乘法运算
     * @Date：2019/1/28 上午10:40
     * @Author：ChengJian
     * @UpdateRemark:
     * @Version:1.0
     *
     */
    public static double multiply(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     *
     * @Description：提供（相对）精确的除法运算,当发生除不尽的情况时,精确到小数点以后10位,以后的数字四舍五入.
     * @Date：2019/1/28 上午10:40
     * @Author：ChengJian
     * @UpdateRemark:
     * @Version:1.0
     *
     */
    public static double divide(double v1, double v2) {
        return divide(v1, v2, 10);
    }

    /**
     *
     * @Description：提供（相对）精确的除法运算.当发生除不尽的情况时,由scale参数指 定精度,以后的数字四舍五入
     * @Date：2019/1/28 上午10:41
     * @Author：ChengJian
     * @UpdateRemark:
     * @Version:1.0
     *
     */

    public static double divide(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }

        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static void main(String[] args) {
        System.out.println(getDouble_toNum_is45(2.1323,0));
    }

}
