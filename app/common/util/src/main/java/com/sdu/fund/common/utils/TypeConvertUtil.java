package com.sdu.fund.common.utils;

/**
 * @program: fundproduct
 * @description:
 * @author: anonymous
 * @create: 2020/1/30 15:26
 **/
public class TypeConvertUtil {

    public static Double convert2Double(String str) {
        if (!isNumeric(str)){
            return null;
        }
        return Double.valueOf(str);
    }

    /*
     * @description 判断输入是否为数字（负数，小数都算）
     * @param [str]
     * @return boolean
     * @author anonymous
     * @date 2020/1/30
     */
    public static boolean isNumeric(String str) {
        if(str==null){
            return false;
        }
        int length = str.length();
        int start = 0;
        if (length < 1) {
            return false;
        }
        if (str.charAt(0) == '-') {
            // 负数
            start = 1;
        } else {
            start = 0;
        }
        for (int i = start; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i)) && str.charAt(i) != '.') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isNumeric("-12.30"));
        System.out.println(isNumeric("12.30"));
        System.out.println(isNumeric("1230"));
        System.out.println(isNumeric("-1230"));
        System.out.println(isNumeric("-123we0"));
    }
}
