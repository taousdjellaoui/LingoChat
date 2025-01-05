package com.example.lingochat.controller;

import com.example.lingochat.entities.*;
import com.example.lingochat.service.*;
import com.mysql.cj.Session;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Integer.parseInt;

@Controller
public class UtilisateurController {
    @Autowired
    UtilisateurService service;
    @Autowired
    ReferenceService serviceRef;
    @Autowired
    LangueService serviceLang;
    @Autowired
    LienService serviceLien;
    @GetMapping("/discover")
    public String afficherUtilisateur(Model model){
        List<Utilisateur> listUtilisateurs = service.afficherUtilisateurs();


        model.addAttribute("listUtilisateurs",listUtilisateurs);
        return "discover";
    }

    @GetMapping("/amis")
    public String afficherAmis(Model model,HttpSession session) throws UtilisateurNotFoundException {
        int idUser = parseInt(session.getAttribute("id").toString());
        Utilisateur user = service.trouverUtilisateur(idUser);
        Utilisateur friendProfile = user;

        /*ist<Utilisateur> listAmis = service.afficherAmis(idUser);
        List<Utilisateur> listAmis2 = service.afficherAmis2(idUser);
        listAmis.addAll(listAmis2);*/

        Set<Utilisateur> listAmis = user.getFriends();
        Set<Utilisateur> listFav = user.getFriendsFav();

        //Amis favoris apparaiterons en premier (le set normal ne garde pas les index)
        Set<Utilisateur> listAmisSet = new LinkedHashSet<>();
        listAmisSet.addAll(listFav);
        listAmisSet.addAll(listAmis);

        if (!user.getFriends().isEmpty()){
            if (!user.getFriendsFav().isEmpty()){
                friendProfile = user.getFriendsFav().iterator().next();
            } else {
                friendProfile = user.getFriends().iterator().next();
            }
        }

        int idFriend = friendProfile.getId();

        model.addAttribute("listAmis",listAmisSet);
        model.addAttribute("friendProfile",friendProfile);

        List<Langue> listeLangParle = serviceLang.findLanguesParleByUtilisateurId(idFriend);
        List<Langue> listeLangApprise = serviceLang.findLanguesAppriseByUtilisateurId(idFriend);
        List<Lien> listeLien = serviceLien.findLienByUtilisateurId(idFriend);
        List<Reference> listeRef = serviceRef.findReferenceByUtilisateurId(idFriend);
        model.addAttribute("user",user);
        model.addAttribute("listeLangParle",listeLangParle);
        model.addAttribute("listeLangApprise",listeLangApprise);
        model.addAttribute("listeLien",listeLien);
        model.addAttribute("listeRef",listeRef);
        Reference reference = new Reference();
        model.addAttribute("reference",reference);

        return "friends";
    }
    @GetMapping("/amis_{id}")
    public String afficherAmi(Model model,@PathVariable(name = "id" ) Integer friendId,HttpSession session) throws UtilisateurNotFoundException {
        int idUser = parseInt(session.getAttribute("id").toString());
        Utilisateur user = service.trouverUtilisateur(idUser);

        /*List<Utilisateur> listAmis = service.afficherAmis(idUser);
        List<Utilisateur> listAmis2 = service.afficherAmis2(idUser);
        listAmis.addAll(listAmis2);*/

        Set<Utilisateur> listAmis = user.getFriends();
        Set<Utilisateur> listFav = user.getFriendsFav();

        //Amis favoris apparaiterons en premier (le set normal ne garde pas les index)
        Set<Utilisateur> listAmisSet = new LinkedHashSet<>();
        listAmisSet.addAll(listFav);
        listAmisSet.addAll(listAmis);

        Utilisateur friendProfile = service.trouverUtilisateur(friendId);

        model.addAttribute("listAmis",listAmisSet);
        model.addAttribute("friendProfile",friendProfile);

        List<Langue> listeLangParle = serviceLang.findLanguesParleByUtilisateurId(friendId);
        List<Langue> listeLangApprise = serviceLang.findLanguesAppriseByUtilisateurId(friendId);
        List<Lien> listeLien = serviceLien.findLienByUtilisateurId(friendId);
        List<Reference> listeRef = serviceRef.findReferenceByUtilisateurId(friendId);
        model.addAttribute("user",user);
        model.addAttribute("listeLangParle",listeLangParle);
        model.addAttribute("listeLangApprise",listeLangApprise);
        model.addAttribute("listeLien",listeLien);
        model.addAttribute("listeRef",listeRef);
        Reference reference = new Reference();
        model.addAttribute("reference",reference);
        return "friends";
    }

