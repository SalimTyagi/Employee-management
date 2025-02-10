package org.data.services;

import org.data.dao.PermissionDao;
import org.data.entities.Employee;
import org.data.entities.Permission;
import org.data.enums.PermissionType;
import org.data.enums.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;
    @Override
    public List<Permission> findAllPermissions() {
        return permissionDao.findAllPermissions();
    }
    @Override
    public Permission findPermissionById(int id) {
        return permissionDao.findPermissionById(id);
    }

    @Override
    public boolean hasPermission(Employee employee, PermissionType permissionType) {
        if (employee == null || employee.getPermissions() == null) {
            return false;
        }

        // Admin should have all permissions by default
        if (employee.getRole().getRoleType().equals(RoleType.ADMIN)) {
            return true;
        }

        // Check if employee has the given permission
        return employee.getPermissions().stream()
                .anyMatch(permission -> permission.getPermissionType().equals(permissionType));
    }
}
