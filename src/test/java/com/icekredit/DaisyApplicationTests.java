package com.icekredit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
//@SpringBootTest
public class DaisyApplicationTests {
    private Logger logger = LoggerFactory.getLogger(DaisyApplication.class);

	@Test
	public void contextLoads() {
	    logger.info("xxxxxxxxxxxxxxxxxx");
	}
	@Test
	public void testJEXL(){

	}

	@Test
	public void preProcess(){
		// 按指定模式在字符串查找
		String sourceCopy = "{{url}}";
		//\{[^}]*\}
		String regex = "\\{\\{[^}]*}}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(sourceCopy);
		while (matcher.find()) {
			String nameMatch = matcher.group().replaceAll("\\{\\{", "");
			String name = nameMatch.replaceAll("\\}\\}", "");
			String value = "你好";
			if (null == value){
				System.out.println(sourceCopy);
			}
			sourceCopy = sourceCopy.replaceAll("\\{\\{" + name + "}}", value);
		}
		System.out.println(sourceCopy);
	}

}
