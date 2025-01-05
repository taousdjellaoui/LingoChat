package com.example.lingochat.service;

import com.example.lingochat.entities.Lien;
import com.example.lingochat.entities.Utilisateur;
import com.example.lingochat.repos.LienRepository;
import com.example.lingochat.repos.UtilisateurRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class LienService {
    @Autowired
    LienRepository repos;
    @Autowired
    UtilisateurRepository reposU;

    public List<Lien> findLienByUtilisateurId(int id){
        return ( List<Lien> ) repos.findLienByUtilisateurId(id);
    }
    public Lien enregistrerLien(Lien lien) {
        return repos.save(lien);
    }

    public boolean maxList(Integer id){
       Utilisateur utilisateur = reposU.getUtilisateurById(id);
        return utilisateur.getLiens().size() < 4;
    }
    public void deleteLien(Integer id, Integer idLien){
        Utilisateur user = reposU.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        Lien lien = repos.findById(idLien).orElseThrow(() -> new RuntimeException("Lien not found"));
        user.getLiens().remove(lien);
        reposU.save(user);
    }
}
