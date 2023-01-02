package com.user.userapi.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.userapi.model.Utilisateur;
import com.user.userapi.services.UserService;



@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired //On couple la couche service et la couche implementation
	private UserService userService ;
	
	@GetMapping  //recuperer les donnees au sein de la base et les afficher
	public List<Utilisateur> findAll(){
		return userService.findAll() ; 
	
	}
	

}

 