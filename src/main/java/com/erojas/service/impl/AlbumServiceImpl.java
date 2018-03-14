package com.erojas.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erojas.model.Album;
import com.erojas.repository.AlbumRepository;
import com.erojas.service.AlbumService;

@Service
public class AlbumServiceImpl implements AlbumService{

	@Autowired
	private AlbumRepository dao;
	
	@Override
	public List<Album> findAll() throws Exception {

		return dao.findAll();
	}

	@Override
	public Optional<Album> findById(Integer codigo) throws Exception {
		return dao.findById(codigo);
	}

	@Override
	public void deleteById(Integer codigo) throws Exception {
		  dao.deleteById(codigo);
		
	}

	@Override
	public void save(Album objeto) throws Exception {
		dao.save(objeto);
		
	}

	@Override
	public void update(Album objeto) throws Exception {
		 if(objeto.getId()>0 &&  objeto.getId()!=null) {
			   dao.save(objeto);
			   
		   }
		
	}

	@Override
	public List<Album> getAllAlbumByArtistaID(Integer idArtista) throws Exception {
		
		return dao.getAllAlbumByArtistaID(idArtista);
	}

	@Override
	public List<Album> getAllAlbumByGeneroID(Integer idGenero) throws Exception {
		// TODO Auto-generated method stub
		return dao.getAllAlbumByGeneroID(idGenero);
	}

}
