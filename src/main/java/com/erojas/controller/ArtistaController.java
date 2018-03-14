package com.erojas.controller;

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

import com.erojas.model.Artista;

import com.erojas.service.ArtistaService;

@Controller
@RequestMapping("/artista")
public class ArtistaController {
	
	@Autowired
	private ArtistaService as;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value="/leer/{idArtista}", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	   public ResponseEntity<Artista> getArtistaById(@PathVariable("idArtista") String idArtista){
		   
		   Artista per = new Artista();
		   try {
			   Integer id = Integer.parseInt(idArtista);
			   per=as.findById(id).get();
			
			  
			} catch (Exception e) {
				logger.error("Unexpected Exception caught.", e);
				return new ResponseEntity<Artista>(per, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<Artista>(per, HttpStatus.OK);
	   }
	
	 @RequestMapping(value="/listar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	   public ResponseEntity<List<Artista>> getAllArtista(){
		   
		   List<Artista> lista=null;
		   
		   try {
			lista=as.findAll();
			  
			} catch (Exception e) {
				logger.error("Unexpected Exception caught.", e);
				return new ResponseEntity<List<Artista>>(lista, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<List<Artista>>(lista, HttpStatus.OK);
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
	   public ResponseEntity<String> registrar( @RequestBody Artista Artista){
		   String mensaje="";
		   try {
			as.save(Artista);
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
	   public ResponseEntity<String> modificar(@PathVariable("id") Integer id, @RequestBody Artista Artista){
		   String mensaje="";
		   try {
			   Artista art = as.findById(id).get();
			   art.setNombre(Artista.getNombre());
			   art.setUrlFoto(Artista.getUrlFoto());
			  
			as.update(art);
			mensaje="!!! Modificacion exitosa  !!!";
		} catch (Exception e) {
			mensaje="--- Modificacion fallida  ----";
			e.printStackTrace();
			logger.error("Unexpected Exception caught.", e);
			return new ResponseEntity<String>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		   return new ResponseEntity<String>(mensaje, HttpStatus.OK);
	   }
	 
}
