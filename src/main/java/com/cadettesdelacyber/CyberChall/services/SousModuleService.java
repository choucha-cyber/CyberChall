package com.cadettesdelacyber.CyberChall.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cadettesdelacyber.CyberChall.models.SousModule;
import com.cadettesdelacyber.CyberChall.models.Module;
import com.cadettesdelacyber.CyberChall.repositories.SousModuleRepository;

@Service
public class SousModuleService {

    @Autowired
    private SousModuleRepository sousModuleRepository;

    public SousModule saveSousModule(SousModule sousModule) {
        return sousModuleRepository.save(sousModule);
    }
    
    public boolean existsByTitreAndModule(String titre, Module module) {
        return sousModuleRepository.existsByTitreAndModule_Id(titre, module.getId());
    }
    
    public List<SousModule> findSousModulesByIds(List<Long> ids) {
        return sousModuleRepository.findAllById(ids);
    }


}