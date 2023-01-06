package com.user.userapi.services;

import java.util.List;
import java.util.NoSuchElementException;

import com.user.userapi.model.Utilisateur;


public interface UserService {
	
	public List<Utilisateur> findAll();
	
	public int register(Utilisateur user);
	
	public Utilisateur findById(int iduser) throws NoSuchElementException;

	


	
		


	
	

}
