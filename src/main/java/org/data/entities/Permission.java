package org.data.entities;

import org.data.enums.PermissionType;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "permissions")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private PermissionType permissionType;

    @ManyToMany(mappedBy = "permissions")
    private Set<Employee> employees;

    public Permission() {}

    public Permission(PermissionType permissionType) {
        this.permissionType = permissionType;
    }

    public int getId() { return id; }

    public PermissionType getPermissionType() { return permissionType; }
    public void setPermissionType(PermissionType permissionType) { this.permissionType = permissionType; }

    public Set<Employee> getEmployees() { return employees; }
    public void setEmployees(Set<Employee> employees) { this.employees = employees; }
}
