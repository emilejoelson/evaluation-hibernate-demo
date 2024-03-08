package org.example;

import org.example.dao.DepartementDAOImpl;
import org.example.dao.EmployeeDAOImpl;
import org.example.entities.Departement;
import org.example.entities.Employee;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        DepartementDAOImpl departementDAO = new DepartementDAOImpl();
        //Creation de departement
        Departement d1 = new Departement("IT", new ArrayList<>());
        Employee e1 = new Employee("ANDRIA", "Emile", "emile.doe@example.com",d1);
        Employee e2 = new Employee("RAHAN", "Joelson", "joelson.doe@example.com",d1);
        Employee e3 = new Employee("Fety", "Michel", "michel.doe@example.com",d1);
        Employee e4 = new Employee("Rare", "Micka", "micka.doe@example.com",d1);
        Employee e5 = new Employee("ANDRIA", "Mima", "andria.doe@example.com",d1);

        departementDAO.ajouterDepartement(d1);
        employeeDAO.ajouterEmploye(e1);
        employeeDAO.ajouterEmploye(e2);
        employeeDAO.ajouterEmploye(e3);
        employeeDAO.ajouterEmploye(e4);
        employeeDAO.ajouterEmploye(e5);

        Departement d2 = new Departement("Informatique", new ArrayList<>());
        Employee e11 = new Employee("Ravo", "Odilon", "ravo.doe@example.com",d2);
        Employee e21 = new Employee("Mima", "ezra", "ezra.doe@example.com",d2);
        Employee e31 = new Employee("Qazdar", "Macka", "macka.doe@example.com",d2);
        Employee e41 = new Employee("Flech", "Dirka", "dirka.doe@example.com",d2);
        Employee e51 = new Employee("Azra", "Mikael", "mickael.doe@example.com",d2);

        departementDAO.ajouterDepartement(d2);
        employeeDAO.ajouterEmploye(e11);
        employeeDAO.ajouterEmploye(e21);
        employeeDAO.ajouterEmploye(e31);
        employeeDAO.ajouterEmploye(e41);
        employeeDAO.ajouterEmploye(e51);

        System.out.println("\n\n:::::::::::::::::::Rechercher tous les employés d'un département spécifique.\n:::::::::::::::::::::::::::::");
        List<Employee> employeeList = employeeDAO.trouverEmployesParDepartement(d1);
        for (Employee empl : employeeList){
            System.out.println(empl);
        }


        System.out.println("\n\n:::::::::::::::::::Rechercher tous les départements auxquels appartient un employé spécifiquee.\n:::::::::::::::::::::::::::::");
        List<Departement> depList = departementDAO.trouverDepartementsParEmploye(e11);
        for (Departement dep : depList){
            System.out.println(dep);
        }
        System.out.println("\n\n:::::::::::::::::::Supprimer un employé.\n:::::::::::::::::::::::::::::");
          employeeDAO.supprimerEmploye(4L);

        System.out.println("\n\n:::::::::::::::::::Tous les employes apres suppression.\n:::::::::::::::::::::::::::::");
        List<Employee> allEmployees = employeeDAO.getAllEmployees();
         for(Employee employee : allEmployees){
             System.out.println(employee);
         }
        System.out.println("\n\n:::::::::::::::::::Supprimer un departement.\n:::::::::::::::::::::::::::::");
        employeeDAO.supprimerEmploye(1L);
        System.out.println("\n\n:::::::::::::::::::Tous les departements apres suppression.\n:::::::::::::::::::::::::::::");
       List<Departement> departements =  departementDAO.getAllDepartements();
       for(Departement dep : departements){
           System.out.println(dep);
       }
    }
}