package com.supcoder.hub.db.service;

import com.supcoder.hub.db.dao.PermissionMapper;
import com.supcoder.hub.db.domain.Permission;
import com.supcoder.hub.db.domain.PermissionExample;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PermissionService {
    @Resource
    private PermissionMapper permissionMapper;

    public Set<String> queryByRoleIds(Integer[] roleIds) {
        Set<String> permissions = new HashSet<String>();
        if(roleIds.length == 0){
            return permissions;
        }

        PermissionExample example = new PermissionExample();
        example.or().andRoleIdIn(Arrays.asList(roleIds)).andDeletedEqualTo(false);
        List<Permission> permissionList = permissionMapper.selectByExample(example);

        for(Permission permission : permissionList){
            permissions.add(permission.getPermission());
        }

        return permissions;
    }


    public Set<String> queryByRoleId(Integer roleId) {
        Set<String> permissions = new HashSet<String>();
        if(roleId == null){
            return permissions;
        }

        PermissionExample example = new PermissionExample();
        example.or().andRoleIdEqualTo(roleId).andDeletedEqualTo(false);
        List<Permission> permissionList = permissionMapper.selectByExample(example);

        for(Permission permission : permissionList){
            permissions.add(permission.getPermission());
        }

        return permissions;
    }

    public Set<String> queryByRoleId(List<Integer> roleIds) {
        Set<String> permissions = new HashSet<String>();
        if(roleIds == null || roleIds.isEmpty()){
            return permissions;
        }

        PermissionExample example = new PermissionExample();
        example.or().andRoleIdIn(roleIds).andDeletedEqualTo(false);
        List<Permission> permissionList = permissionMapper.selectByExample(example);

        for(Permission permission : permissionList){
            permissions.add(permission.getPermission());
        }

        return permissions;
    }

    public boolean checkSuperPermission(Integer roleId) {
        if(roleId == null){
            return false;
        }

        PermissionExample example = new PermissionExample();
        example.or().andRoleIdEqualTo(roleId).andPermissionEqualTo("*").andDeletedEqualTo(false);
        return permissionMapper.countByExample(example) != 0;
    }

    public boolean checkSuperPermission(List<Integer> roleIds) {
        if(roleIds == null || roleIds.isEmpty()){
            return false;
        }

        PermissionExample example = new PermissionExample();
        example.or().andRoleIdIn(roleIds).andPermissionEqualTo("*").andDeletedEqualTo(false);
        return permissionMapper.countByExample(example) != 0;
    }

    public void deleteByRoleId(Integer roleId) {
        PermissionExample example = new PermissionExample();
        example.or().andRoleIdEqualTo(roleId).andDeletedEqualTo(false);
        permissionMapper.logicalDeleteByExample(example);
    }

    public void add(Permission Permission) {
        Permission.setAddTime(LocalDateTime.now());
        Permission.setUpdateTime(LocalDateTime.now());
        permissionMapper.insertSelective(Permission);
    }
}
