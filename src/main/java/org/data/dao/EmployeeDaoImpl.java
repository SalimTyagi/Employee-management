package org.data.dao;

import org.data.entities.Employee;
import org.data.entities.Permission;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveEmployee(Employee employee) {

        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
             transaction = session.beginTransaction();
             session.saveOrUpdate(employee);
             transaction.commit();
        }catch (HibernateException ex){
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    @Override
    public Employee findEmployeeById(int id) {

        try(Session session = sessionFactory.openSession()){
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Employee> employeeCriteriaQuery= criteriaBuilder.createQuery(Employee.class);
            Root<Employee> employeeRoot = employeeCriteriaQuery.from(Employee.class);
            employeeCriteriaQuery.select(employeeRoot).where(criteriaBuilder.equal(employeeRoot.get("id"),id));
            Query<Employee> query= session.createQuery(employeeCriteriaQuery);
            return query.uniqueResult();
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
       try(Session session = sessionFactory.openSession()){
           CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
           CriteriaQuery<Employee> employeeCriteriaQuery= criteriaBuilder.createQuery(Employee.class);
           Root<Employee> employeeRoot = employeeCriteriaQuery.from(Employee.class);
           employeeCriteriaQuery.select(employeeRoot);
           Query<Employee> query= session.createQuery(employeeCriteriaQuery);
           return query.list();
       }
    }

    @Override
    public void deleteEmployee(int id) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
           transaction = session.beginTransaction();
           Employee employee = session.get(Employee.class,id);
           if(employee!=null){
               if (employee.getRole() != null) {
                   employee.getRole().getEmployees().remove(employee);
                   employee.setRole(null);
               }

               if (employee.getPermissions() != null) {
                   for (Permission permission : employee.getPermissions()) {
                       permission.getEmployees().remove(employee);
                   }
                   employee.getPermissions().clear();
               }

               session.delete(employee);
           }else {
               System.out.println("No Employee found with ID :"+id);
           }
           transaction.commit();
        }catch (HibernateException ex){
            if(transaction!=null){
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    @Override
    public Employee findEmployeeByUsername(String userName) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Employee WHERE name = :name", Employee.class)
                    .setParameter("name", userName)
                    .uniqueResult();
        }
    }
    @Override
    public List<Employee> searchEmployees(String searchQuery) {
        try (Session session = sessionFactory.openSession()) {
            String sql = "SELECT * FROM employee WHERE search_vector @@ to_tsquery(:searchQuery)";
            Query<Employee> query = session.createNativeQuery(sql, Employee.class);
            query.setParameter("searchQuery", searchQuery + ":*");
            List<Employee> resultList = query.getResultList();
            return resultList;
        }
    }

}
