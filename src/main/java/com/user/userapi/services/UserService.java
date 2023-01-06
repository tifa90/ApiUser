package com.user.userapi.services;

import java.util.List;

import com.user.userapi.model.Utilisateur;


public interface UserService {
	
	public List<Utilisateur> findAll();
	
	public Utilisateur findById(int iduser);

	public int register(Utilisateur user);

	
		


	
	

}
