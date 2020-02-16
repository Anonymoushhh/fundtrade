package com.sdu.fund.common.utils;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.*;

/**
 * @program: fundproduct
 * @description: 对象属性比较工具类
 * @author: anonymous
 * @create: 2019-12-10 16:45
 **/
public class ObjectCompareUtil {

    public static Map<String, List<Object>> compareFields(Object obj1, Object obj2, String... ignoreArr) {

        try{
            Map<String, List<Object>> map = new HashMap<String, List<Object>>();
            List<String> ignoreList = null;
            if(ignoreArr != null && ignoreArr.length > 0){
                // array转化为list
                ignoreList = Arrays.asList(ignoreArr);
            }
            if (obj1.getClass() == obj2.getClass()) {// 只有两个对象都是同一类型的才有可比性

                Class clazz = obj1.getClass();

                // 获取object的属性描述

                PropertyDescriptor[] pds = Introspector.getBeanInfo(clazz,

                    Object.class).getPropertyDescriptors();

                for (PropertyDescriptor pd : pds) {// 这里就是所有的属性了

                    String name = pd.getName();// 属性名

                    if(ignoreList != null && ignoreList.contains(name)){// 如果当前属性选择忽略比较，跳到下一次循环

                        continue;

                    }

                    Method readMethod = pd.getReadMethod();// get方法

                    // 在obj1上调用get方法等同于获得obj1的属性值

                    Object o1 = readMethod.invoke(obj1);

                    // 在obj2上调用get方法等同于获得obj2的属性值

                    Object o2 = readMethod.invoke(obj2);

                    if(o1 instanceof Timestamp){

                        o1 = new Date(((Timestamp) o1).getTime());

                    }

                    if(o2 instanceof Timestamp){

                        o2 = new Date(((Timestamp) o2).getTime());

                    }

                    if(o1 == null && o2 == null){

                        continue;

                    }else if(o1 == null && o2 != null){

                        List<Object> list = new ArrayList<Object>();

                        list.add(o1);

                        list.add(o2);

                        map.put(name, list);

                        continue;

                    }

                    if (!o1.equals(o2)) {// 比较这两个值是否相等,不等就可以放入map了

                        List<Object> list = new ArrayList<Object>();

                        list.add(o1);

                        list.add(o2);

                        map.put(name, list);

                    }

                }

            }

            return map;

        }catch(Exception e){

            e.printStackTrace();

            return null;

        }

    }
}
