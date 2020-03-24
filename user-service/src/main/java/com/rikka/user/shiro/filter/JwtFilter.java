package com.rikka.user.shiro.filter;

import com.rikka.common.constant.SecretConstant;
import com.rikka.common.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.util.Assert;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Jwt filter
 *
 */

@Slf4j
public class JwtFilter extends BasicHttpAuthenticationFilter {

    /**
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        System.out.println("filter========");
        String token = request.getParameter("token");
        log.info("token: {}",token);
        Assert.notNull(token,"token is null !");
        JwtUtil.analyToken(token, SecretConstant.SECRET.getName());
        return true;
    }
    /**
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return true;
    }
}
