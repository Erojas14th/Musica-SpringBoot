package com.erojas.service;

import java.util.List;

import com.erojas.model.Album;

public interface AlbumService extends Service<Album>{
	List<Album> getAllAlbumByArtistaID( Integer idArtista) throws Exception;
	List<Album> getAllAlbumByGeneroID( Integer idGenero) throws Exception;
}
