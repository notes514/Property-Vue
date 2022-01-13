package com.guet.property.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.guet.property.common.exception.CommonJsonException;
import com.guet.property.dto.session.SessionUserInfo;
import com.guet.property.mapper.LoginMapper;
import com.guet.property.service.LoginService;
import com.guet.property.service.TokenService;
import com.guet.property.util.CommonUtils;
import com.guet.property.util.constants.ErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 此服务类用于实现针对于用户登录的常用操作
 *
 * @author dhxstart
 * @date 2021/12/11 18:12
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private TokenService tokenService;

    @Override
    public JSONObject authLogin(JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        JSONObject info = new JSONObject();
        JSONObject user = loginMapper.checkUser(username, password);
        if (user == null) {
            throw new CommonJsonException(ErrorEnum.E_10010);
        }
        String token = tokenService.generateToken(username);
        info.put("token", token);
        return CommonUtils.successJson(info, "登录成功！");
    }

    @Override
    public JSONObject getInfo() {
        // 从session获取用户信息
        SessionUserInfo userInfo = tokenService.getUserInfo();
        log.info(userInfo.toString());
        return CommonUtils.successJson(userInfo);
    }

    @Override
    public JSONObject logout() {
        tokenService.invalidateToken();
        return CommonUtils.successJson();
    }
}
