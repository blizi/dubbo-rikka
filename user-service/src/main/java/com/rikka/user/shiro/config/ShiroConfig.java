package com.rikka.user.shiro.config;


import com.rikka.user.shiro.factory.StateLessSubjectFactory;
import com.rikka.user.shiro.filter.JwtFilter;
import com.rikka.user.shiro.filter.RolesFilter;
import com.rikka.user.shiro.realm.UserRealm;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * shiro  配置类
 */
@Configuration
public class ShiroConfig {

    @Bean("userRealm")
    UserRealm getUserRealm(){
        return new UserRealm();
    }

    @Bean("SecurityManager")
    SecurityManager securityManager(@Qualifier("userRealm") UserRealm realm){
        DefaultWebSecurityManager defaultSecurityManager  = new DefaultWebSecurityManager();
        defaultSecurityManager.setRealm(realm);
        /*
         * 关闭shiro自带的session，详情见文档
         *
         */
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        defaultSecurityManager.setSubjectDAO(subjectDAO);
        defaultSecurityManager.setSubjectFactory(getSubjectFactory());
        return defaultSecurityManager ;
    }

    @Bean
    StateLessSubjectFactory getSubjectFactory(){
        return new StateLessSubjectFactory();
    }

    //不加这个  注解不生效，具体不详
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }
    //加入注解的使用，不加入这个注解不生效
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
    //Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("SecurityManager") SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filterMap = new HashMap<>();
        //自定义拦截器
        filterMap.put("jwt",new JwtFilter());
        filterMap.put("jwtRoles",new RolesFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        /**
         * anon: 无需认证即可访问
         * authc: 需要认证才可访问
         * user: 点击“记住我”功能可访问
         * perms: 拥有权限才可以访问
         * role: 拥有某个角色权限才能访问
         */
        //登出
        Map<String,String> map = new HashMap<>();
        map.put("/logout", "logout");
        //对所有用户认证
        map.put("/auth/login","anon");
        //map.put("/test","jwtRoles[admin]");
        map.put("/users/**", "jwt");
        //未认证时跳转接口
        shiroFilterFactoryBean.setLoginUrl("/rz");
        //首页
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //错误页面，认证不通过跳转
        shiroFilterFactoryBean.setUnauthorizedUrl("/error");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }
}
