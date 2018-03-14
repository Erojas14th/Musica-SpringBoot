package com.erojas.service;

import java.util.List;

import com.erojas.model.Cancion;

public interface CancionService extends Service<Cancion>{
	List<Cancion> getAllCancionByAlbumID(Integer idAlbum) throws Exception;
}
