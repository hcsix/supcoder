package com.supcoder.hub.dashboard.config;

import com.supcoder.hub.dashboard.auth.JwtRealm;
import com.supcoder.hub.dashboard.filter.JwtTokenFilter;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.util.LifecycleUtils;
import org.apache.shiro.util.ThreadContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * ShiroConfig
 *
 * @author lee
 * @date 2024/12/10
 */
@Configuration
public class ShiroConfig {

    @Bean
    public Realm realm() {
        return new JwtRealm();
    }

    @Bean
    public DefaultSecurityManager securityManager(Realm realm) {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(realm);
        ThreadContext.bind(securityManager);
        LifecycleUtils.init(securityManager);
        return securityManager;
    }


    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        chainDefinition.addPathDefinition("/api/auth/login", "anon");
        chainDefinition.addPathDefinition("/api/auth/register", "anon");
        chainDefinition.addPathDefinition("/api/auth/refresh-token", "anon");
        chainDefinition.addPathDefinition("/api/auth/mock-login", "anon");
        chainDefinition.addPathDefinition("/api/auth/captcha", "anon");
        chainDefinition.addPathDefinition("/api/auth/logout", "authc");
        chainDefinition.addPathDefinition("/api/auth/account", "authc");
        chainDefinition.addPathDefinition("/**", "authc");
        return chainDefinition;
    }


    @Bean
    public DefaultSessionStorageEvaluator sessionStorageEvaluator() {
        DefaultSessionStorageEvaluator sessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        sessionStorageEvaluator.setSessionStorageEnabled(false);
        return sessionStorageEvaluator;
    }



    @Bean
    public Map<String, Filter> filters() {
        Map<String, Filter> filters = new HashMap<>();
        filters.put("authc", new JwtTokenFilter());
        return filters;
    }
}
