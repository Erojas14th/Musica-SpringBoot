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

import com.erojas.model.Genero;
import com.erojas.service.GeneroService;

@Controller
@RequestMapping("/genero")
public class GeneroController {

	@Autowired
	private GeneroService as;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value="/leer/{idGenero}", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	   public ResponseEntity<Genero> getGeneroById(@PathVariable("idGenero") String idGenero){
		   
		   Genero per = new Genero();
		   try {
			   Integer id = Integer.parseInt(idGenero);
			   per=as.findById(id).get();
			
			  
			} catch (Exception e) {
				logger.error("Unexpected Exception caught.", e);
				return new ResponseEntity<Genero>(per, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<Genero>(per, HttpStatus.OK);
	   }
	
	 @RequestMapping(value="/listar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	   public ResponseEntity<List<Genero>> getAllGenero(){
		   
		   List<Genero> lista=null;
		   
		   try {
			lista=as.findAll();
			  
			} catch (Exception e) {
				logger.error("Unexpected Exception caught.", e);
				return new ResponseEntity<List<Genero>>(lista, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<List<Genero>>(lista, HttpStatus.OK);
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
	   public ResponseEntity<String> registrar( @RequestBody Genero Genero){
		   String mensaje="";
		   try {
			as.save(Genero);
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
	   public ResponseEntity<String> modificar(@PathVariable("id") Integer id, @RequestBody Genero Genero){
		   String mensaje="";
		   try {
			   Genero gen = new Genero();
			   gen=as.findById(id).get();
			   gen.setNombre(Genero.getNombre());
			  
			as.update(gen);
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
