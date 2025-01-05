package com.example.lingochat.repos;

import com.example.lingochat.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,Integer> {

    //trouver par courriel
    @Query("SELECT u FROM Utilisateur u WHERE u.courriel=:courriel")
    public Utilisateur getUtilisateurByCourriel(@Param("courriel") String courriel);
    @Query("SELECT u FROM Utilisateur u WHERE u.id=:id")
    public Utilisateur getUtilisateurById(@Param("id") Integer id);

    //trouver par courriel et mdp (se connecter)
    @Query("SELECT u FROM Utilisateur u WHERE u.courriel=:courriel and u.motDePasse=:motDePasse")
    public Utilisateur getUtilisateurByCourrielAndMotDePasse(@Param("courriel") String courriel, @Param("motDePasse") String motDePasse);

    //bloquer/debloquer
    //2 pour le 2eme paramètre active et 1 pour le 1e paramètre id
    @Query("UPDATE Utilisateur u SET u.active=?2 WHERE u.id=?1")
    @Modifying
    public void updateActiveStatus(Integer id, boolean active);
    @Query("UPDATE Utilisateur u SET u.courriel = ?2 WHERE u.id = ?1")
    @Modifying
    public void changerEmail(Integer id, String courriel);
    @Query("UPDATE Utilisateur u SET u.motDePasse = ?2 WHERE u.id = ?1")
    @Modifying
    public void changerPassword(Integer id, String password);

    //upgrade un utilisateur en membreplus ou unupgrade en utilisateur (ici j'ai pris le status au complet)

    @Query("UPDATE Utilisateur u SET u.status=?2 WHERE u.id=?1")
    @Modifying
    public void updateStatus(  Utilisateur utilisateur);


    //rechercher par nom
    @Query("SELECT u FROM Utilisateur u WHERE u.userName LIKE %?1%")
    public List<Utilisateur> findAllByName(String keyword);

    @Query("SELECT DISTINCT f FROM Utilisateur u JOIN u.friends f WHERE u.id = :userId OR f.id != :userId")
    public List<Utilisateur> findAllFriendsByUserId(@Param("userId") Integer userId);

    @Query("SELECT DISTINCT u FROM Utilisateur u JOIN u.friends f WHERE f.id = :userId OR u.id != :userId")
    public List<Utilisateur> findAllFriendsByUserId2(@Param("userId") Integer userId);

    @Query("SELECT DISTINCT f FROM Utilisateur u JOIN u.friends f WHERE u.id = :idUser AND f.userName LIKE %:keyword%")
    public List<Utilisateur> findAllFriends(@Param("keyword") String keyword, @Param("idUser") Integer idUser);

    @Query("SELECT DISTINCT f FROM Utilisateur u JOIN u.friends f WHERE u.id = :idUser")
    public List<Utilisateur> findAllFriends(@Param("idUser") Integer idUser);
    //rechercer par status mais jsp si c'est la même chose que juste faire <public List<Utilisateur> findByStatus(Status status);>
    // qui est un peu plus en bas


    public Utilisateur findByUserName(String userName);
    public Utilisateur findByUserNameAndMotDePasse(String userName,String motDePasse);


    public List<Utilisateur> findByStatus(String status);



    public List<Utilisateur> findByPhoto(String photo);

    @Query("SELECT u FROM Utilisateur u WHERE u.photo=:fileName")
    public List<Utilisateur> findByFileName(@Param("fileName") String fileName);



    // Cette méthode suit la convention spécifié par Spring Data JPA
    public Long countById(Integer id);

}
