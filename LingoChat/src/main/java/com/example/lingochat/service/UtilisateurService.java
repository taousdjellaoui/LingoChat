package com.example.lingochat.service;

import com.example.lingochat.entities.Utilisateur;
import com.example.lingochat.repos.UtilisateurRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class    UtilisateurService {
    @Autowired
    UtilisateurRepository repos;   //     UtilisateurRepository repos = new  UtilisateurRepository ()

    public List<Utilisateur> afficherUtilisateurs(){
        return repos.findAll();
    }
    public List<Utilisateur> afficherAmis(Integer id){
        return ( List<Utilisateur> ) repos.findAllFriendsByUserId(id);
    }
    public List<Utilisateur> afficherAmis2(Integer id){
        return ( List<Utilisateur> ) repos.findAllFriendsByUserId2(id);
    }
    public Utilisateur enregistrer(Utilisateur utilisateur){
        return repos.save(utilisateur);
    }


    public List<Utilisateur> chercherPhoto(String photo) throws UtilisateurNotFoundException {
        try {
            return   repos.findByFileName(photo);
        }catch (NoSuchElementException exception){
            throw new UtilisateurNotFoundException("Utilisateur introuvable avec la photo"+ photo);
        }
    }

    public boolean isEmailUnique(String courriel, Integer id){
        Utilisateur userCourriel =   repos.getUtilisateurByCourriel(courriel);
        if(userCourriel==null) return true;
        boolean isCreatingNewUser = false;
        if(id == null){
            isCreatingNewUser = true;
        }
        if(isCreatingNewUser){
            if(userCourriel!=null) return false;

        }else{
            if(userCourriel.getId()!=id) return false;
        }

        return true;
    }
    public boolean isUsernameUnique(String username, Integer id){
        Utilisateur userUserName =   repos.findByUserName(username);
        if(userUserName==null) return true;
        boolean isCreatingNewUser = false;
        if(id == null){
            isCreatingNewUser = true;
        }
        if(isCreatingNewUser){
            if(userUserName!=null) return false;

        }else{
            if(userUserName.getId()!=id) return false;
        }

        return true;
    }

    public void supprimerUtilisateur(Integer id) throws UtilisateurNotFoundException {
        Long compterId =  repos.countById(id);
        System.out.println("compterId : " + compterId);

        if(compterId== null || compterId==0){
            throw new UtilisateurNotFoundException("Utilisateur avec l'id " +id + "introuvable");
        }
        repos.deleteById(id);
    }

    public Utilisateur trouverUtilisateur(Integer id) throws UtilisateurNotFoundException {

        try {
            Utilisateur utilisateur =  repos.findById(id).get();
            return utilisateur;

        }catch (NoSuchElementException ex){
            throw new UtilisateurNotFoundException("Utilisateur avec l'id " +id + "introuvable");
        }

    }
    public Utilisateur trouverUtilisateurParEmailEtMotDePasse(String email,String password) throws UtilisateurNotFoundException {

        try {
            Utilisateur utilisateur =  repos.getUtilisateurByCourrielAndMotDePasse(email,password);
            return utilisateur;

        }catch (NoSuchElementException ex){
            throw new UtilisateurNotFoundException("Utilisateur avec le email " +email + "introuvable");
        }

    }
    public void changerEmail(String email,int id) throws UtilisateurNotFoundException {

        try {
           repos.changerEmail(id,email);


        }catch (NoSuchElementException ex){
            throw new UtilisateurNotFoundException("Changement d'email echoué");
        }

    }
    public void changerPassword(String password,int id) throws UtilisateurNotFoundException {

        try {
            repos.changerPassword(id,password);


        }catch (NoSuchElementException ex){
            throw new UtilisateurNotFoundException("Changement de password echoué");
        }

    }


    public void updateActiveStatus(Integer id, boolean enable) {
        repos.updateActiveStatus(id, enable);
    }
    public void addFriend(Integer id, Integer idFriend){
        Utilisateur user = repos.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        Utilisateur friend = repos.findById(idFriend).orElseThrow(() -> new RuntimeException("Friend not found"));

        // Add friend to the user's list of friends
        user.getFriends().add(friend);

        // Save the updated user to the database
        repos.save(user);
    }
    public void deleteFriend(Integer id, Integer idFriend){
        Utilisateur user = repos.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        Utilisateur friend = repos.findById(idFriend).orElseThrow(() -> new RuntimeException("Friend not found"));
        user.getFriends().remove(friend);
        user.getFriendsFav().remove(friend);
        repos.save(user);
    }
    public void addFav(Integer id, Integer idFriend){
        Utilisateur user = repos.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        Utilisateur friend = repos.findById(idFriend).orElseThrow(() -> new RuntimeException("Friend not found"));

        // Add friend to the user's list of friends
        user.getFriendsFav().add(friend);

        // Save the updated user to the database
        repos.save(user);
    }
    public void deleteFav(Integer id, Integer idFriend){
        Utilisateur user = repos.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        Utilisateur friend = repos.findById(idFriend).orElseThrow(() -> new RuntimeException("Friend not found"));
        user.getFriendsFav().remove(friend);
        repos.save(user);
    }
    public List<Utilisateur> rechercherUtilisateur(String keyword) {
        if (keyword != null) {
            return repos.findAllByName(keyword);
        }
        return  null;
    }
    public List<Utilisateur> rechercherFriends(String keyword,Integer idUser) {
        if (keyword != "") {
            return repos.findAllFriends(keyword,idUser);
        }
        else if (keyword.isBlank()) {
            return repos.findAllFriends(idUser);
        } else {
            return repos.findAllFriends(idUser);
        }
    }
}
