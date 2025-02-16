package org.data.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private int age;
    @Column
    private String address;
    @Column
    private String password;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "employee_permissions",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<Permission> permissions;

    @Temporal(TemporalType.DATE)  // Ensures correct format in DB
    private Date dateOfBirth;
    @Column(name = "gender") // New field for Gender
    private String gender;

    public Employee(){

    }

    public Employee(int id, String name, int age, String address, String password, Date dateOfBirth, String gender, Role role) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.password = password;
        this.dateOfBirth=dateOfBirth;
        this.gender=gender;
        this.role = role;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setAge(int age) { this.age = age; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public Set<Permission> getPermissions() {
        return permissions;
    }
    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
    public Date getDateOfBirth() { return dateOfBirth; } // New getter
    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; } // New setter
    public String getGender() { return gender; } // New getter
    public void setGender(String gender) { this.gender = gender; } // New setter

}
