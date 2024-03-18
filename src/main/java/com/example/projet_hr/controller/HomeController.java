package com.example.projet_hr.controller;

import com.example.projet_hr.model.*;
import com.example.projet_hr.service.DepartementService;
import com.example.projet_hr.service.EmploiService;
import com.example.projet_hr.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {


    @Autowired
    private EmployeService employeService;

    @Autowired
    private DepartementService departementService;

    @Autowired
    private EmploiService emploiService;

    @GetMapping("/home")
    public String getAllEmployes(Model model) {
        List<Employe> employes = employeService.findAll();
        List<EmployeDTO> employeDTOs = employes.stream().map(this::convertToDTO).collect(Collectors.toList());
        model.addAttribute("employes", employeDTOs);

        return "home"; // fichier HTML à afficher
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


    @GetMapping("/allDepartements")
    public String getAllDepartements(Model model){
        List<Departement> departements = departementService.findAll();
        List<DepartementDTO> departementDTOs = departements.stream().map(this::convertToDTO).collect(Collectors.toList());
        model.addAttribute("departements", departementDTOs);
        return "departements";
    }



    private DepartementDTO convertToDTO(Departement departement) {
        DepartementDTO dto = new DepartementDTO();
        dto.setId(departement.getId());
        dto.setNomDepartement(departement.getNomDepartement());
        return dto;
    }



    @GetMapping("/allemplois")
    public String getAllEmplois(Model model){
        List<Emploi> emplois = emploiService.findAll();
        List<EmploiDTO> emploiDTOs = emplois.stream().map(this::convertToDTO).collect(Collectors.toList());
        model.addAttribute("emplois", emploiDTOs);
        return "emplois";
    }


    private EmploiDTO convertToDTO(Emploi emploi) {
        EmploiDTO dto = new EmploiDTO();
        dto.setId(emploi.getId());
        dto.setTitreEmploi(emploi.getTitreEmploi());
        dto.setNomDepartement(emploi.getDepartement().getNomDepartement());
        return dto;

    }

}
