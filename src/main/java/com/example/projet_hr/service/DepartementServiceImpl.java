package com.example.projet_hr.service;
import com.example.projet_hr.exception.NotFoundException;
import com.example.projet_hr.model.Departement;
import com.example.projet_hr.repository.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class DepartementServiceImpl implements DepartementService {
    /**
     * @Autowired permet de créer un nouvel objet sans utilisation new
     */
    @Autowired
    private DepartementRepository departementRepository;

    /**
     * Méthode saveDepartement pour enregistrer un département dans la base de donnée
     *
     * @param departement
     * @return
     */
    @Override
    public Departement saveDepartement(Departement departement) {
        return departementRepository.save(departement);
    }
    /**
     * Méthode findDepartementById pour chercher un département par son Id
     *
     * @param id
     * @return
     */
    @Override
    public Departement findDepartementById(int id) {
        Departement departement = null;
        Optional<Departement> optionalDepartement = departementRepository.findById(id);
        if(optionalDepartement.isPresent())
        {
            departement = optionalDepartement.get();
        }
        else
        {
            throw new NotFoundException("Departement id " + id + " n'existe pas.");
        }
        return departement;
    }
    /**
     * Méthode deleteDepartementById pour supprimer un département de la liste des départements par son Id
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteDepartementById(int id) {
        Departement departement = null;
        Optional<Departement> optionalDepartement = departementRepository.findById(id);
        if(optionalDepartement.isPresent())
        {
            departementRepository.deleteById(id);
            return true;
        }
        else
        {
            return false;
        }
    }
    /**
     * Méthode updateDepartementNameById pour mettre à jour le nom d'un département en utilisant son Id
     *
     * @param id
     * @param newNomDepartement
     * @return
     */
    @Override
    public Departement updateDepartementNameById(int id, String newNomDepartement) {
        Departement departement = findDepartementById(id);
        departement.setNomDepartement(newNomDepartement);
        return departementRepository.save(departement);
    }
    /**
     * Méthode pour findAll chercher tous les départements de la liste
     *
     * @return
     */
    @Override
    public List<Departement> findAll() {
        return departementRepository.findAll();
    }
    /**
     * Méthode findDepartementByName pour chercher un département par son nom
     *
     * @param name
     * @return
     */
    @Override
    public Departement findDepartementByName(String name) {
        Optional<Departement> optionalDepartement = departementRepository.findByNomDepartement(name);
        if (optionalDepartement.isPresent())
        {
            return optionalDepartement.get();
        }
        else
        {
            throw new NotFoundException("Le département avec le nom " + name + " n'existe pas.");
        }
    }
    /**
     * @param departementId
     * @return
     */
    @Override
    public boolean departmentExists(int departementId) {
        return departementRepository.existsById(departementId);
    }
}
