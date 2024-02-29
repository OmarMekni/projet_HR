package com.example.projet_hr.controller;


import com.example.projet_hr.model.Departement;
import com.example.projet_hr.model.Emploi;
import com.example.projet_hr.model.EmploiDTO;
import com.example.projet_hr.service.DepartementService;
import com.example.projet_hr.service.EmploiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;
import java.util.stream.Collectors;
@Controller
public class EmploiController {
    @Autowired
    private DepartementService departementService;
    @Autowired
    private EmploiService emploiService;
    @GetMapping("/formEmploi")
    public String formEmploi(Model model) {

        List<Emploi> emplois = emploiService.findAll();

        List<Departement> departements = departementService.findAll();

// Emploi emploi = new Emploi();

        model.addAttribute("emplois", emplois);

        model.addAttribute("departements", departements);
        return "formEmploi";

    }
    @ModelAttribute("departementNames")
    public List<Departement> getAllDepartementNames() {

        List<Departement> departements = departementService.findAll();
        return departements;

    }

    @PostMapping("/addEmploi")
    public String saveJobWithDepartment(@ModelAttribute("emploi") Emploi emploi, Model model,

                                        RedirectAttributes redirectAttributes) {

        Departement departement = emploi.getDepartement();
        if (departement == null || !departementService.departmentExists(departement.getId())) {

            model.addAttribute("error", "ID du departement n'est pas valide ou manquant.");
            return "formEmploi";

        }
        try {

            emploiService.saveEmploi(emploi);

            redirectAttributes.addFlashAttribute("success", "Travail enregistré avec succès!");

        } catch (DataIntegrityViolationException e) {

            redirectAttributes.addFlashAttribute("error", "Le nom du Travail existe déjà.");

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute("error", "Erreur: le Travail n'a pas été enregistré.");

        }
        return "redirect:/formEmploi";

    }
    /**
     * Supprimer un job par son Id
     * @param id
     * @return
     */
    @DeleteMapping("/emploi/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable int id)

    {
        boolean deleted = emploiService.deleteEmploiById(id);
        if (deleted)

        {
            return ResponseEntity.ok("L'emploi avec id " + id + " est supprimé");

        }
        else

        {
            return ResponseEntity.badRequest().body("L'emploi avec id " + id + " n'existe pas");

        }

    }
    private EmploiDTO convertToDTO(Emploi emploi) {

        EmploiDTO dto = new EmploiDTO();

        dto.setId(emploi.getId());

        dto.setTitreEmploi(emploi.getTitreEmploi());

        dto.setNomDepartement(emploi.getDepartement().getNomDepartement());
        return dto;

    }

}

