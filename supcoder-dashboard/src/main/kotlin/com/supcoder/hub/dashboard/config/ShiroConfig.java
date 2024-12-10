package com.supcoder.hub.dashboard.config;

import com.supcoder.hub.dashboard.auth.JwtRealm;
import com.supcoder.hub.dashboard.filter.JwtTokenFilter;
import jakarta.servlet.Filter;
import org.apache.shiro.lang.util.LifecycleUtils;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


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
    public DefaultWebSecurityManager securityManager(Realm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        ThreadContext.bind(securityManager);
        LifecycleUtils.init(securityManager);
        return securityManager;
    }



    @Bean
    public ShiroFilterFactoryBean shiroFilterChainDefinition(DefaultSecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        chainDefinition.addPathDefinition("/api/auth/login", "anon");
        chainDefinition.addPathDefinition("/api/auth/register", "anon");
        chainDefinition.addPathDefinition("/api/auth/refresh-token", "anon");
        chainDefinition.addPathDefinition("/api/auth/mock-login", "anon");
        chainDefinition.addPathDefinition("/api/auth/captcha", "anon");
        chainDefinition.addPathDefinition("/api/auth/logout", "authc");
        chainDefinition.addPathDefinition("/api/auth/account", "authc");
        chainDefinition.addPathDefinition("/api/notices", "authc");
        chainDefinition.addPathDefinition("/**", "authc");
        factoryBean.setFilterChainDefinitionMap(chainDefinition.getFilterChainMap());
        factoryBean.setFilters(filters());
        return factoryBean;
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
