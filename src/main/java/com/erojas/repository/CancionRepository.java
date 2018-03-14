package com.erojas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.erojas.model.Cancion;

@Repository
public interface CancionRepository extends JpaRepository<Cancion, Integer>{

	@Query("select new com.erojas.model.Cancion(c.id , c.nombre , c.pista , c.estrellas) from cancion c "
			+ " where c.album.id= :idAlbum ")

	List<Cancion> getAllCancionByAlbumID(@Param("idAlbum") Integer idAlbum) throws Exception;
}
