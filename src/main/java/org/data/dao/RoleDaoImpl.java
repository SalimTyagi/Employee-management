package org.data.dao;

import org.data.entities.Role;
import org.data.enums.RoleType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Role> findAllRoles() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Role> criteriaQuery = criteriaBuilder.createQuery(Role.class);
            Root<Role> roleRoot= criteriaQuery.from(Role.class);
            criteriaQuery.select(roleRoot);
            return session.createQuery(criteriaQuery).getResultList();
            //return session.createQuery("FROM Role", Role.class).list();
        }
    }
    @Override
    public Role findRoleByType(RoleType roleType) {

        try(Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder= session.getCriteriaBuilder();
            var criteriaQuery = criteriaBuilder.createQuery(Role.class);
            Root<Role> roleRoot = criteriaQuery.from(Role.class);
            criteriaQuery.select(roleRoot).where(criteriaBuilder.equal(roleRoot.get("roleType"), roleType));
            Role role = session.createQuery(criteriaQuery).getSingleResult();
            return role;
        }
//        Session session = sessionFactory.openSession();
//        Query<Role> query = session.createQuery("FROM Role WHERE roleType = :roleType", Role.class);
//        query.setParameter("roleType", roleType);
//        return query.uniqueResult();
    }
}
