package com.cadettesdelacyber.CyberChall.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cadettesdelacyber.CyberChall.models.Professeur;

@Repository
public interface ProfesseurRepository extends JpaRepository<Professeur, Long> {}

