package org.data.dao;

import org.data.entities.Permission;
import org.data.enums.PermissionType;

import java.util.List;

public interface PermissionDao {

    List<Permission> findAllPermissions();
    Permission findPermissionById(int id);
    Permission findPermissionByType(PermissionType permissionType);
}
