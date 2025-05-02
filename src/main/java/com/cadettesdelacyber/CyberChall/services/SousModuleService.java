package com.cadettesdelacyber.CyberChall.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cadettesdelacyber.CyberChall.models.SousModule;
import com.cadettesdelacyber.CyberChall.repositories.SousModuleRepository;

@Service
public class SousModuleService {

    @Autowired
    private SousModuleRepository sousModuleRepository;

    public SousModule saveSousModule(SousModule sousModule) {
        return sousModuleRepository.save(sousModule);
    }

}