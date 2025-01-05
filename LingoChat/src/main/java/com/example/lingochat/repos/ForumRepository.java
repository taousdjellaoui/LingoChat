package com.example.lingochat.repos;

import com.example.lingochat.entities.Forum;
import com.example.lingochat.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ForumRepository extends JpaRepository<Forum,Integer> {
    //rechercher par nom
    @Query("SELECT f FROM Forum f WHERE f.nom LIKE %?1%")
    public List<Forum> findAllByName(String keyword);
}
