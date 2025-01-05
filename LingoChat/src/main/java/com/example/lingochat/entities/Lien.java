package com.example.lingochat.entities;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
@Entity
@Table(name = "lien")
public class Lien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST) // Ajout de cascade
    @JoinColumn(name = "utilisateur_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Utilisateur utilisateur;

    @Column(length = 64)
    private String description,url;

    public Lien() {
    }

    public Lien(Integer id) {
        this.id = id;
    }

    public Lien(Utilisateur utilisateur, String description, String url) {
        this.utilisateur = utilisateur;
        this.description = description;
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    @Override
    public String toString() {
        return "Lien{" +
                "id=" + id +
                ", utilisateur_name=" + utilisateur.getUserName() +
                ", description='" + description +
                ", url='" + url + '}';
    }
}
