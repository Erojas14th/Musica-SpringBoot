package com.erojas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erojas.model.Artista;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista,Integer> {

}
