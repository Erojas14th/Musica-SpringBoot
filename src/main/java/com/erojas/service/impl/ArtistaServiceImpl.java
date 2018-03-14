package com.erojas.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erojas.model.Artista;
import com.erojas.repository.ArtistaRepository;
import com.erojas.service.ArtistaService;

@Service
public class ArtistaServiceImpl implements ArtistaService{

	@Autowired
	private ArtistaRepository dao;
	
	@Override
	public List<Artista> findAll() throws Exception {
		
		return dao.findAll();
	}

	@Override
	public Optional<Artista> findById(Integer codigo) throws Exception {
		
		return dao.findById(codigo);
	}

	@Override
	public void deleteById(Integer codigo) throws Exception {
		  dao.deleteById(codigo);
		
	}

	@Override
	public void save(Artista objeto) throws Exception {
		dao.save(objeto);
		
	}

	@Override
	public void update(Artista objeto) throws Exception {
		 if(objeto.getId()>0 &&  objeto.getId()!=null) {
			   dao.save(objeto);
			   
		   }
			
		
	}

}
