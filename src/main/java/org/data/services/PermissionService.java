package org.data.services;

import org.data.entities.Employee;
import org.data.entities.Permission;
import org.data.enums.PermissionType;

import java.util.List;

public interface PermissionService {
    List<Permission> findAllPermissions();
    Permission findPermissionById(int id);
    boolean hasPermission(Employee employee,PermissionType permissionType);
    Permission getPermissionByType(PermissionType permissionType);
}
