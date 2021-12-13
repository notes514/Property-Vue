package com.guet.property.common.exception;

import com.alibaba.fastjson.JSONObject;
import com.guet.property.util.CommonUtils;
import com.guet.property.util.constants.ErrorEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一异常拦截
 *
 * @author dhxstart
 * @date 2021/12/7 23:00
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @ExceptionHandler(value = Exception.class)
    public JSONObject defaultErrorHandler(HttpServletRequest request, Exception e) {
        String errorPosition = "";
        // 如果错误堆栈信息存在
        if (e.getStackTrace().length > 0) {
            StackTraceElement element = e.getStackTrace()[0];
            String fileName = element.getFileName() == null ? "未找到错误文件！" : element.getFileName();
            int lineNumber = element.getLineNumber();
            errorPosition = fileName + ":" + lineNumber;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ErrorEnum.E_400.getErrorCode());
        jsonObject.put("msg", ErrorEnum.E_400.getErrorMsg());
        JSONObject errorObject = new JSONObject();
        errorObject.put("errorLocation", e + "    错误位置:" + errorPosition);
        jsonObject.put("info", errorObject);
        logger.error("异常", e);
        return jsonObject;
    }

    /**
     * GET/POST请求方法错误的拦截器，因为开发中比较常见，而且发生在进入controller之前，
     * 上面的拦截器拦截不到这个错误，所以定义了这个拦截器
     *
     * @return com.alibaba.fastjson.JSONObject
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public JSONObject httpRequestMethodHandler() {
        return CommonUtils.errorJson(ErrorEnum.E_500);
    }

    /**
     * 此方法用于处理自定义错误的拦截器，拦截到错误后将返回这个错误类里边的json到给前端，
     * 常见的使用场景是参数校验失败，抛出此错误，返回错误信息给前端
     *
     * @param commonJsonException 自定义错误类
     * @return com.alibaba.fastjson.JSONObject
     */
    @ExceptionHandler(CommonJsonException.class)
    public JSONObject commonJsonExceptionHandler(CommonJsonException commonJsonException) {
        return commonJsonException.getResultJson();
    }

    /**
     * 进行权限不足的错误拦截
     *
     * @return com.alibaba.fastjson.JSONObject
     */
    @ExceptionHandler(UnauthorizedException.class)
    public JSONObject unauthorizedExceptionHandler() {
        return CommonUtils.errorJson(ErrorEnum.E_502);
    }

    /**
     * 进行未登录报错拦截，在请求需要权限的接口，如果没登录时会报错
     *
     * @return com.alibaba.fastjson.JSONObject
     */
    @ExceptionHandler(UnauthenticatedException.class)
    public JSONObject unauthenticatedException() {
        return CommonUtils.errorJson(ErrorEnum.E_20011);
    }
}
