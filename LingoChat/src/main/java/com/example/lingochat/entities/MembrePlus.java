package com.example.lingochat.entities;

import jakarta.persistence.*;
//À NE PAS UTILISER, ON UTILISE UTILISATEUR DIRECTEMENT
//À NE PAS UTILISER
//À NE PAS UTILISER
//À NE PAS UTILISER
//À NE PAS UTILISER
//À NE PAS UTILISER
//À NE PAS UTILISER
//À NE PAS UTILISER

//@Entity
//@Table(name = "membreplus")
public class MembrePlus extends Utilisateur {

    //@ManyToOne(optional = false, cascade = CascadeType.PERSIST) // Ajout de cascade
    //@JoinColumn(name = "idMembrePlus", nullable = false)
    private Utilisateur utilisateur;
    //@Column(length = 150)
    private String url;
    //@Column(length = 150)
    private String description;


    public MembrePlus(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }


    public MembrePlus(String description, int id, String userName, String motDePasse, String courriel, String photo, boolean active) {
        super(id, userName, motDePasse, courriel, photo, active);
        this.description = description;
    }

    public MembrePlus(String description, String userName, String motDePasse, String courriel, String photo,boolean active) {
        super(userName, motDePasse, courriel, photo,active);
        this.description = description;
    }

    public MembrePlus() {
    }
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "MembrePlus{" +
                "utilisateur=" + utilisateur +
                ", url='" + url +
                ", description='" + description +
                '}';
    }
}


