package com.grupo5.apitpajedrez.repositories;

import com.grupo5.apitpajedrez.entities.JugadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadorRepository extends JpaRepository<JugadorEntity, Long> {
}
