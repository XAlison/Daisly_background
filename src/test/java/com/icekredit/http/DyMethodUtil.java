//package com.icekredit.http;
//
//import java.util.Map;
//
//import org.apache.commons.jexl2.Expression;
//import org.apache.commons.jexl2.JexlContext;
//import org.apache.commons.jexl2.JexlEngine;
//import org.apache.commons.jexl2.MapContext;
//
///**
// * 动态加载方法
// * @author wangyfc
// *
// */
//public class DyMethodUtil {
//
//    public static Object invokeMethod(String jexlExp,Map<String,Object> map){
//        JexlEngine jexl=new JexlEngine();
//        Expression e = jexl.createExpression(jexlExp);
//        JexlContext jc = new MapContext();
//        for(String key:map.keySet()){
//            jc.set(key, map.get(key));
//        }
//        if(null==e.evaluate(jc)){
//            return "";
//        }
//        return e.evaluate(jc);
//    }
//
//}
