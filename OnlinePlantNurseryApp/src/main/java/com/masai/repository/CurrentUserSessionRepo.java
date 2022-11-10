package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.CurrentUserSession;

public interface CurrentUserSessionRepo extends JpaRepository<CurrentUserSession, Integer> {
	
	public CurrentUserSession findByKey(String key);

}
