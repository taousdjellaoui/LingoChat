package com.example.lingochat.rest;

import com.example.lingochat.service.LienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LienRestController {
    @Autowired
    LienService service;

    @PostMapping("/lien/check_nombre")
    public String verifierMaxLien(@Param("id") Integer id) {
        return service.maxList(id) ? "OK" : "Max";
    }
}
