package com.user.userapi.service.implementation;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.userapi.dao.UserRepository;
import com.user.userapi.model.Utilisateur;
import com.user.userapi.services.UserService;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	
	private UserRepository userRepo;
	
	@Override //reference a la classe parent qui est l'interface UserService
	public List<Utilisateur> findAll(){
		
		//List<User> liste = new ArrayList<User>();
		//userRepo.findAll().forEach(liste::add);
		//return liste;
		
		return userRepo.findAll();
	}

}
