package org.example.dao;

import org.example.entities.Departement;
import org.example.entities.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> trouverEmployesParDepartement(Departement departement);
    Employee ajouterEmploye(Employee employee);
    void supprimerEmploye(Long id);
    List<Employee> getAllEmployees();
}
