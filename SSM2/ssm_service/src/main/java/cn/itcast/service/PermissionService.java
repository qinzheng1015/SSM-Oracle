package cn.itcast.service;

import cn.itcast.domain.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> findAll() throws Exception;
    void save(Permission permission) throws Exception;

    List<Permission> findOtherPermission(String id) throws Exception;

    Permission findById(String id) throws Exception;

    void change(Permission permission) throws Exception;
}
