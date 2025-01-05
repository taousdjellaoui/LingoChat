package com.example.lingochat.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 40, nullable = false, unique = true)
    private String userName;
    @Column(length = 128, nullable = false, unique = true)
    private String courriel;
    @Column(length = 64, nullable = false)
    private String motDePasse;
    @Column(length = 64)
    private String photo;

    @Column(length = 64)
    private String status;

    private boolean active;

    //relation ManyToMany
    @ManyToMany
    @JoinTable(name="utilisateur_friend",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Utilisateur> friends = new HashSet();

    @ManyToMany
    @JoinTable(name="utilisateur_friend_favorite",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Utilisateur> friendsFav = new HashSet();

    @ManyToMany
    @JoinTable(name="utilisateur_langueParle",
            joinColumns = @JoinColumn(name = "utilisateur_id"),
            inverseJoinColumns = @JoinColumn(name = "langue_id")
    )
    private Set<Langue> languesParle = new HashSet();

    @ManyToMany
    @JoinTable(name="utilisateur_langueApprise",
            joinColumns = @JoinColumn(name = "utilisateur_id"),
            inverseJoinColumns = @JoinColumn(name = "langue_id")
    )
    private Set<Langue> languesApprise= new HashSet();

    //relation OneToMany
    @OneToMany(mappedBy = "utilisateur")
    private Set<Reference> references;
    @OneToMany(mappedBy = "utilisateur")
    private Set<Forum> forums;
    @OneToMany(mappedBy = "utilisateur")
    private Set<Post> posts;
    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Lien> liens;

    public Utilisateur() {
    }

    public Utilisateur(Integer id) {
        this.id = id;
    }

    public Utilisateur(int id, String userName, String motDePasse, String courriel, String photo, boolean active) {
        this.id = id;
        this.userName = userName;
        this.motDePasse = motDePasse;
        this.courriel = courriel;
        this.photo = photo;
        this.active = active;
        friends = new HashSet();
        friendsFav = new HashSet();
        languesParle = new HashSet();
        languesApprise = new HashSet();
    }

    public Utilisateur(String userName, String motDePasse, String courriel, String photo, boolean active) {
        this.userName = userName;
        this.motDePasse = motDePasse;
        this.courriel = courriel;
        this.photo = photo;
        this.active = active;
        friends = new HashSet();
        friendsFav = new HashSet();
        languesParle = new HashSet();
        languesApprise = new HashSet();
    }

    public Utilisateur( String userName, String courriel, String motDePasse, String photo, String status, boolean active) {
        this.userName = userName;
        this.courriel = courriel;
        this.motDePasse = motDePasse;
        this.photo = photo;
        this.status = status;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getCourriel() {
        return courriel;
    }

    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isActive() {
        return active;
    }


    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Utilisateur> getFriends() {
        return friends;
    }
    public void setFriends(Set<Utilisateur> utilisateurs) {
        this.friends = utilisateurs;
    }
    public void ajouter(Utilisateur utilisateur){
        this.friends.add(utilisateur);
    }

    public Set<Utilisateur> getFriendsFav() {
        return friendsFav;
    }

    public void setFriendsFav(Set<Utilisateur> friendsFav) {
        this.friendsFav = friendsFav;
    }

    public Set<Langue> getLanguesParle() {
        return languesParle;
    }
    public void setLanguesParle(Set<Langue> langues) {
        this.languesParle = langues;
    }

    public Set<Langue> getLanguesApprise() {
        return languesApprise;
    }
    public void setLanguesApprise(Set<Langue> languesApprise) {
        this.languesApprise = languesApprise;
    }

    public void ajouterLangueParle(Langue langue){
        this.languesParle.add(langue);
    }
    public void ajouterLangueApprise(Langue langue){ this.languesApprise.add(langue); }
    public Set<Reference> getReferences() {
        return references;
    }

    public Set<Forum> getForums() {
        return forums;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public Set<Lien> getLiens() {
        return liens;
    }

    public boolean seConnecter(String username, String password){
        if (this.getUserName().equals(username) && this.getMotDePasse().equals(password)){
            return true;
        }
        else return false;
    }

    public String afficherTitreDesColonnes() {
        String message = "";
        message = String.format(" %-10s  %30s %15s %15s %15s %25s", "Id", "Email", "Active", "Nom",
                "Password", "Photo");
        message+="\n --------------------------------------------------------------------------------------------------------------------------------------";
        return message;
    }


    @Override
    public String toString() {
        String message = "";
        message = String.format(" %-10d  %30s %15b %15s %15s %25s ",this.id,this.courriel, this.status,this.userName, this.motDePasse, this.photo);
        return message; }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Utilisateur other = (Utilisateur) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }


}

