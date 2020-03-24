package com.rikka.user.shiro.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 自定义权限 filter
 */
@Slf4j
public class RolesFilter extends BasicHttpAuthenticationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
//        Subject subject = SubjectUtils.getSubject(request.getParameter("token"));
//        log.info("{}",mappedValue);
//        Assert.notNull(subject,"subject is null");
//        log.info("{}",subject.hasRole("admin1"));
//        log.info("{}",mappedValue);
        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return super.onAccessDenied(request, response);
    }
}
