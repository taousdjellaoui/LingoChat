package com.example.lingochat.entities;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
@Entity
@Table(name = "forum")
public class Forum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //private int utilisateurId, postId;
    @Column(length = 64, nullable = false)
    private String nom;
    @Column(length = 64)
    private String description;


    @ManyToOne(optional = false, cascade = CascadeType.PERSIST) // Ajout de cascade
    @JoinColumn(name = "utilisateur_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "forum", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Post> posts = new HashSet<>();

    public Forum() {
    }

    public Forum(int id) {
        this.id = id;
    }

    public Forum(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void ajouterPost(Post post) {
        this.posts.add(post);
    }
    @Override
    public String toString() {
        return "Forum{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", utilisateur_name=" + utilisateur.getUserName() +'}';
    }



}
