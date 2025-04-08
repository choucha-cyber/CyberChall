package com.cadettesdelacyber.CyberChall.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cadettesdelacyber.CyberChall.models.Theme;
import com.cadettesdelacyber.CyberChall.repositories.ThemeRepository;

@Service
public class ThemeService {
	@Autowired
    private ThemeRepository themeRepository;

    public List<Theme> getAllThemes() {
        return themeRepository.findAll();
    }

    public Theme getTheme(Long id) {
        return themeRepository.findById(id).orElse(null);
    }

    public Theme saveTheme(Theme theme) {
        return themeRepository.save(theme);
    }

    public void deleteTheme(Long id) {
        themeRepository.deleteById(id);
    }
}
