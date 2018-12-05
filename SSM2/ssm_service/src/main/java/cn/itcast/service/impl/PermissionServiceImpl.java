package cn.itcast.service.impl;

import cn.itcast.dao.PermissionDao;
import cn.itcast.domain.Permission;
import cn.itcast.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;
    @Override
    public List<Permission> findAll() throws Exception {
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) throws Exception {
        permissionDao.save(permission);
    }

    @Override
    public List<Permission> findOtherPermission(String id) throws Exception{
        return permissionDao.findOtherPermission(id);
    }

    @Override
    public Permission findById(String id) throws Exception{
        return permissionDao.findById(id);
    }


    @Override
    public void change(Permission permission) throws Exception{
        permissionDao.change(permission);
    }
}
