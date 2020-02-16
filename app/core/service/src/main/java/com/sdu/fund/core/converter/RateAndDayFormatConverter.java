package com.sdu.fund.core.converter;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @program: fundproduct
 * @description: 费率和时间范围转换工具类
 * @author: anonymous
 * @create: 2020/1/27 20:56
 **/
public class RateAndDayFormatConverter {

    private static final Map<String,String> convertMap = Maps.newLinkedHashMap();

    static{
        // 这俩顺序不能反
        convertMap.put("小于等于","]");
        convertMap.put("小于",")");

        convertMap.put("大于等于","[");
        convertMap.put("大于","(");


        convertMap.put("万元","0000");
        convertMap.put("元","");
        convertMap.put("每笔","");
        convertMap.put("%","");
        convertMap.put("&nbsp;","");
        convertMap.put("天","D");
        convertMap.put("月","M");
        convertMap.put("（每年）","");
        convertMap.put("年","Y");

        convertMap.put("---","null");
        convertMap.put("--","null");

        convertMap.put("，",",");
    }

    /*
     * @description 转换为存储标准格式
     * @param [str]
     * @return java.lang.String
     * @author anonymous
     * @date 2020/1/27
     */
    public static String convert2Format(String str){
        String[] split = StringUtils.split(str,"|");
        if(split.length>1){
            // 说明这一个格里有好几个费率，统一取最后一个
            str = split[split.length-1];
        }
        if(StringUtils.contains(str,"小于等于")){
            str = StringUtils.replace(str,"小于等于","");
            str = str+convertMap.get("小于等于");
        }
        if(StringUtils.contains(str,"小于")){
            str = StringUtils.replace(str,"小于","");
            str = str+convertMap.get("小于");
        }
        for (Map.Entry<String,String> entry:convertMap.entrySet()) {
            str = StringUtils.replace(str,entry.getKey(),entry.getValue());
        }
        return str;
    }

    public static void main(String[] args) {
        System.out.println(convert2Format("大于等于1年，小于2年"));
        System.out.println(convert2Format("大于等于7天，小于1年"));
        System.out.println(convert2Format("小于7天"));
        System.out.println(convert2Format("大于等于2年"));
        System.out.println(convert2Format("小于50万元"));
        System.out.println(convert2Format("大于等于50万元，小于200万元"));
        System.out.println(convert2Format("大于等于500万元"));
        System.out.println(convert2Format("大于等于1天，小于2年"));
        String str = "007300,国联安中证半导体ETF联接A,GLAZZBDTETFLJA,2020-01-23,1.7785,1.7785,-1.1780,8.2669,28.4208,50.2238,73" +
                ".0901,,,,23.6873,77.8322,2019-06-26,1,77.8322,0.80%,0.08%,1,0.08%,1,";
        String[] strArray = str.split(",",25);
        System.out.println(strArray.length);
    }
}
