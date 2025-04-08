package com.cadettesdelacyber.CyberChall.models;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Session {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;

	  private String nom;
	  private LocalDate date;

	  // Profs ou cadettes responsables
	  @ManyToOne
	  private Admin responsable;

	  @ManyToMany
	  private List<Eleve> eleves;

	  @ManyToMany
	  private List<Challenge> challenges;

}
