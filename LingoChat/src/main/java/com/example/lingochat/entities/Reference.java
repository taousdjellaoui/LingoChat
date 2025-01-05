package com.example.lingochat.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "reference")
public class Reference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST) // Ajout de cascade
    @JoinColumn(name = "utilisateur_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Utilisateur utilisateur;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST) // Ajout de cascade
    @JoinColumn(name = "auteur_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Utilisateur auteur;

    @Column(length = 150)
    private String description;

    public Reference() {
    }

    public Reference(Integer id) {
        this.id = id;
    }

    public Reference(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Utilisateur getAuteur() {
        return auteur;
    }

    public void setAuteur(Utilisateur auteur) {
        this.auteur = auteur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Reference{" + "id=" + id + ", utilisateur_name=" + utilisateur.getUserName() + ", auteur_name=" + auteur.getUserName() + ", description=" + description + "}";
    }



}
