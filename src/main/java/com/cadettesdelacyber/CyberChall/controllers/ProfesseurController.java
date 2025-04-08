package com.cadettesdelacyber.CyberChall.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cadettesdelacyber.CyberChall.models.Professeur;
import com.cadettesdelacyber.CyberChall.services.ProfesseurService;

@RestController
@RequestMapping("/professeurs")
public class ProfesseurController {
    @Autowired
    private ProfesseurService professeurService;

    @GetMapping
    public List<Professeur> getAll() {
        return professeurService.getAllProfesseurs();
    }

    @GetMapping("/{id}")
    public Professeur getOne(@PathVariable Long id) {
        return professeurService.getProfesseur(id);
    }

    @PostMapping
    public Professeur create(@RequestBody Professeur professeur) {
        return professeurService.saveProfesseur(professeur);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        professeurService.deleteProfesseur(id);
    }
}

