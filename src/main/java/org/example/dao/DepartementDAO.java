package org.example.dao;

import org.example.entities.Departement;
import org.example.entities.Employee;

import java.util.List;

public interface DepartementDAO {

    Departement ajouterDepartement(Departement departement);
    List<Departement> trouverDepartementsParEmploye(Employee employee);
    void supprimerDepartement(Long id);
    List<Departement> getAllDepartements();
}
