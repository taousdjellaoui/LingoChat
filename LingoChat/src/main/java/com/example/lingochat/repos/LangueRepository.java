package com.example.lingochat.repos;

import com.example.lingochat.entities.Langue;
import com.example.lingochat.entities.Reference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LangueRepository extends JpaRepository<Langue,Integer> {
    @Query("SELECT l FROM Utilisateur u JOIN u.languesParle l WHERE u.id = :userId")
    List<Langue> findLanguesParleByUserId(@Param("userId") Integer userId);

    @Query("SELECT l FROM Utilisateur u JOIN u.languesApprise l WHERE u.id = :userId")
    List<Langue> findLanguesAppriseByUserId(@Param("userId") Integer userId);

}
