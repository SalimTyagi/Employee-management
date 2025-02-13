package org.data.services;

import org.data.dao.RoleDao;
import org.data.entities.Role;
import org.data.enums.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Override
    public List<Role> findAllRoles() {
        return roleDao.findAllRoles();
    }

    @Override
    public Role getRoleByType(RoleType roleType) {
        return roleDao.findRoleByType(roleType);
    }
}
