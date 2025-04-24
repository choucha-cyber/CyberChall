package com.cadettesdelacyber.CyberChall.services;


import com.cadettesdelacyber.CyberChall.models.User;

public interface UserService {
	
    User findByUsername(String username);
    User findByUsernameAndRole(String username, String role);
    void createUser(User user);

}