    @GetMapping("/amis/recherche")
    public String rechercherUtilisateur(HttpSession session,Model model, @Param("keyword") String keyword) throws UtilisateurNotFoundException {
        int idUser = parseInt(session.getAttribute("id").toString());
        Utilisateur user = service.trouverUtilisateur(idUser);
        Utilisateur friendProfile = user;

        List<Utilisateur> listAmis = service.rechercherFriends(keyword,idUser);
        Set<Utilisateur> listFav = user.getFriendsFav();
        Set<Utilisateur> listAmisSet = new LinkedHashSet<>();

        // Add favorite friends first
        for (Utilisateur ami : listAmis) {
            if (listFav.contains(ami)) {
                listAmisSet.add(ami);
            }
        }
        // Add remaining friends
        for (Utilisateur ami : listAmis) {
            if (!listFav.contains(ami)) {
                listAmisSet.add(ami);
            }
        }
        if (!user.getFriends().isEmpty()){
            if (!user.getFriendsFav().isEmpty()){
                friendProfile = user.getFriendsFav().iterator().next();
            } else {
                friendProfile = user.getFriends().iterator().next();
            }
        }

        int friendId = friendProfile.getId();
        model.addAttribute("listAmis",listAmisSet);
        model.addAttribute("friendProfile",friendProfile);

        List<Langue> listeLangParle = serviceLang.findLanguesParleByUtilisateurId(friendId);
        List<Langue> listeLangApprise = serviceLang.findLanguesAppriseByUtilisateurId(friendId);
        List<Lien> listeLien = serviceLien.findLienByUtilisateurId(friendId);
        List<Reference> listeRef = serviceRef.findReferenceByUtilisateurId(friendId);
        model.addAttribute("user",user);
        model.addAttribute("listeLangParle",listeLangParle);
        model.addAttribute("listeLangApprise",listeLangApprise);
        model.addAttribute("listeLien",listeLien);
        model.addAttribute("listeRef",listeRef);
        Reference reference = new Reference();
        model.addAttribute("reference",reference);
        model.addAttribute("keyword", keyword);
        return "friends";
    }

    @GetMapping("/profil_{id}")
    public String pageProfilUser (@PathVariable(name = "id" ) Integer userId, Model model,HttpSession session) throws UtilisateurNotFoundException {
        Utilisateur user = service.trouverUtilisateur(userId);
        int idUser;
        if (session.getAttribute("id") != null) {
            idUser = parseInt(session.getAttribute("id").toString());
            Utilisateur userSession = service.trouverUtilisateur(idUser);
            model.addAttribute("userSession",userSession);
        }

        List<Langue> listeLang = serviceLang.getAllLangues();
        List<Langue> listeLangParle = serviceLang.findLanguesParleByUtilisateurId(userId);
        List<Langue> listeLangApprise = serviceLang.findLanguesAppriseByUtilisateurId(userId);
        List<Lien> listeLien = serviceLien.findLienByUtilisateurId(userId);
        List<Reference> listeRef = serviceRef.findReferenceByUtilisateurId(userId);
        model.addAttribute("user",user);
        model.addAttribute("listeLang",listeLang);
        model.addAttribute("listeLangParle",listeLangParle);
        model.addAttribute("listeLangApprise",listeLangApprise);
        model.addAttribute("listeLien",listeLien);
        model.addAttribute("listeRef",listeRef);

        Lien lien = new Lien();
        model.addAttribute("lien",lien);
        return "profil";
    }
    @GetMapping("/profil_{id}/add")
    public String ajouterAmis (@PathVariable(name = "id" ) Integer friendId, Model model,HttpSession session) throws UtilisateurNotFoundException {
        int idUser = parseInt(session.getAttribute("id").toString());
        service.addFriend(idUser,friendId);

        return "redirect:/profil_"+friendId;
    }
    @GetMapping("/profil_{id}/delete")
    public String supprimerAmis (@PathVariable(name = "id" ) Integer friendId, Model model,HttpSession session) throws UtilisateurNotFoundException {
        int idUser = parseInt(session.getAttribute("id").toString());
        service.deleteFriend(idUser,friendId);

        return "redirect:/profil_"+friendId;
    }
    @GetMapping("/amis_{id}/delete")
    public String supprimerAmi (@PathVariable(name = "id" ) Integer friendId, Model model,HttpSession session) throws UtilisateurNotFoundException {
        int idUser = parseInt(session.getAttribute("id").toString());
        service.deleteFriend(idUser,friendId);

        return "redirect:/amis";

    }
    @GetMapping("/fav_{id}/add")
    public String supprimerFavoris (@PathVariable(name = "id" ) Integer friendId, Model model,HttpSession session) throws UtilisateurNotFoundException {
        int idUser = parseInt(session.getAttribute("id").toString());
        Utilisateur userSession = service.trouverUtilisateur(idUser);
        model.addAttribute("userSession",userSession);
        service.addFav(idUser,friendId);

        return "redirect:/amis";
    }
    @GetMapping("/fav_{id}/delete")
    public String ajouterFavoris (@PathVariable(name = "id" ) Integer friendId, Model model,HttpSession session) throws UtilisateurNotFoundException {
        int idUser = parseInt(session.getAttribute("id").toString());
        Utilisateur userSession = service.trouverUtilisateur(idUser);
        model.addAttribute("userSession",userSession);
        service.deleteFav(idUser,friendId);

        return "redirect:/amis";
    }

}
