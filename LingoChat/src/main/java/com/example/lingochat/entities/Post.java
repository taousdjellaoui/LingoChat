package com.example.lingochat.entities;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST) // Ajout de cascade
    @JoinColumn(name = "forum_id", nullable = false)
    private Forum forum;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST) // Ajout de cascade
    @JoinColumn(name = "id_utilisateur", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Utilisateur utilisateur;


    @Column(length = 64)
    private String photo;
    @Column(length = 64)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Post() {
    }

    public Post(Integer id) {
        this.id = id;
    }

    public Post(Forum forum, Utilisateur utilisateur) {
        this.forum = forum;
        this.utilisateur = utilisateur;
    }

    public Post(String photo, String description, Date date) {
        this.photo = photo;
        this.description = description;
        this.date = date;
    }

    public Post(int id, String photo, String description) {
        this.id = id;
        this.photo = photo;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", forum_id=" + forum.getId() +
                ", utilisateur_name=" + utilisateur.getUserName() +
                ", photo='" + photo +
                ", description='" + description +
                ", date=" + date +
                '}';
    }
}
