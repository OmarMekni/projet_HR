package com.example.projet_hr.controller;

import com.example.projet_hr.model.Departement;
import com.example.projet_hr.model.Emploi;
import com.example.projet_hr.model.Employe;
import com.example.projet_hr.model.EmployeDTO;
import com.example.projet_hr.service.DepartementService;
import com.example.projet_hr.service.EmploiService;
import com.example.projet_hr.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;
import java.util.stream.Collectors;
@Controller
public class EmployeController {
    /**
     * @Autowired permet de créer un nouvel objet sans utilisation new
     */
    @Autowired
    private EmployeService employeService;
    @Autowired
    private DepartementService departementService;
    @Autowired
    private EmploiService emploiService;

    @GetMapping("/formEmploye")
    public String formEmploye(Model model) {
        List<Emploi> emplois = emploiService.findAll();
        List<Departement> departements = departementService.findAll();
        List<Employe> employes = employeService.findAll();
        Employe employe = new Employe();
        model.addAttribute("emplois", emplois);
        model.addAttribute("departements", departements);
        model.addAttribute("employe", employe);
        model.addAttribute("employes", employes);
        return "formEmploye";
    }
    @PostMapping("/addEmploye")
    public String saveEmployeWithDepartment(@ModelAttribute("employe") Employe employe, RedirectAttributes redirectAttributes) {
        try {
            if (employeService.isEmailAlreadyInUse(employe.getEmail())) {
                redirectAttributes.addFlashAttribute("error", "Erreur: L'adresse e-mail est déjà utilisée!");
                return "redirect:/formEmploye";
            }
            Emploi emploi = emploiService.findEmploiById(employe.getEmploi().getId());
            if (emploi != null) {
                employe.setDepartement(emploi.getDepartement());
                employeService.saveEmploye(employe);
                redirectAttributes.addFlashAttribute("success", "Employé enregistré avec succès!");
            } else {
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Une erreur s'est produite lors de la sauvegarde de l'employé.");
        }
        return "redirect:/formEmploye";
    }
    @GetMapping("/editEmploye/{id}")
    public String editEmploye(@PathVariable int id, Model model) {
        Employe employe = employeService.findEmployeById(id);
        model.addAttribute("employe", employe);
        return "editEmploye";
    }

    @PostMapping("/updateEmploye")
    public String updateEmploye(@ModelAttribute("employe") Employe updatedEmploye, RedirectAttributes redirectAttributes) {
        Employe existingEmploye = employeService.findEmployeById(updatedEmploye.getId());
        if (existingEmploye != null) {
            existingEmploye.setPrenom(updatedEmploye.getPrenom());
            existingEmploye.setNom(updatedEmploye.getNom());
            existingEmploye.setEmail(updatedEmploye.getEmail());
            existingEmploye.setSalaire(updatedEmploye.getSalaire());
            employeService.saveEmploye(existingEmploye);
            redirectAttributes.addFlashAttribute("employeeUpdated", true);
            return "redirect:/employes";
        } else {
            return "redirect:/employes";
        }
    }
    @GetMapping("/employes")
    public String getAllEmployes(Model model) {
        List<Employe> employes = employeService.findAll();
        List<EmployeDTO> employeDTOs = employes.stream().map(this::convertToDTO).collect(Collectors.toList());
        model.addAttribute("employes", employeDTOs);
        return "employes";
    }

    public EmployeDTO convertToDTO(Employe employe) {
        EmployeDTO dto = new EmployeDTO();
        dto.setId(employe.getId());
        dto.setPrenom(employe.getPrenom());
        dto.setNom(employe.getNom());
        dto.setEmail(employe.getEmail());
        dto.setSalaire(employe.getSalaire());
        dto.setDateEmbauche(employe.getDateEmbauche());
        dto.setTitreEmploi(employe.getEmploi().getTitreEmploi());
        dto.setNomDepartement(employe.getDepartement().getNomDepartement());
        return dto;
    }

    /**
     * Supprimer un employe en utilisant son Id
     * @param id
     * @return
     */
    @DeleteMapping("/deleteEmploye/{id}")
    public ResponseEntity<Void> deleteEmployeById(@PathVariable int id) {
        boolean deleted = employeService.deleteEmployeById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
