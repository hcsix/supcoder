package com.supcoder.hub.dashboard.config;

import com.supcoder.hub.dashboard.shiro.UserAuthorizingRealm;
import com.supcoder.hub.dashboard.shiro.UserSessionManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ShiroConfig
 *
 * @author lee
 * @date 2024/12/10
 */
public class ShiroConfig {

    @Bean
    public Realm realm() {
        return new UserAuthorizingRealm();
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(org.apache.shiro.mgt.SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/api/auth/kaptcha", "anon");
        filterChainDefinitionMap.put("/api/auth/login", "anon");
        filterChainDefinitionMap.put("/api/auth/401", "anon");
        filterChainDefinitionMap.put("/api/auth/index", "anon");
        filterChainDefinitionMap.put("/api/auth/403", "anon");
        filterChainDefinitionMap.put("/api/index/*", "anon");

        filterChainDefinitionMap.put("/api/**", "authc");
        shiroFilterFactoryBean.setLoginUrl("/api/auth/401");
        shiroFilterFactoryBean.setSuccessUrl("/api/auth/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/api/auth/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SessionManager sessionManager() {

        return new UserSessionManager();
    }

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm());
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor =
                new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public static DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }
}
