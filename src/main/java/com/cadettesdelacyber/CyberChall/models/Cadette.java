package com.cadettesdelacyber.CyberChall.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CADETTE")
	public class Cadette extends Admin {
	    private String ecoleOrigine;

}
