package com.erojas.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erojas.model.Cancion;
import com.erojas.repository.CancionRepository;
import com.erojas.service.CancionService;

@Service
public class CancionServiceImpl implements CancionService{

	@Autowired
	private CancionRepository dao;
	
	@Override
	public List<Cancion> findAll() throws Exception {
		return dao.findAll();
	}

	@Override
	public Optional<Cancion> findById(Integer codigo) throws Exception {
		return dao.findById(codigo);
	}

	@Override
	public void deleteById(Integer codigo) throws Exception {
		 dao.deleteById(codigo);
		
	}

	@Override
	public void save(Cancion objeto) throws Exception {
		dao.save(objeto);
		
	}

	@Override
	public void update(Cancion objeto) throws Exception {
		if(objeto.getId()>0 &&  objeto.getId()!=null) {
			   dao.save(objeto);
			   
		   }
		
	}

	@Override
	public List<Cancion> getAllCancionByAlbumID(Integer idAlbum) throws Exception {
		
		return dao.getAllCancionByAlbumID( idAlbum);
	}

}
