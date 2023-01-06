package com.user.userapi.services;

import java.util.List;

import com.user.userapi.model.Utilisateur;


public interface UserService {
	
	public List<Utilisateur> findAll();
	
	public int register(Utilisateur user);
	
	public Utilisateur findById(int iduser);

	


	
		


	
	

}
