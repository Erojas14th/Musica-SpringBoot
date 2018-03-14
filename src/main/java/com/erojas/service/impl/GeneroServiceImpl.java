package com.erojas.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erojas.model.Genero;
import com.erojas.repository.GeneroRepository;
import com.erojas.service.GeneroService;

@Service
public class GeneroServiceImpl implements GeneroService {

	@Autowired
	private GeneroRepository dao;
	
	@Override
	public List<Genero> findAll() throws Exception {
		return dao.findAll();
	}

	@Override
	public Optional<Genero> findById(Integer codigo) throws Exception {
		return dao.findById(codigo);
	}

	@Override
	public void deleteById(Integer codigo) throws Exception {
		 dao.deleteById(codigo);
		
	}

	@Override
	public void save(Genero objeto) throws Exception {
		dao.save(objeto);
		
	}

	@Override
	public void update(Genero objeto) throws Exception {
		 if(objeto.getId()>0 &&  objeto.getId()!=null) {
			   dao.save(objeto);
			   
		   }
		
	}

}
