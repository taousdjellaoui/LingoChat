package com.example.lingochat.controller;

import com.example.lingochat.entities.Utilisateur;
import com.example.lingochat.service.UtilisateurNotFoundException;
import com.example.lingochat.service.UtilisateurService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Objects;

import static java.lang.Integer.parseInt;

@Controller
public class SettingsController {
    @Autowired
    UtilisateurService service;
    // GET mapping for displaying the account settings page
    @GetMapping("/accountInfo={action}")
    public String showAccountSettings(Model model,@PathVariable(name ="action") String action) {
    model.addAttribute("action",action);
    if(Objects.equals(action, "6")){
        List<Utilisateur> listUtilisateurs = service.afficherUtilisateurs();
        model.addAttribute("listUtilisateurs",listUtilisateurs);
        System.out.println("action ::"+action);
    }
        return "accountInfo"; // Thymeleaf template name
    }


    // POST mapping for handling form submissions
    @PostMapping("/accountInfo/ChangerEmail")
    public String handleChangerEmail( @RequestParam("email") String email,
                                   Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        // Logic to handle different actions based on 'cas' parameter
        // For simplicity, let's assume '1' is for changing email and '2' is for changing password

            try {
                service.changerEmail(email,parseInt(session.getAttribute("id").toString()));
                redirectAttributes.addFlashAttribute("message","changement de l'email réussi! ");
            } catch (UtilisateurNotFoundException e) {
                throw new RuntimeException(e);
            }



        return "redirect:/accountInfo=1";
    }
    @PostMapping("/accountInfo/ChangerPassword")
    public String handleChangerPassword( @RequestParam("password") String password,
                                     Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        // Logic to handle different actions based on 'cas' parameter
        // For simplicity, let's assume '1' is for changing email and '2' is for changing password

        try {
            service.changerPassword(password,parseInt(session.getAttribute("id").toString()));
            redirectAttributes.addFlashAttribute("message","changement de mot de passe réussi! ");
        } catch (UtilisateurNotFoundException e) {
            throw new RuntimeException(e);
        }



        return "redirect:/accountInfo=2";
    }
    @GetMapping("/utilisateurs")
    public String afficherUtilisateur(Model model){
        List<Utilisateur> listUtilisateurs = service.afficherUtilisateurs();
        model.addAttribute("listUtilisateurs",listUtilisateurs);
        return "accountInfo";
    }
    @GetMapping("/utilisateurs/{id}/active/{status}")
    public String mettreAjourStatusActiveUtilisateur(@PathVariable("id") Integer id, @PathVariable("status") boolean active, RedirectAttributes redirectAttributes){

        service.updateActiveStatus(id,active);
        return "redirect:/accountInfo=6";

    }
    @GetMapping("/utilisateurs/delete/{id}")
    public String supprimerUtilisateur(@PathVariable(name ="id" ) Integer id, RedirectAttributes redirectAttributes){

        System.out.println("Id : " + id);
        try {
            service.supprimerUtilisateur(id);
            redirectAttributes.addFlashAttribute("message","L'utilisateur avec ID " +id + " a été supprimé avec succès");
        }catch (UtilisateurNotFoundException ex){

            redirectAttributes.addFlashAttribute("message","L'utilisateur avec ID " +id + " est introuvable ");
        }

        return "redirect:/accountInfo=6";
    }
    @PostMapping("/utilisateurs/delete")
    public String supprimerUtilisateurActive(   Model model, HttpSession session,RedirectAttributes redirectAttributes) {


        try {
            service.supprimerUtilisateur(parseInt(session.getAttribute("id").toString()));
            redirectAttributes.addFlashAttribute("message","Votre compte a été supprimer avec succès");
           session.invalidate();
        } catch (UtilisateurNotFoundException e) {
            redirectAttributes.addFlashAttribute("message","erreur lors de la suppression ");


        }


            return "redirect:/connexion";
    }

    @PostMapping("/enregistrerUtilisateur")
    public String enregisterUtilisateur(@RequestParam("email") String email,@RequestParam("password") String password,@RequestParam("username") String username,
                                        @RequestParam("status") String status,@RequestParam("active") String active
                                       , Model model, HttpSession session, RedirectAttributes redirectAttributes) {
     boolean activeStatus;
     if(active.equals("on")){
         activeStatus=true;
     }else{
         activeStatus=false;
     }

        Utilisateur utilisateur = new Utilisateur(username,email,password,"",status, activeStatus);
        service.enregistrer(utilisateur);
        model.addAttribute("message", "Inscription Réussite");
        redirectAttributes.addFlashAttribute("message", "Inscription Réussite!");

        return "redirect:/accountInfo=5";
    }
}
