package com.example.lingochat.rest;

import com.example.lingochat.entities.Utilisateur;
import com.example.lingochat.service.UtilisateurService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class UtilisateurRestController {
    @Autowired
    UtilisateurService service;
    @Autowired
    private EntityManager entityManager; // Injection de dépendance de l'EntityManager

    @GetMapping("/api/utilisateurs")
    public ResponseEntity<List<Utilisateur>> getAllUtilisateurs(){
        List<Utilisateur> utilisateurs = service.afficherUtilisateurs();

        if(utilisateurs.isEmpty()){
            return  new ResponseEntity<>(utilisateurs, HttpStatus.NO_CONTENT);
        }
        return  new ResponseEntity<>(utilisateurs, HttpStatus.OK);
    }
    @PostMapping("/inscription/check_email_username")
        public String verifierDoublonemailEtUsername(@Param("email")String email, @Param("id")Integer id,@Param("username")String username){
            String retourchekc1;
            String retourCheck2;
            System.out.println("email : "+ email);
            System.out.println("id : "+ id);
            retourchekc1 = service.isEmailUnique(email,id)?"OK":"email";
            retourCheck2= service.isUsernameUnique(username,id)?"OK":"username";
            if(retourchekc1.equals("OK") && retourCheck2.equals("OK")){
                return "OK";
            }else if(retourchekc1.equals("email")){
                return "email";
            }else{
                return "username";
            }



        }
        @PostMapping("/inscription/check_username")
        public String verifierDoublonusername(@Param("username")String username, @Param("id")Integer id){
            System.out.println("username : "+ username);
            System.out.println("id : "+ id);
            return service.isUsernameUnique(username,id)?"OK":"Doublon";

        }




    }

