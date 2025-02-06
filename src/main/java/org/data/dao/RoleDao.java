package org.data.dao;

import org.data.entities.Role;

import java.util.List;

public interface RoleDao {
    List<Role> findAllRoles();
}
