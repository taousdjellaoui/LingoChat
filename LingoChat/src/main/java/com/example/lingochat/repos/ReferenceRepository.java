package com.example.lingochat.repos;

import com.example.lingochat.entities.Langue;
import com.example.lingochat.entities.Post;
import com.example.lingochat.entities.Reference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReferenceRepository extends JpaRepository<Reference,Integer> {
    @Query("SELECT r FROM Reference r WHERE r.utilisateur.id=:utilisateur_id")
    public List<Reference> findReferenceByUtilisateurId(@Param("utilisateur_id") Integer utilisateur_id);
}
