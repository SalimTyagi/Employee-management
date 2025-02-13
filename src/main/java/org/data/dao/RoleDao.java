package org.data.dao;

import org.data.entities.Role;
import org.data.enums.RoleType;

import java.util.List;

public interface RoleDao {
    List<Role> findAllRoles();
    Role findRoleByType(RoleType roleType);
}
