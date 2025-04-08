package com.cadettesdelacyber.CyberChall.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Theme {
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private Long id;

	   private String titre;
	   private String description;

	   @OneToMany(mappedBy = "theme")
	   private List<Challenge> challenges;

}
