package com.erojas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.erojas.model.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer>{

	@Query("select new com.erojas.model.Album(a.id , a.nombre , a.urlFoto , a.comentario , a.anho) from album a "
			+ " where a.artista.id = :idArtista")
	List<Album> getAllAlbumByArtistaID(@Param("idArtista") Integer idArtista) throws Exception;
	
	@Query("select new com.erojas.model.Album(a.id , a.nombre , a.urlFoto , a.comentario , a.anho) from album a "
			+ " where a.genero.id = :idGenero")
	List<Album> getAllAlbumByGeneroID(@Param("idGenero") Integer idGenero) throws Exception;
	
}
