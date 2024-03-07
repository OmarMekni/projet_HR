package com.example.projet_hr.controller;

import com.example.projet_hr.exception.NotFoundException;
import com.example.projet_hr.model.Departement;
import com.example.projet_hr.model.DepartementDTO;
import com.example.projet_hr.service.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class DepartementController {
    /**
     * @Autowired permet de créer un nouvel objet sans utilisation new
     */
    @Autowired
    private DepartementService departementService;

    /**
     * Pour afficher la page html formDepartement.html
     * @return formDepartement
     */
    @GetMapping("/formDepartement")
    public String formDepartement(){
        return "formDepartement";
    }


    @PostMapping("/addDepartement")
    public String saveDepartement(@ModelAttribute("departement") Departement departement, RedirectAttributes redirectAttributes) {
        try {
            departementService.saveDepartement(departement);
            redirectAttributes.addFlashAttribute("success", "Département enregistré avec succès!");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", "Le nom du département existe déjà.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur: le Département n'a pas été enregistré.");
        }
        return "redirect:/formDepartement";
    }


    /**
     * Supprimer un departement
     * @param id
     * @return
     */
    @DeleteMapping("/departement/{id}")
    public ResponseEntity<String> deleteDepartementById(@PathVariable int id)
    {
        boolean deleted = departementService.deleteDepartementById(id);
        if (deleted)
        {
            return  ResponseEntity.ok("Le département avec l'Id " + id + " est supprimé.");
        }
        else
        {
            return ResponseEntity.badRequest().body("Le département avec l'Id " + id + " n'existe pas.");
        }
    }

    /**
     * Modifier le nom d'un département
     */
    @PutMapping("/updateName/{id}/{newName}")
    public ResponseEntity<String> updateDepartementNameById(@PathVariable int id, @PathVariable String newName)
    {
        try {
            Departement departement = departementService.updateDepartementNameById(id, newName);
            return ResponseEntity.ok("Le département avec l'Id " + id + " a bien été mis à jour.");
        }
        catch (NotFoundException ex)
        {
            return ResponseEntity.badRequest().body("Le département avec l'Id " + id + " n'existe pas.");
        }
    }


    /**
     * Afficher la liste de tous les départements
     * @return
     */
    @GetMapping("/departements")
    public List<DepartementDTO> getAllDepartements(){
        List<Departement> departements =departementService.findAll();
        List<DepartementDTO> departementDTOs =
                departements.stream().map(this::convertToDTO).collect(Collectors.toList());
        return departementDTOs;
    }



    private DepartementDTO convertToDTO(Departement departement) {
        DepartementDTO dto = new DepartementDTO();
        dto.setId(departement.getId());
        dto.setNomDepartement(departement.getNomDepartement());
        return dto;
    }

}
