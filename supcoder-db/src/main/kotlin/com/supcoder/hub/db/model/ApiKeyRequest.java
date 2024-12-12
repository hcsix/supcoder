package com.supcoder.hub.db.model;


/**
 * ApiKeyRequest
 *
 * @author lee
 * @date 2024/12/12
 */
public class ApiKeyRequest {
    private String service;
    private String permissions;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }
}
