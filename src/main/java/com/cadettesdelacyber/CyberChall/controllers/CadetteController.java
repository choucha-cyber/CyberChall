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

import com.cadettesdelacyber.CyberChall.models.Cadette;
import com.cadettesdelacyber.CyberChall.services.CadetteService;

@RestController
@RequestMapping("/cadettes")
public class CadetteController {
    @Autowired
    private CadetteService cadetteService;

    @GetMapping
    public List<Cadette> getAll() {
        return cadetteService.getAllCadettes();
    }

    @GetMapping("/{id}")
    public Cadette getOne(@PathVariable Long id) {
        return cadetteService.getCadette(id);
    }

    @PostMapping
    public Cadette create(@RequestBody Cadette cadette) {
        return cadetteService.saveCadette(cadette);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        cadetteService.deleteCadette(id);
    }
}

