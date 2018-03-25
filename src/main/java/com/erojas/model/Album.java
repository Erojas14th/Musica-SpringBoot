package com.erojas.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity(name = "album")
@Table(name = "album")

public class Album implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id",nullable=false)
	private Integer id;
	// Relations
	@ManyToOne
	@JoinColumn(name = "fk_artista", nullable = false)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")//Serializa solo fk
	@JsonIdentityReference(alwaysAsId = true)  
	private Artista artista;

	@ManyToOne
	@JoinColumn(name = "fk_genero", nullable = false)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")//Serializa solo fk
	@JsonIdentityReference(alwaysAsId = true)  
	private Genero genero;
	
	@Column(name = "nombre")
	private String nombre;

	@Column(name = "foto")
	private String urlFoto;

	@Column(name = "video")
	private String urlVideo;
	
	@Column(name = "comentario")
	private String comentario;

	@Column(name = "anho", length = 4)
	private String anho;
	
	// Relations
		@OneToMany(mappedBy = "album", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
		private List<Cancion> canciones;
	// Constructor
		public Album() {
		}

		public Album(Integer id, String name, String urlFoto, String comentario, String anho,String urlVideo) {

			this.id = id;
			this.nombre = name;
			this.urlFoto = urlFoto;

			this.comentario = comentario;
			this.anho = anho;
			this.urlVideo=urlVideo;
		}

		// Getters and setters
		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getComentario() {
			return comentario;
		}

		public void setComentario(String comentario) {
			this.comentario = comentario;
		}

		public String getAnho() {
			return anho;
		}

		public void setAnho(String anho) {
			this.anho = anho;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getUrlFoto() {
			return urlFoto;
		}

		public void setUrlFoto(String urlFoto) {
			this.urlFoto = urlFoto;
		}

		public Artista getArtista() {
			return artista;
		}

		public void setArtista(Artista artista) {
			this.artista = artista;
		}

		public Genero getGenero() {
			return genero;
		}

		public void setGenero(Genero genero) {
			this.genero = genero;
		}

		public List<Cancion> getCanciones() {
			return canciones;
		}

		public void setCanciones(List<Cancion> canciones) {
			this.canciones = canciones;
		}
		
		
		
		
		public String getUrlVideo() {
			return urlVideo;
		}

		public void setUrlVideo(String urlVideo) {
			this.urlVideo = urlVideo;
		}

		// Equals and Hashcode
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Album other = (Album) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return true;
		}
		
		

}
