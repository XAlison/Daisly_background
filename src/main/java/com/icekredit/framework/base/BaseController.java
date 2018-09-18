package com.icekredit.framework.base;
import com.icekredit.framework.JsonResult;
import com.icekredit.framework.auth.MyException;
import com.icekredit.service.CacheRedisService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;

public abstract class BaseController{
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected final static int view = 100;
	protected final static int modInter = 1;
	protected final static int addInter = 2;
	protected final static int delInter = 3;

	protected final static int modModule = 4;
	protected final static int addModule = 5;
	protected final static int delModule = 6;

	protected final static int modArticle = 7;
	protected final static int addArticle = 8;
	protected final static int delArticle = 9;

	protected final static int modDict = 10;
	protected final static int addDict = 11;
	protected final static int delDict = 12;

	protected final static int modSource = 13;
	protected final static int addSource = 14;
	protected final static int delSource = 15;

	protected final static int modError = 16;
	protected final static int addError = 17;
	protected final static int delError = 18;

	@Autowired
	protected CacheRedisService cacheRedisService;

	private static Logger logger = org.slf4j.LoggerFactory.getLogger(BaseController.class);
	/**
	 * spring 中request、response是线程安全的，可以直接注入
	 * 
	 * @ModelAttribute注解只有在被
	 * @Controller和@ControllerAdvice两个注解的类下使用 ModelAttribute的作用
	 *    1)放置在方法的形参上： 表示引用Model中的数据
	 *    2)放置在方法上面：表示请求该类的每个Action前都会首先执行它也可以将一些准备数据的操作放置在该方法里面。
	 * @param request
	 * @param response
	 */
	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	/**
	 * @return
	 */
	protected HashMap<String, String> getRequestHeaders() {
		HashMap<String, String> requestHeaders = new HashMap<String, String>();
		@SuppressWarnings("unchecked")
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			String headerValue = request.getHeader(headerName);
			requestHeaders.put(headerName, headerValue);
		}
		return requestHeaders;
	}

	/**
	 * @return
	 */
	protected HashMap<String, String> getRequestParams() {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		@SuppressWarnings("unchecked")
		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement();
			String paramValue = request.getParameter(paramName);
			requestParams.put(paramName, paramValue);
		}
		return requestParams;
	}

	@ExceptionHandler({ Exception.class })
	@ResponseBody
	public JsonResult expHandler(HttpServletRequest request, Exception ex) {
		if (ex instanceof MyException) {
			return new JsonResult((MyException) ex);
		} else {
			logger.error(ex.getMessage());
			return new JsonResult(new MyException("000001", ex.getMessage()));
		}
	}

	protected void printMsg(String message) {
		response.setHeader("Content-Type", "text/html");
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter out = response.getWriter();
			out.write(message);
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.error(e.toString());
		}
	}


	protected Object getParam(String key, String def) {
		String value = request.getParameter(key);
		return value == null ? def : value;
	}

}