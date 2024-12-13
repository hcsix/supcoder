package com.supcoder.common.core.checker;

/**
 * ServiceChecker
 *
 * @author lee
 * @date 2024/12/12
 */
public class ServiceChecker {

    public static final String SERVICE_LOTTERY = "lottery";

    public static boolean isSupportedService(String service) {
        return SERVICE_LOTTERY.equals(service);
    }
}
