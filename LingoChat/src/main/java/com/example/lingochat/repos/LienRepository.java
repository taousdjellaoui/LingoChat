package com.example.lingochat.repos;

import com.example.lingochat.entities.Lien;
import com.example.lingochat.entities.Reference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LienRepository extends JpaRepository<Lien,Integer> {
    @Query("SELECT l FROM Lien l WHERE l.utilisateur.id=:utilisateur_id")
    public List<Lien> findLienByUtilisateurId(@Param("utilisateur_id") Integer utilisateur_id);
}
