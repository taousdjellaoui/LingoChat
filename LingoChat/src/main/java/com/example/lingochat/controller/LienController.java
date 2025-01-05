package com.example.lingochat.controller;

import com.example.lingochat.entities.Lien;
import com.example.lingochat.entities.Utilisateur;
import com.example.lingochat.service.LienService;
import com.example.lingochat.service.UtilisateurNotFoundException;
import com.example.lingochat.service.UtilisateurService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static java.lang.Integer.parseInt;

@Controller
public class LienController {
    @Autowired
    LienService service;
    @Autowired
    UtilisateurService serviceUser;

    @PostMapping("/lien/save")
    public String ajouterLien(Lien lien, RedirectAttributes redirectAttributes, HttpSession session) throws UtilisateurNotFoundException {
        int userId = Integer.parseInt(session.getAttribute("id").toString());
        Utilisateur utilisateur = serviceUser.trouverUtilisateur(userId);
        lien.setUtilisateur(utilisateur);
        service.enregistrerLien(lien);
        return "redirect:/profil_"+userId;
    }
    @GetMapping("/lien_{id}/delete")
    public String supprimerLien (@PathVariable(name = "id" ) Integer lienId, Model model, HttpSession session) throws UtilisateurNotFoundException {
        int idUser = parseInt(session.getAttribute("id").toString());
        service.deleteLien(idUser,lienId);

        return "redirect:/profil_"+idUser;
    }
}
