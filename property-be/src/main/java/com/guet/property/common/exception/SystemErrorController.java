package com.guet.property.common.exception;

import com.alibaba.fastjson.JSONObject;
import com.guet.property.util.CommonUtils;
import com.guet.property.util.constants.ErrorEnum;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 此类用于系统错误拦截，主要是针对404错误
 *
 * @author dhxstart
 * @date 2021/12/7 23:21
 */
@Controller
public class SystemErrorController implements ErrorController {

    /**
     * 此方法用于处理登录后的各种错误路径，404页面改为返回此json
     * 在未登录情况下，大部分接口都已经被shiro拦截，返回让用户登录
     *
     * @return com.alibaba.fastjson.JSONObject
     */
    @RequestMapping(value = "/error")
    @ResponseBody
    public JSONObject handleError() {
        return CommonUtils.errorJson(ErrorEnum.E_501);
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
