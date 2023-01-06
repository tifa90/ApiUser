package com.user.userapi.rest;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@Autowired 
	private UserService userService ;
	
	
	
	
	public int calculateAge(LocalDate birthDate, LocalDate currentDate) {
			    
			    return Period.between(birthDate, currentDate).getYears();
			}
	
	public LocalDate LocalDateToMilisecond(Date dateToConvert) {
	    return Instant.ofEpochMilli(dateToConvert.getTime())
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}
	


	@GetMapping  
	public List<Utilisateur> findAll(){
		return userService.findAll() ; 
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Utilisateur> findbyId(@PathVariable int id) {
		try {
		if(userService.findById(id) == null) {
			return new ResponseEntity ("", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(userService.findById(id), HttpStatus.OK);
		}catch(NoSuchElementException e) {
		return new ResponseEntity ("User with id:"+id +" Not Found", HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping  
	@ResponseStatus(code = HttpStatus.CREATED)
	public int register(@RequestBody Utilisateur user){ 
		if(!user.getCountry().equalsIgnoreCase("France"))  {
		return Response.SC_BAD_REQUEST ;
		}
		int age = calculateAge(LocalDateToMilisecond(user.getBirthdate()), LocalDate.now());
		if(age<18) {
			return Response.SC_BAD_REQUEST ;
			
		}	
				
		userService.register(user);
		return Response.SC_CREATED ;
		
		
	}
}

 