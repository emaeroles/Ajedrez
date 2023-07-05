package com.grupo5.apitpajedrez.repositories;

import com.grupo5.apitpajedrez.entities.PartidaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidaRepository extends JpaRepository<PartidaEntity, Long> {
}
