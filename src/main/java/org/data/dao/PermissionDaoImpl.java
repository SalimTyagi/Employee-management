package org.data.dao;

import org.data.entities.Permission;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PermissionDaoImpl implements PermissionDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Permission> findAllPermissions() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Permission", Permission.class).list();
        }
    }

    @Override
    public Permission findPermissionById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Permission.class, id);
        }
    }
}
