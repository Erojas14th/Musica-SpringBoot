package com.erojas.service;

import java.util.List;
import java.util.Optional;


public interface Service<T> {


	List<T> findAll() throws Exception;
	Optional<T> findById(Integer codigo) throws Exception;
	void deleteById(Integer codigo) throws Exception;
	void save(T objeto) throws Exception;
	void update(T objeto) throws Exception;
	
}
