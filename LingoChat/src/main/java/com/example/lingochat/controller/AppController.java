package com.example.lingochat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

    @Controller
    public class AppController {
        @GetMapping("")
        public String pageAccueil (){
            return "index";
        }

        //maping temporaire pour les liens dans le header

        @GetMapping("accountInfo")
        public String pageaccountInfo (){
            return "accountInfo";
        }
    }

