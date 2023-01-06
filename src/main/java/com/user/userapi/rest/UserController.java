package com.user.userapi.rest;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.user.userapi.model.Utilisateur;
import com.user.userapi.services.UserService;



@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired //On couple la couche service et la couche implementation
	private UserService userService ;
	
	
	
	
	public int calculateAge(LocalDate birthDate, LocalDate currentDate) {
			    
			    return Period.between(birthDate, currentDate).getYears();
			}
	
	public LocalDate convertToLocalDateViaMilisecond(Date dateToConvert) {
	    return Instant.ofEpochMilli(dateToConvert.getTime())
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}
	


	@GetMapping  //Display user information
	public List<Utilisateur> findAll(){
		return userService.findAll() ; 
	}
	
	public Utilisateur findbyId(int iduser) {
		return userService.findById(iduser);
	}
	
	@PostMapping  //registrate a new user
	@ResponseStatus(code = HttpStatus.CREATED)
	public int register(@RequestBody Utilisateur user){ //on va creer un identifiant de user en base du coup on va retrourner un id
		if(!user.getCountry().equalsIgnoreCase("France"))  {
		return Response.SC_BAD_REQUEST ;
		}
		int age = calculateAge(convertToLocalDateViaMilisecond(user.getBirthdate()), LocalDate.now());
		System.out.println("######################"+age);
		if(age<18) {
			return Response.SC_BAD_REQUEST ;
			
		}	
				
		userService.register(user);
		return Response.SC_CREATED ;
		
		
	}
}

 