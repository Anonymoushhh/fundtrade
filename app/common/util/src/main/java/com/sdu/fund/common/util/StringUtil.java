package com.sdu.fund.common.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @program: fundproduct
 * @description: 字符串操作工具类
 * @author: anonymous
 * @create: 2019-12-06 09:02
 **/
public class StringUtil {

    /*
     * @description 返回str中key后面第一个英文引号中的值
     * @param [str]
     * @return java.lang.String
     * @author anonymous
     * @date 2019/12/6
     */
    public static String findValueBetweenFirstQuotes(String str, String key) {
        int index = StringUtils.indexOf(str, key) + key.length();
        if(index-key.length()==-1){
            // 没找到
            return null;
        }
        int first = -1;
        int last = str.length();
        for (int i = index; i < str.length(); i++) {
            if (str.charAt(i) == '"' && first == -1) {
                // 第一个引号
                first = i;
            } else if (str.charAt(i) == '"' && first != -1) {
                // 第二个引号
                last = i;
                break;
            }
        }

        return str.substring(first + 1, last);
    }

    /*
     * @description 返回str中key后面第一个括号中的值，如果遇到"",说明没有值
     * @param [str]
     * @return java.lang.String
     * @author anonymous
     * @date 2019/12/6
     */
    public static String findValueBetweenFirstSquareBrackets(String str, String key) {
        int index = StringUtils.indexOf(str, key) + key.length();
        if(index-key.length()==-1){
            // 没找到
            return null;
        }
        int first = -1;
        int last = str.length();
        for (int i = index; i < str.length(); i++) {
            if (str.charAt(i) == '"' && first == -1) {
                // 没找到括号先遇到""，说明值为空
                return "[]";
            } else if (str.charAt(i) == '[' && first == -1) {
                // 第一个括号
                first = i;
            } else if (str.charAt(i) == ']' && first != -1) {
                // 第二个括号
                last = i;
                break;
            }
        }

        return str.substring(first, last + 1);
    }

    public static String[] splitByComma(String str) {
        // 为什么非要自己写？因为原方法会莫名其妙把最后一个逗号后面的空字符串去掉，造成数据丢失
        return str.split(",");
    }
        public static void main(String[] args) {
        String a = "/** * 测试数据 * @type {arry} *//*2019-12-11 18:13:35*/var ishb=false;" +
            "/*基金或股票信息*/var fS_name = \"西部利得量化成长混合\";var fS_code = \"000006\";/*原费率*/var fund_sourceRate=\"1.50\";" +
            "/*现费率*/var fund_Rate=\"0.15\";/*最小申购金额*/var fund_minsg=\"100\";/*基金持仓股票代码*/var stockCodes=[\"6006001\"," +
            "\"6000151\",\"6880081\",\"0029262\",\"6004661\",\"0027772\",\"6000311\",\"6003731\",\"6033691\"," +
            "\"6008451\"];/*基金持仓债券代码*/var zqCodes = \"0196111\";/*基金持仓股票代码(新市场号)*/va";
        System.out.println(StringUtil.findValueBetweenFirstQuotes(a,
            "zqCodes"));
        System.out.println("[" + (StringUtil.findValueBetweenFirstQuotes(a,
            "zqCodes") != null ?
            StringUtil.findValueBetweenFirstQuotes(a,
                "zqCodes"): "") + "]");

        List<String> zqCodes =
            JSON.parseArray("[" + (StringUtil.findValueBetweenFirstQuotes(a,
                "zqCodes") != null ?
                StringUtil.findValueBetweenFirstQuotes(a,
                    "zqCodes"): "") + "]", String.class);

        System.out.println(zqCodes.toString());
    }
}
