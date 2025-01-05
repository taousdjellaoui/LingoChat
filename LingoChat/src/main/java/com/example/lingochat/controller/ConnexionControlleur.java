package com.example.lingochat.controller;

import com.example.lingochat.entities.Utilisateur;
import com.example.lingochat.service.UtilisateurNotFoundException;
import com.example.lingochat.service.UtilisateurService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ConnexionControlleur {
    @Autowired
    UtilisateurService service;

    @GetMapping("/inscription")
    public String sInscrireformulaire(@ModelAttribute("utilisateur") Utilisateur utilisateur, Model model) {

        return "inscription";
    }

    @PostMapping("/inscription")
    public String sInscrire(@ModelAttribute("utilisateur") Utilisateur utilisateur, Model model,RedirectAttributes redirectAttributes) {

    System.out.println("Utilisateur :" + utilisateur);
    utilisateur.setStatus("utilisateur");
    System.out.println("Utilisateur :" + utilisateur);
    utilisateur.setActive(true);
    service.enregistrer(utilisateur);
    model.addAttribute("message", "Inscription Réussite");
         redirectAttributes.addFlashAttribute("message", "Inscription Réussite!");
         return "connexion";


    }

    @GetMapping("/connexion")
    public String sConnecterformulaire(@ModelAttribute("utilisateur") Utilisateur utilisateur, Model model) {

        return "connexion";
    }

    @PostMapping("/connexion")
    public String seConnecter(@ModelAttribute("utilisateur") Utilisateur utilisateur, Model model, HttpSession session, RedirectAttributes redirectAttributes) {

        try {

                Utilisateur utilisateurTrouver = service.trouverUtilisateurParEmailEtMotDePasse(utilisateur.getCourriel(), utilisateur.getMotDePasse());
            if (utilisateurTrouver!= null) {
                session.setAttribute("username", utilisateurTrouver.getUserName());
                session.setAttribute("status", utilisateurTrouver.getStatus());
                session.setAttribute("id", utilisateurTrouver.getId());
                session.setAttribute("photo", utilisateurTrouver.getPhoto());
                System.out.println("Utilisateur session photo:" + session.getAttribute("photo"));

                System.out.println("Utilisateur session:" + session.getAttribute("username"));
                return "index";

            }else{
                redirectAttributes.addFlashAttribute("message", "l'utilisateur avec l'Email " + utilisateur.getCourriel() + " est introuvable");
                return "redirect:/connexion";
            }

        } catch (UtilisateurNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", "l'utilisateur avec l'Email " + utilisateur.getCourriel() + " est introuvable");
        }

return "connexion";
    }

    @GetMapping("/deconnexion")
    public String Sedeconnecter(HttpSession session) {
        session.invalidate();
        return "redirect:/connexion";

    }
}
