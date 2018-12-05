package cn.itcast.dao;

import cn.itcast.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface PermissionDao {
    @Select("select * from permission where id in (select permissionId from role_permission where roleId = #{roleId})")
    public List<Permission> findByRoleId(String roleId) throws Exception;

    @Select("select * from permission")
    List<Permission> findAll() throws Exception;

    @Insert("insert into permission(permissionName,url) values (#{permissionName},#{url})")
    void save(Permission permission);

    @Select("select * from permission where id not in(select permissionId from role_permission where roleId = #{id})")
    List<Permission> findOtherPermission(String id) throws Exception;

    @Select("select * from permission where id = #{id}")
    Permission findById(String id) throws Exception;

    @Update("update permission set permissionName = #{permissionName}, url = #{url}  where id = #{id}")
    void change(Permission permission) throws Exception;
}
