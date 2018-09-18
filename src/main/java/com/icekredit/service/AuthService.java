package com.icekredit.service;

import com.icekredit.pojo.vo.MyAuthVo;

public interface AuthService {

    String getTokenId(MyAuthVo myAuthVo);

    String CheckToken(String token);

}
