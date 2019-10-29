package com.onebox.sgomez.railroad.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.onebox.sgomez.railroad.models.entity.Town;

public interface TownDao extends CrudRepository<Town, String>{
	
}
