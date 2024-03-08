package org.example.dao;

import org.example.entities.Departement;
import org.example.entities.Employee;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class DepartementDAOImpl implements DepartementDAO {
    private final SessionFactory sessionFactory;

    public DepartementDAOImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }
    @Override
    public Departement ajouterDepartement(Departement departement) {
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.save(departement);
            session.getTransaction().commit();
            return departement;
        }
    }

    @Override
    public List<Departement> trouverDepartementsParEmploye(Employee employee) {
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            TypedQuery<Departement> query = session.createQuery("SELECT d FROM Departement d WHERE :employee MEMBER OF d.employes", Departement.class);
            query.setParameter("employee", employee);
            List<Departement> departements = query.getResultList();
            session.getTransaction().commit();
            return departements;
        }
    }

    @Override
    public List<Departement> getAllDepartements() {
        try(Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("SELECT d from Departement d");
            return query.getResultList();
        }
    }
    @Override
    public void supprimerDepartement(Long id) {
        try (Session session = sessionFactory.openSession()){
            session.getTransaction().begin();

            Departement departement = session.get(Departement.class, id);

            if (departement != null) {
                // Supprimer tous les employés associés au département
                for (Employee employee : departement.getEmployes()) {
                    // Retirer le département de l'employé
                    employee.setDepartement(null);
                    session.delete(employee);
                }

                // Supprimer le département lui-même
                session.delete(departement);
                System.out.println("Suppression réussie du département.");
            } else {
                System.out.println("Échec de la suppression du département avec l'ID : " + id + ". Le département n'existe pas.");
            }

            session.getTransaction().commit();
        }
    }
}
