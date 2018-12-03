package cn.itcast.service;

import cn.itcast.domain.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll() throws Exception;
    void save(Role role) throws Exception;

    List<Role> findOtherRole(String id) throws Exception;

    Role findById(String id) throws Exception;

    void addPermissionToRole(String roleId, String[] ids) throws Exception;
}
