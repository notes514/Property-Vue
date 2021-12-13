package com.guet.property.common.exception;

import com.alibaba.fastjson.JSONObject;
import com.guet.property.util.CommonUtils;
import com.guet.property.util.constants.ErrorEnum;

/**
 * 此类用于在参数校验，如果不符合要求，则抛出错误，拦截器可以同意拦截此错误，将其中的json返回给前端
 *
 * @author dhxstart
 * @date 2021/12/7 22:27
 */
public class CommonJsonException extends RuntimeException {
    private final JSONObject resultJson;

    /**
     * 调用时可在任何代码处直接throws这个Exception，都会统一进行拦截，并封装好json返回给前台
     *
     * @param errorEnum 错误的 ErrorEnum
     */
    public CommonJsonException(ErrorEnum errorEnum) {
        this.resultJson = CommonUtils.errorJson(errorEnum);
    }

    public CommonJsonException(JSONObject resultJson) {
        this.resultJson = resultJson;
    }

    public JSONObject getResultJson() {
        return resultJson;
    }
}
