package com.example.lingochat.service;

import com.example.lingochat.entities.Langue;
import com.example.lingochat.entities.Reference;
import com.example.lingochat.entities.Utilisateur;
import com.example.lingochat.repos.ForumRepository;
import com.example.lingochat.repos.LangueRepository;
import com.example.lingochat.repos.UtilisateurRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class LangueService {
    @Autowired
    LangueRepository repos;
    @Autowired
    UtilisateurRepository reposU;
    public List<Langue> findLanguesAppriseByUtilisateurId (int id){
        return ( List<Langue> ) repos.findLanguesAppriseByUserId(id);
        }
        public List<Langue> findLanguesParleByUtilisateurId (int id){
            return ( List<Langue> ) repos.findLanguesParleByUserId(id);
        }
    public List<Langue> getAllLangues (){
        return ( List<Langue> ) repos.findAll();
    }

    public void addLangParle(Integer id, Integer idLang){
        Utilisateur user = reposU.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        Langue langue = repos.findById(idLang).orElseThrow(() -> new RuntimeException("Langue not found"));
        user.getLanguesParle().add(langue);
        reposU.save(user);
    }
    public void deleteLangParle(Integer id, Integer idLang){
        Utilisateur user = reposU.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        Langue langue = repos.findById(idLang).orElseThrow(() -> new RuntimeException("Langue not found"));
        user.getLanguesParle().remove(langue);
        reposU.save(user);
    }
    public void addLangApprise(Integer id, Integer idLang){
        Utilisateur user = reposU.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        Langue langue = repos.findById(idLang).orElseThrow(() -> new RuntimeException("Langue not found"));
        user.getLanguesApprise().add(langue);
        reposU.save(user);
    }
    public void deleteLangApprise(Integer id, Integer idLang){
        Utilisateur user = reposU.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        Langue langue = repos.findById(idLang).orElseThrow(() -> new RuntimeException("Langue not found"));
        user.getLanguesApprise().remove(langue);
        reposU.save(user);
    }


}
