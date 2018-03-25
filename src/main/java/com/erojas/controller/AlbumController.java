package com.erojas.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.erojas.model.Album;
import com.erojas.service.AlbumService;

@Controller
@RequestMapping("/album")
public class AlbumController {

	@Autowired
	private AlbumService as;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@RequestMapping(value="/leer/{idAlbum}", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	   public ResponseEntity<Album> getAlbumById(@PathVariable("idAlbum") String idAlbum){
		   
		   Album per = new Album();
		   try {
			   Integer id = Integer.parseInt(idAlbum);
			   per=as.findById(id).get();
			
			  
			} catch (Exception e) {
				logger.error("Unexpected Exception caught.", e);
				return new ResponseEntity<Album>(per, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<Album>(per, HttpStatus.OK);
	   }
	
	 @RequestMapping(value="/listar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	   public ResponseEntity<List<Album>> getAllAlbum(){
		   
		   List<Album> lista=null;
		   
		   try {
			lista=as.findAll();
			  
			} catch (Exception e) {
				logger.error("Unexpected Exception caught.", e);
				return new ResponseEntity<List<Album>>(lista, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<List<Album>>(lista, HttpStatus.OK);
	   }
	
	 @RequestMapping(path="/eliminar/{id}" , method=RequestMethod.DELETE)
	   public ResponseEntity<String> eliminar(@PathVariable("id") Integer id){
		   String mensaje="";
		   
		   try {
			as.deleteById(id);
			mensaje="!!! Eliminacion exitosa !!!";
		} catch (Exception e) {
			mensaje="--- Eliminacion fallida ----";
			e.printStackTrace();
			logger.error("Unexpected Exception caught.", e);
			return new ResponseEntity<String>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		   return new ResponseEntity<String>(mensaje, HttpStatus.OK);
	   }
	 
	 @RequestMapping(path="/registrar" , method=RequestMethod.POST , produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	   public ResponseEntity<String> registrar( @RequestBody Album Album){
		   String mensaje="";
		   try {
			 
			as.save(Album);
			mensaje="!!! Registro exitosa !!!";
		} catch (Exception e) {
			mensaje="--- Registro fallido ----";
			e.printStackTrace();
			logger.error("Unexpected Exception caught.", e);
			return new ResponseEntity<String>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		   return new ResponseEntity<String>(mensaje, HttpStatus.OK);
	   }
	 
	 @RequestMapping(path="/modificar/{id}" , method=RequestMethod.PUT , produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	   public ResponseEntity<String> modificar(@PathVariable("id") Integer id, @RequestBody Album Album){
		   String mensaje="";
		   try {
			   Album al = new Album();
			   al=as.findById(id).get();
			   al.setAnho(Album.getAnho());
			   al.setArtista(Album.getArtista());
			   al.setComentario(Album.getComentario());
			   al.setGenero(Album.getGenero());
			   al.setNombre(Album.getNombre());
			   al.setUrlFoto(Album.getUrlFoto());
			as.update(al);
			mensaje="!!! Modificacion exitosa  !!!";
		} catch (Exception e) {
			mensaje="--- Modificacion fallida  ----";
			e.printStackTrace();
			logger.error("Unexpected Exception caught.", e);
			return new ResponseEntity<String>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		   return new ResponseEntity<String>(mensaje, HttpStatus.OK);
	   }
	 
	 // Relations methods
	 @RequestMapping(value="/leer/artista/{idArtista}", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	   public ResponseEntity<List<Album>>  getAllAlbumByArtistaID(@PathVariable("idArtista") String idArtista){
		   
		   List<Album> lista = new ArrayList<>();;
		   try {
			   Integer id = Integer.parseInt(idArtista);
			   lista=as.getAllAlbumByArtistaID(id);
			
			  
			} catch (Exception e) {
				logger.error("Unexpected Exception caught.", e);
				return new ResponseEntity<List<Album>>(lista, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<List<Album>>(lista, HttpStatus.OK);
	   }
	 @RequestMapping(value="/leer/genero/{idGenero}", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	   public ResponseEntity<List<Album>>  getAllAlbumByGeneroID(@PathVariable("idGenero") String idGenero){
		   
		   List<Album> lista = new ArrayList<>();;
		   try {
			   Integer id = Integer.parseInt(idGenero);
			   lista=as.getAllAlbumByArtistaID(id);
			
			  
			} catch (Exception e) {
				logger.error("Unexpected Exception caught.", e);
				return new ResponseEntity<List<Album>>(lista, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<List<Album>>(lista, HttpStatus.OK);
	   }
	 
}
