package com.icekredit.framework.auth;
import com.icekredit.config.Config;
import com.icekredit.pojo.User;
import com.icekredit.service.CacheRedisService;
import com.icekredit.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;


/**
 * 对登录状态进行拦截
 * @author 
 *
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {
	private final static String API = "api";
	private Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			//判断是否是token登录
			logger.info(request.getRequestURL().toString());
			if (request.getRequestURL().toString().contains(API)){
				return true;
			}

			CacheRedisService cacheRedisService = SpringContextHolder.getBean("cacheService", CacheRedisService.class);
			Config config = SpringContextHolder.getBean("config", Config.class);

			// 未登陆用户唯一识别
			String uuid = MyCookie.getCookie(Const.COOKIE_UUID, false, request);
			if (MyString.isEmpty(uuid)) {
				MyCookie.addCookie(Const.COOKIE_UUID, System.currentTimeMillis() + Tools.getChar(10), response);
			}

			try {
				// 返回服务器ip
				response.setHeader("serviceIp", InetAddress.getLocalHost().getHostAddress());
			} catch (Exception e) {
				response.setHeader("serviceIp", "服务器配置异常，无法获取服务器IP");
			}
			if(request.getRequestURI().endsWith("login") ||
					request.getRequestURI().endsWith("Login") ||
					request.getRequestURI().endsWith("checkLoginOrNot") ||
					request.getRequestURI().endsWith("loginpage") ||
					request.getRequestURI().endsWith("loginOut") ||
					request.getRequestURI().endsWith("swagger-ui") ||
					request.getRequestURI().endsWith("getImgCode") ||
					request.getRequestURI().endsWith(".css") ||
					request.getRequestURI().endsWith(".js")){
				return true;
			}

			String token = MyCookie.getCookie(Const.COOKIE_TOKEN, false, request);
			String uid = MyCookie.getCookie(Const.COOKIE_USERID, false, request);
			// 前端没有传递token，未登录
			if (MyString.isEmpty(token) || MyString.isEmpty(uid)) {
				//response.sendRedirect("/checkLoginOrNot");
				return false;
			}

			// 后端没登录信息：登录超时
			User obj = (User) cacheRedisService.getObj(Const.CACHE_USER + uid);
			if (obj == null) {
				// 删除cookie
				MyCookie.deleteCookie(Const.COOKIE_TOKEN, request, response);
				//response.sendRedirect("/checkLoginOrNot");
				return false;
			}

			// 每次访问，将用户登录有效信息延长
			cacheRedisService.setObj(Const.CACHE_USER + uid, obj, config.getLoginInforTime());
			return true;

		} else
			return true;
	}

}