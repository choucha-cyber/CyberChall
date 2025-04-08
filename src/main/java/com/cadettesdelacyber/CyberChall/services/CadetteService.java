package com.cadettesdelacyber.CyberChall.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cadettesdelacyber.CyberChall.models.Cadette;
import com.cadettesdelacyber.CyberChall.repositories.CadetteRepository;

@Service
public class CadetteService {
    @Autowired
    private CadetteRepository cadetteRepository;

    public List<Cadette> getAllCadettes() {
        return cadetteRepository.findAll();
    }

    public Cadette getCadette(Long id) {
        return cadetteRepository.findById(id).orElse(null);
    }

    public Cadette saveCadette(Cadette cadette) {
        return cadetteRepository.save(cadette);
    }

    public void deleteCadette(Long id) {
        cadetteRepository.deleteById(id);
    }
}

