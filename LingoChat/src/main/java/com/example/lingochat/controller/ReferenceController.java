package com.example.lingochat.controller;

import com.example.lingochat.entities.Lien;
import com.example.lingochat.entities.Post;
import com.example.lingochat.entities.Reference;
import com.example.lingochat.entities.Utilisateur;
import com.example.lingochat.service.LienService;
import com.example.lingochat.service.ReferenceService;
import com.example.lingochat.service.UtilisateurNotFoundException;
import com.example.lingochat.service.UtilisateurService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ReferenceController {
    @Autowired
    ReferenceService service;
    @Autowired
    UtilisateurService serviceUser;
    @PostMapping("/reference/save_{id}")
    public String ajouterReference(@PathVariable(name = "id" ) Integer friendId, @RequestParam("description") String description, RedirectAttributes redirectAttributes, HttpSession session, Model model) throws UtilisateurNotFoundException {
        int auteurId = Integer.parseInt(session.getAttribute("id").toString());
        Utilisateur auteur = serviceUser.trouverUtilisateur(auteurId);
        Utilisateur friendProfile = serviceUser.trouverUtilisateur(friendId);
        Reference reference = new Reference();
        reference.setAuteur(auteur);
        reference.setUtilisateur(friendProfile);
        reference.setDescription(description);
        service.enregistrerRef(reference);
        return "redirect:/amis_{id}";
    }
}
