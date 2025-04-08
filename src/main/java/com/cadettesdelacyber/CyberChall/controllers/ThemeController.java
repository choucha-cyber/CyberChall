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

import com.cadettesdelacyber.CyberChall.models.Theme;
import com.cadettesdelacyber.CyberChall.services.ThemeService;

@RestController
@RequestMapping("/themes")
public class ThemeController {
    @Autowired
    private ThemeService themeService;

    @GetMapping
    public List<Theme> getAll() {
        return themeService.getAllThemes();
    }

    @GetMapping("/{id}")
    public Theme getOne(@PathVariable Long id) {
        return themeService.getTheme(id);
    }

    @PostMapping
    public Theme create(@RequestBody Theme theme) {
        return themeService.saveTheme(theme);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        themeService.deleteTheme(id);
    }
}

