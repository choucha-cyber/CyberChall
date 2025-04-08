package com.cadettesdelacyber.CyberChall.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cadettesdelacyber.CyberChall.models.Eleve;

@Repository
public interface EleveRepository extends JpaRepository<Eleve, Long> {
    List<Eleve> findByNomContaining(String nom);
}

