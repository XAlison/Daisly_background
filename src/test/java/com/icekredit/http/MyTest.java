package com.icekredit.http;

import com.alibaba.fastjson.*;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyTest {
    @Test
    public void testStringFind() {

        // 按指定模式在字符串查找
        String line = "This<order><for> QT3000! OK?";
        //\{[^}]*\}
        String regex = "<[^>]*>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            System.out.println(matcher.group().replaceAll("<", "").replaceAll(">", ""));
        }
    }
    @Test
    public void testGetAssertions(){
        String s = "tests[\".mobile\"] = jsonData.result.phone === \"18305843069\";".replace(" ", "");
        String patternTargetValue = "=\\S*=";
        String patternTrueValue = "=\"\\S*\";";

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(patternTrueValue);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(s);
        int count = 0;
        if (m.find( )) {
            System.out.println("Found value: " + m.group(0).replace("=","") );
        }
    }

    @Test
    public void testJexl(){

    }
}