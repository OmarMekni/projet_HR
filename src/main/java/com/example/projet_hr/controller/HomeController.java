package com.example.projet_hr.controller;

import com.example.projet_hr.model.Employe;
import com.example.projet_hr.model.EmployeDTO;
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

    @GetMapping("/home")
    public String getAllEmployes(Model model) {
        List<Employe> employes = employeService.findAll();
        List<EmployeDTO> employeDTOs = employes.stream().map(this::convertToDTO).collect(Collectors.toList());
//        List<EmployeDTO> employeDTOs = employes.stream().map(EmployeController::convertToDTO).collect(Collectors.toList());
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
}
