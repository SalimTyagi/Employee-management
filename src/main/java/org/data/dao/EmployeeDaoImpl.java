package org.data.dao;

import org.data.entities.Employee;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    private SessionFactory sessionFactory;

//    static {
//        try {
//            sessionFactory = new Configuration().configure().buildSessionFactory();
//        } catch (Throwable ex) {
//            System.err.println("Failed to create sessionFactory object." + ex);
//            throw new ExceptionInInitializerError(ex);
//        }
//    }

    @Override
    public void saveEmployee(Employee employee) {

        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    @Override
    public Employee findEmployeeById(int id) {

        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);
        criteriaQuery.select(employeeRoot);

        Query<Employee> employeeQuery = session.createQuery(criteriaQuery);
        return employeeQuery.list().get(0);



//        return sessionFactory.getCurrentSession()
//                .createQuery("FROM Employee e WHERE e.id = :id", Employee.class)
//                .setParameter("id", id)
//                .uniqueResult();
    }
}
