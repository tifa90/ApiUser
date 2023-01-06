package com.user.userapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.userapi.model.Utilisateur;

public interface UserRepository extends JpaRepository<Utilisateur, Integer> {

	

}
