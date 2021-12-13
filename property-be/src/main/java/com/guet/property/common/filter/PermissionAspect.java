package com.guet.property.common.filter;

import com.guet.property.common.annotation.Logical;
import com.guet.property.common.annotation.RequiresPermissions;
import com.guet.property.common.exception.UnauthorizedException;
import com.guet.property.dto.session.SessionUserInfo;
import com.guet.property.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;

/**
 * 角色权限控制拦截器
 *
 * @author dhxstart
 * @date 2021/12/7 23:35
 */
@Aspect
@Slf4j
@Component
@Order(3)
public class PermissionAspect {
    @Autowired
    TokenService tokenService;

    @Before("@annotation(com.guet.property.common.annotation.RequiresPermissions)")
    public void before(JoinPoint joinPoint) {
        log.debug("开始校验[操作权限]");
        SessionUserInfo userInfo = tokenService.getUserInfo();
        Set<String> myCodes = userInfo.getPermissionList();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        RequiresPermissions a = methodSignature.getMethod().getAnnotation(RequiresPermissions.class);
        String[] perms = a.value();
        log.debug("校验权限code: {}", Arrays.toString(perms));
        log.debug("用户已有权限: {}", myCodes);
        // 5.对比[要求]的code和[用户实际拥有]的code
        if (a.logical() == Logical.AND) {
            // 必须包含要求的每个权限
            for (String perm : perms) {
                if (!myCodes.contains(perm)) {
                    log.warn("用户缺少权限 code : {}", perm);
                    // 抛出[权限不足]的异常
                    throw new UnauthorizedException();
                }
            }
        } else {
            // 多个权限只需包含其中一种即可
            boolean flag = false;
            for (String perm : perms) {
                if (myCodes.contains(perm)) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                log.warn("用户缺少权限 code= : {} (任意有一种即可)", Arrays.toString(perms));
                // 抛出[权限不足]的异常
                throw new UnauthorizedException();
            }
        }
    }
}
