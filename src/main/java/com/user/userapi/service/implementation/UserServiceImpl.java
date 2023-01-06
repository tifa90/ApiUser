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
	
	
	@Override 
	public List<Utilisateur> findAll(){
		return userRepo.findAll();
	}
	
	@Override
	public Utilisateur findById(int iduser) {
		return userRepo.findById(iduser).get();
		//return null;
	}
	
	

	@Override
	public int register(Utilisateur user) {
		return userRepo.save(user).getIduser();
	}




}
