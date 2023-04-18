package com.sample.restapi.controller;


import com.sample.restapi.entity.Local;
import com.sample.restapi.exception.LocalNotFoundException;
import com.sample.restapi.interceptor.SkipApiKeyValidation;
import com.sample.restapi.service.LocalService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LocalController {

    @Autowired
    LocalService localService;

    @SkipApiKeyValidation
    @GetMapping("/findLocalById/{id}")
    Local findLocalById(@PathVariable Long id) throws LocalNotFoundException {
        return localService.findLocalById(id);
    }

    @GetMapping("/findLocalByNameWithJPQL/{name}")
    Optional<Local> findLocalByNameWithJPQL(@PathVariable String name) {
        return localService.findLocalByNameWithJPQL(name);
    }

    @GetMapping("findByName/{name}")
    Optional<Local> findByName(@PathVariable String name) {
        return localService.findByName(name);
    }

    @GetMapping("findByNameIgnoreCase/{name}")
    Optional<Local> findByNameIgnoreCase(@PathVariable String name) {
        return localService.findByNameIgnoreCase(name);
    }

    @GetMapping("/findAllLocals")
    public List<Local> findAllLocals() {
        return localService.findAllLocals();
    }

    @PostMapping("/saveLocal")
    public Local saveLocal(@Valid @RequestBody Local local) {
        return localService.saveLocal(local);
    }

    @PutMapping("/updateLocal/{id}")
    public Local updateLocal(@PathVariable Long id, @RequestBody Local local) {
        return localService.updateLocal(id, local);
    }

    @DeleteMapping("/deleteLocal/{id}")
    public String deleteLocal(@PathVariable Long id) {
        localService.deleteLocal(id);
        return "Successfully deleted";
    }

}
