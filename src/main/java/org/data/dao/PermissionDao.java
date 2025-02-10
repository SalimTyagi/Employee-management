package org.data.dao;

import org.data.entities.Permission;

import java.util.List;

public interface PermissionDao {

    List<Permission> findAllPermissions();
    Permission findPermissionById(int id);
}
