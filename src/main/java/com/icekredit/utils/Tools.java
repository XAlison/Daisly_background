package com.icekredit.utils;
import com.alibaba.fastjson.JSONObject;
import com.icekredit.dao.RedisCacheDao;
import com.icekredit.framework.auth.MyException;
import com.icekredit.pojo.User;
import com.icekredit.service.CacheRedisService;
import com.icekredit.service.UserService;
import com.jayway.restassured.path.xml.XmlPath;
import com.jayway.restassured.response.Response;
import org.dom4j.DocumentHelper;
import org.jsoup.Jsoup;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.jayway.restassured.RestAssured.get;


public class Tools {
	private final static String TOKEN = "token";

	// 获取图形验证码
	public static String getImgCode(HttpServletRequest request) throws MyException {
		RedisCacheDao redisCacheDao = SpringContextHolder.getBean("redisCacheDao", RedisCacheDao.class);
		String timesStr = redisCacheDao.getStr(Const.CACHE_IMGCODE_TIMES + MyCookie.getCookie(Const.COOKIE_UUID, false, request));
		int times = 0;
		if(timesStr != null){
			times = Integer.parseInt(timesStr.trim()) + 1;
		}
		if(times > 3){
			throw new MyException("000011");
		}
		redisCacheDao.setStr(Const.CACHE_IMGCODE_TIMES + MyCookie.getCookie(Const.COOKIE_UUID, false, request), times + "", 10 * 60);
		String imgCode = redisCacheDao.getStr(Const.CACHE_IMGCODE + MyCookie.getCookie(Const.COOKIE_UUID, false, request));
		return imgCode == null? System.currentTimeMillis()+"" : imgCode.toString();
	}

	public static String getChar(int num){
		String md="123456789abcdefghijkmnpqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ789abcd";
		Random random = new Random();
		String temp="";
		for(int i=0;i<num;i++){
			temp=temp+md.charAt(random.nextInt(50));
		}
		return temp;
	}

	/**
	 * 构造查询Map集合
	 * @param params 不定数量参数 格式(key1,value1,key2,value2....)
	 * @return
	 */
	public static Map<String, Object> getMap(Object... params) {
		if (params.length == 0 || params.length % 2 != 0) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < params.length; i = i + 2) {
			if (!MyString.isEmpty(params[i + 1]))
				map.put(params[i].toString(), params[i + 1]);
		}
		return map;

	}

	public static User getUser(){
		CacheRedisService cacheService = SpringContextHolder.getBean("cacheService", CacheRedisService.class);
		String uId = MyCookie.getCookie(Const.COOKIE_USERID, false, Tools.getRequest());
		return (User) cacheService.getObj(Const.CACHE_USER + uId);
	}

	public static User getUserByToken(){
		UserService userService = SpringContextHolder.getBean("userService", UserService.class);
		HttpServletRequest request = getRequest();
		String token = request.getHeader(TOKEN);
		User user = userService.getUserByToken(token);
		return user;
	}

	public static HttpServletRequest getRequest(){
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	public static boolean checkUserName(String userName){
		String regex = "^[0-9A-Za-z-_\\.]{5,20}$";		
		return userName.matches(regex);
	}
	public static boolean checkEmail(String email){
	        boolean flag = false;
	        try{
	                String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	                Pattern regex = Pattern.compile(check);
	                Matcher matcher = regex.matcher(email);
	                flag = matcher.matches();
	            }catch(Exception e){
	                flag = false;
	            }
	        return flag;
	}

	public static String checkStringFormat(String str){
		try {
			JSONObject.parseObject(str);
			return "json";
		}catch (Exception e){
			try {
				Jsoup.parse(str);
				return "html";

			}catch (Exception ee){
				try {
					DocumentHelper.parseText(str);
					return "xml";
				}catch (Exception eee){

				}
			}

		}
		return "text";
	}

	public static void main(String args[]){
		Response r = get("http://www.baidu.com").thenReturn();
		try{
			//r.getBody().prettyPeek()
			XmlPath xmlPath = r.getBody().xmlPath();
			System.out.println();
		}catch (Exception e){
			int a = 0;
		}
		System.out.println(checkStringFormat("<html></html>"));
	}
}
