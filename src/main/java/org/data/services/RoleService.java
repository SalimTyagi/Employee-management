package org.data.services;

import org.data.entities.Role;
import org.data.enums.RoleType;

import java.util.List;

public interface RoleService {
    List<Role> findAllRoles();
    Role getRoleByType(RoleType manager);
}
