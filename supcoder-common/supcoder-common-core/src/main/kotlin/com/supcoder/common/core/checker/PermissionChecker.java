package com.supcoder.common.core.checker;

/**
 * PermissionChecker
 *
 * @author lee
 * @date 2024/12/12
 */
public class PermissionChecker {

    public static final String PERMISSION_ALL = "*";
    public static final String PERMISSION_READ = "r";
    public static final String PERMISSION_WRITE = "w";

    public static boolean isFormatValid(String permission) {
        return permission.equals(PERMISSION_ALL) || permission.equals(PERMISSION_READ) || permission.equals(PERMISSION_WRITE);
    }


    public static boolean hasPermission(String permissions, String permission) {
        return permissions.contains(permission) || permissions.contains(PERMISSION_ALL);
    }

}
