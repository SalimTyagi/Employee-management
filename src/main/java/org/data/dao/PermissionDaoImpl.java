package org.data.dao;

import org.data.entities.Permission;
import org.data.enums.PermissionType;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

    @Override
    public Permission findPermissionByType(PermissionType permissionType) {
        try(Session session= sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Permission> criteriaQuery = criteriaBuilder.createQuery(Permission.class);
            Root<Permission> permissionRoot = criteriaQuery.from(Permission.class);
            criteriaQuery.select(permissionRoot).where(criteriaBuilder.equal(permissionRoot.get("permissionType"),permissionType));
            Permission permission = session.createQuery(criteriaQuery).getSingleResult();
            return permission;

        }
//        Session session = sessionFactory.openSession();
//        Query<Permission> query = session.createQuery("FROM Permission WHERE permissionType = :permissionType", Permission.class);
//        query.setParameter("permissionType", permissionType);
//        return query.uniqueResult();
    }
}
