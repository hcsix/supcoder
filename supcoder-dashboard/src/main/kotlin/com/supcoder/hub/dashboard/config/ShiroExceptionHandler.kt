package com.supcoder.hub.dashboard.config

import com.supcoder.hub.core.util.ResultUtil
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.tomcat.util.http.ResponseUtil
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ShiroExceptionHandler
 *
 * @author lee
 * @date 2024/12/10
 */


@ControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
open class ShiroExceptionHandler {

    private val logger: Log = LogFactory.getLog(ShiroExceptionHandler::class. java)

    @ExceptionHandler(AuthenticationException::class)
    @ResponseBody
    open fun unauthenticatedHandler(e: AuthenticationException): Object {
        logger.warn(e.message, e)
        return ResultUtil.unLogin() as Object
    }

    @ExceptionHandler(AuthorizationException::class)
    @ResponseBody
    open fun unauthorizedHandler(e: AuthorizationException): Object {
        logger.warn(e.message, e)
        return ResultUtil.unAuth() as Object
    }

}