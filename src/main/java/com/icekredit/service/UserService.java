package com.icekredit.service;
import com.icekredit.config.Config;
import com.icekredit.dto.LoginDto;
import com.icekredit.framework.auth.MyException;
import com.icekredit.mapper.mapperOffLine.UserMapper;
import com.icekredit.pojo.User;
import com.icekredit.pojo.vo.ChangePassWordVo;
import com.icekredit.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Service("userService")
public class UserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private CacheRedisService cacheRedisService;
	@Autowired
	Config config;

	public List<User> getUserByName(LoginDto user){
		return userMapper.getByLoginName(user);
	}

	public User getUserByToken(String token){
		return userMapper.getUserByToken(token);
	}

	public void updateToken(String token){
		userMapper.updateToken(Tools.getUser().getId(), token);
	}
	public void changePassWord(ChangePassWordVo changePassWordVo) throws MyException {
		User user = Tools.getUser();
		User userInDataBase = userMapper.getByUser(user);
		String newPassWdMd5 = MD5.encrytMD5(changePassWordVo.getNewPasswd());
		String oldPassWdMd5 = MD5.encrytMD5(changePassWordVo.getOldPasswd());
		String checkPassWdMd5 = MD5.encrytMD5(changePassWordVo.getPasswdCheck());
		if (userInDataBase == null){
			throw new MyException("000020");
		}else if (!user.getPassword().equals(oldPassWdMd5)){
			throw  new MyException("000019");
		}else if(!newPassWdMd5.equals(checkPassWdMd5)){
			throw new MyException("000021");
		}
		userMapper.updatePassword(user.getId(), newPassWdMd5);
	}
	public void login(User user, HttpServletRequest request, HttpServletResponse response) {
		String token  = Aes.encrypt(user.getId());
		MyCookie.addCookie(Const.COOKIE_TOKEN, token, response);
		// 将用户信息存入缓存
		cacheRedisService.setObj(Const.CACHE_USER + user.getId(), user, config.getLoginInforTime());
		MyCookie.addCookie(Const.COOKIE_USERID, user.getId(), response);
		MyCookie.addCookie(Const.COOKIE_USERNAME, user.getUserName(), response);
//		MyCookie.addCookie(Const.COOKIE_REMBER_PWD, user.get() , response);

//		if (model.getRemberPwd().equals("YES")) {
//			MyCookie.addCookie(Const.COOKIE_PASSWORD, model.getPassword(), true, response);
//		} else {
//			MyCookie.deleteCookie(Const.COOKIE_PASSWORD, request, response);
//		}
//		model.setSessionAdminName(model.getUserName());
	}
}
