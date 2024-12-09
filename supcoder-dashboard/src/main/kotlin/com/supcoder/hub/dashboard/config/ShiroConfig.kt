package com.supcoder.hub.dashboard.config

import com.supcoder.hub.dashboard.shiro.UserAuthorizingRealm
import com.supcoder.hub.dashboard.shiro.UserSessionManager
import org.apache.shiro.spring.web.ShiroFilterFactoryBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.DependsOn;

/**
 * ShiroConfig
 *
 * @author lee
 * @date 2024/12/9
 */
@Configuration
open class ShiroConfig {

    @Bean
    open fun realm(): Realm {
        return UserAuthorizingRealm()
    }

    @Bean
    open fun shiroFilterFactoryBean(securityManager: SecurityManager): ShiroFilterFactoryBean {
        val shiroFilterFactoryBean = ShiroFilterFactoryBean()
        shiroFilterFactoryBean.securityManager = securityManager;
        val filterChainDefinitionMap = LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/admin/auth/kaptcha", "anon");
        filterChainDefinitionMap.put("/admin/auth/login", "anon");
        filterChainDefinitionMap.put("/admin/auth/401", "anon");
        filterChainDefinitionMap.put("/admin/auth/index", "anon");
        filterChainDefinitionMap.put("/admin/auth/403", "anon");
        filterChainDefinitionMap.put("/admin/index/*", "anon");
        filterChainDefinitionMap.put("/admin/**", "authc");
        shiroFilterFactoryBean.loginUrl = "/admin/auth/401";
        shiroFilterFactoryBean.successUrl = "/admin/auth/index";
        shiroFilterFactoryBean.unauthorizedUrl = "/admin/auth/403";
        shiroFilterFactoryBean.filterChainDefinitionMap = filterChainDefinitionMap;
        return shiroFilterFactoryBean;
    }

    @Bean
    open fun sessionManager(): SessionManager {
        return UserSessionManager()
    }

    @Bean
    open fun defaultWebSecurityManager(): DefaultWebSecurityManager {
        val securityManager = DefaultWebSecurityManager();
        securityManager.setRealm(realm());
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    @Bean
    open fun authorizationAttributeSourceAdvisor(securityManager: SecurityManager): AuthorizationAttributeSourceAdvisor {
        val authorizationAttributeSourceAdvisor = AuthorizationAttributeSourceAdvisor()
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    open fun defaultAdvisorAutoProxyCreator(): DefaultAdvisorAutoProxyCreator {
        val creator = DefaultAdvisorAutoProxyCreator();
        creator.isProxyTargetClass = true;
        return creator;
    }
}