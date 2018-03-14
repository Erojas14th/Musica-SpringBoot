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

import com.erojas.model.Cancion;
import com.erojas.service.CancionService;

@Controller
@RequestMapping("/cancion")
public class CancionController {


	@Autowired
	private CancionService as;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value="/leer/{idCancion}", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	   public ResponseEntity<Cancion> getCancionById(@PathVariable("idCancion") String idCancion){
		   
		   Cancion per = new Cancion();
		   try {
			   Integer id = Integer.parseInt(idCancion);
			   per=as.findById(id).get();
			
			  
			} catch (Exception e) {
				logger.error("Unexpected Exception caught.", e);
				return new ResponseEntity<Cancion>(per, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<Cancion>(per, HttpStatus.OK);
	   }
	
	 @RequestMapping(value="/listar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	   public ResponseEntity<List<Cancion>> getAllCancion(){
		   
		   List<Cancion> lista=null;
		   
		   try {
			lista=as.findAll();
			  
			} catch (Exception e) {
				logger.error("Unexpected Exception caught.", e);
				return new ResponseEntity<List<Cancion>>(lista, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<List<Cancion>>(lista, HttpStatus.OK);
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
	   public ResponseEntity<String> registrar( @RequestBody Cancion Cancion){
		   String mensaje="";
		   try {
			as.save(Cancion);
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
	   public ResponseEntity<String> modificar(@PathVariable("id") Integer id, @RequestBody Cancion Cancion){
		   String mensaje="";
		   try {
			   Cancion can = new Cancion();
			   can=as.findById(id).get();
			   can.setAlbum(Cancion.getAlbum());
			   can.setEstrellas(Cancion.getEstrellas());
			   can.setNombre(Cancion.getNombre());
			   can.setPista(Cancion.getPista());
			as.update(can);
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
	 @RequestMapping(value="/leer/{idAlbum}", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	   public ResponseEntity<List<Cancion>>  getAllCancionByAlbumID(@PathVariable("idAlbum") String idAlbum){
		   
		   List<Cancion> lista = new ArrayList<>();;
		   try {
			   Integer id = Integer.parseInt(idAlbum);
			lista=as.getAllCancionByAlbumID(id);
			
			  
			} catch (Exception e) {
				logger.error("Unexpected Exception caught.", e);
				return new ResponseEntity<List<Cancion>>(lista, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<List<Cancion>>(lista, HttpStatus.OK);
	   }
}
