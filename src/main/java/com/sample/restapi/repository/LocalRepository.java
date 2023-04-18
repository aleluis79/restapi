package com.sample.restapi.repository;

import com.sample.restapi.entity.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LocalRepository  extends JpaRepository<Local, Long> {

    // Consulta con JPQL
    @Query("Select l FROM Local l WHERE l.name = :name")
    Optional<Local> findLocalByNameWithJPQL(String name);

    // Consulta con Inversión de Control
    Optional<Local> findByName(String name);

    Optional<Local> findByNameIgnoreCase(String name);

}
