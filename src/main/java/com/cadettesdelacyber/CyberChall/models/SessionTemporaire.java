package com.cadettesdelacyber.CyberChall.models;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import jakarta.persistence.*;


@Entity
public class SessionTemporaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String token;

    private LocalDateTime dateCreation;
    private LocalDateTime dateExpiration;
    
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin; // Relation avec l'admin qui a créé la session

    @ManyToMany
    @JoinTable(
        name = "session_module",
        joinColumns = @JoinColumn(name = "session_id"),
        inverseJoinColumns = @JoinColumn(name = "module_id")
    )
    private List<Module> modules; 
    
    @Transient
    private String qrCodeBase64;
 
    
	public SessionTemporaire() {
	
	}

	public SessionTemporaire(String token, LocalDateTime dateCreation, LocalDateTime dateExpiration, Admin admin,
			List<Module> modules) {
		super();
		this.token = token;
		this.dateCreation = dateCreation;
		this.dateExpiration = dateExpiration;
		this.admin = admin;
		this.modules = modules;
	}

	public SessionTemporaire(Long id, String token, LocalDateTime dateCreation, LocalDateTime dateExpiration,
			Admin admin, List<Module> modules) {
		super();
		this.id = id;
		this.token = token;
		this.dateCreation = dateCreation;
		this.dateExpiration = dateExpiration;
		this.admin = admin;
		this.modules = modules;
	}

	public SessionTemporaire(Long id, String token, LocalDateTime dateCreation, LocalDateTime dateExpiration,
			Admin admin, List<Module> modules, String qrCodeBase64) {
		super();
		this.id = id;
		this.token = token;
		this.dateCreation = dateCreation;
		this.dateExpiration = dateExpiration;
		this.admin = admin;
		this.modules = modules;
		this.qrCodeBase64 = qrCodeBase64;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	   public String getQrCodeBase64() {
	        return qrCodeBase64;
	    }

	    public void setQrCodeBase64(String qrCodeBase64) {
	        this.qrCodeBase64 = qrCodeBase64;
	    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(LocalDateTime dateCreation) {
		this.dateCreation = dateCreation;
	}

	public LocalDateTime getDateExpiration() {
		return dateExpiration;
	}

	public void setDateExpiration(LocalDateTime dateExpiration) {
		this.dateExpiration = dateExpiration;
	}

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
 
}