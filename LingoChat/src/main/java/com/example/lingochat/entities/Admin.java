package com.example.lingochat.entities;

import jakarta.persistence.*;
//À NE PAS UTILISER, ON UTILISE QUE LE STATUS  POUR LES DIFFÉRENCIER FINALEMENT C'EST PLUS SIMPLE
//À NE PAS UTILISER
//À NE PAS UTILISER
//À NE PAS UTILISER
//À NE PAS UTILISER
//À NE PAS UTILISER
//À NE PAS UTILISER
//À NE PAS UTILISER
//@Entity
//@Table(name = "admin")
public class Admin extends Utilisateur{
    //@ManyToOne(optional = false, cascade = CascadeType.PERSIST) // Ajout de cascade
    //@JoinColumn(name = "idAdmin", nullable = false)
    private Utilisateur utilisateur;
    //@Column(length = 150 )
    private String description;

    public Admin() {
    }
    public Admin(String description, int id, String userName, String motDePasse, String courriel, String photo, boolean active) {
        super(id, userName, motDePasse, courriel, photo,active);
        this.description = description;

    }

    public Admin(String description, String userName, String motDePasse, String courriel, String photo, boolean active) {
        super(userName, motDePasse, courriel, photo, active);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return super.toString() + "Admin{" + "description=" + description + '}'; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }





}
