package com.supcoder.hub.dashboard.annotation



/**
 * RequiresPermissionsDesc
 *
 * @author lee
 * @date 2024/12/9
 */
@Target(AnnotationTarget.TYPE, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class RequiresPermissionsDesc(
    val menu: Array<String>,
    val button: Array<String>
)