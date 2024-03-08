package org.example.dao;

import org.example.entities.Departement;
import org.example.entities.Employee;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO{
    private final SessionFactory sessionFactory;

    public EmployeeDAOImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public List<Employee> trouverEmployesParDepartement(Departement departement) {
        try (Session session = sessionFactory.openSession()){
             session.beginTransaction();
            Query query = session.createQuery("Select e FROM Employee e WHERE e.departement =:departement");
            query.setParameter("departement",departement);
            List<Employee> employees = query.getResultList();
             session.getTransaction().commit();
             return  employees;
        }

    }



    @Override
    public Employee ajouterEmploye(Employee employee) {
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.save(employee);
            session.getTransaction().commit();
            return employee;
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        try(Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("Select e from Employee e");
            return query.getResultList();
        }
    }
    @Override
    public void supprimerEmploye(Long id) {
        try (Session session = sessionFactory.openSession()){
            session.getTransaction().begin();

            Employee employee = session.get(Employee.class, id);

            if (employee != null) {
                // Retirer l'employé de son département
                if (employee.getDepartement() != null) {
                    employee.getDepartement().getEmployes().remove(employee);
                }

                session.delete(employee);
                System.out.println("Suppression réussie de l'employé.");
            } else {
                System.out.println("Échec de la suppression de l'employé avec l'ID : " + id + ". L'employé n'existe pas.");
            }

            session.getTransaction().commit();
        }
    }
}
