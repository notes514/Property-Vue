package com.guet.property.config.filter;

import com.guet.property.config.exception.CommonJsonException;
import com.guet.property.dto.session.SessionUserInfo;
import com.guet.property.service.TokenService;
import com.guet.property.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * 请求过滤类
 *
 * @author dhxstart
 * @date 2021/12/7 23:36
 */
@Component
@Slf4j
public class RequestFilter extends OncePerRequestFilter implements Filter {

    @Autowired
    TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            // 每个请求记录一个traceId,可以根据traceId搜索出本次请求的全部相关日志
            MDC.put("traceId", UUID.randomUUID().toString().replace("-", "").substring(0, 12));
            setUsername(request);
            setProductId(request);
            // 使request中的body可以重复读取 https://juejin.im/post/6858037733776949262#heading-4
            request = new ContentCachingRequestWrapper(request);
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            throw e;
        } finally {
            // 清理ThreadLocal
            MDC.clear();
        }
    }

    /**
     * 将url参数中的productId放入ThreadLocal
     *
     * @param request 请求头
     */
    private void setProductId(HttpServletRequest request) {
        String productIdStr = request.getParameter("productId");
        if (!StringUtils.isNullOrEmpty(productIdStr)) {
            log.debug("url中productId = {}", productIdStr);
            MDC.put("productId", productIdStr);
        }
    }

    /**
     * 设置用户token
     *
     * @param request 请求头
     * @date 2021/12/7 23:37
     */
    private void setUsername(HttpServletRequest request) {
        // 通过token解析出username
        String token = request.getHeader("token");
        if (!StringUtils.isNullOrEmpty(token)) {
            MDC.put("token", token);
            try {
                SessionUserInfo info = tokenService.getUserInfo();
                if (info != null) {
                    String username = info.getUsername();
                    MDC.put("username", username);
                }
            } catch (CommonJsonException e) {
                log.info("无效的token:{}", token);
            }
        }
    }
}
