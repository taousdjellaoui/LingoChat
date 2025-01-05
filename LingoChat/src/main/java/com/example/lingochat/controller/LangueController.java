package com.example.lingochat.controller;

import com.example.lingochat.entities.Utilisateur;
import com.example.lingochat.service.LangueService;
import com.example.lingochat.service.UtilisateurNotFoundException;
import com.example.lingochat.service.UtilisateurService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static java.lang.Integer.parseInt;

@Controller
public class LangueController {
    @Autowired
    LangueService service;
    @Autowired
    UtilisateurService serviceU;

    @GetMapping("/langParle_{id}/add")
    public String ajouterLangParle(@PathVariable(name = "id") Integer langId, Model model, HttpSession session) throws UtilisateurNotFoundException {
        int idUser = parseInt(session.getAttribute("id").toString());
        service.addLangParle(idUser, langId);

        return "redirect:/profil_"+idUser;
    }

    @GetMapping("/langParle_{id}/delete")
    public String supprimerLangParle(@PathVariable(name = "id") Integer langId, Model model, HttpSession session) throws UtilisateurNotFoundException {
        int idUser = parseInt(session.getAttribute("id").toString());
        service.deleteLangParle(idUser, langId);

        return "redirect:/profil_"+idUser;
    }

    @GetMapping("/langApprise_{id}/add")
    public String ajouterLangApprise(@PathVariable(name = "id") Integer langId, Model model, HttpSession session) throws UtilisateurNotFoundException {
        int idUser = parseInt(session.getAttribute("id").toString());
        service.addLangApprise(idUser, langId);

        return "redirect:/profil_"+idUser;
    }

    @GetMapping("/langApprise_{id}/delete")
    public String supprimerLangApprise(@PathVariable(name = "id") Integer langId, Model model, HttpSession session) throws UtilisateurNotFoundException {
        int idUser = parseInt(session.getAttribute("id").toString());
        service.deleteLangApprise(idUser, langId);

        return "redirect:/profil_"+idUser;
    }

}
