package com.sample.restapi.service;

import com.sample.restapi.entity.Local;
import com.sample.restapi.exception.LocalNotFoundException;

import java.util.List;
import java.util.Optional;

public interface LocalService {
    List<Local> findAllLocals();
    Local saveLocal(Local local);
    Local updateLocal(Long id, Local local);
    void deleteLocal(Long id);
    Optional<Local> findLocalByNameWithJPQL(String name);
    Optional<Local> findByName(String name);
    Optional<Local> findByNameIgnoreCase(String name);
    Local findLocalById(Long id) throws LocalNotFoundException;
}
