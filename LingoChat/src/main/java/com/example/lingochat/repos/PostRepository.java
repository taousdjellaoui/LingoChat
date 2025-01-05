package com.example.lingochat.repos;

import com.example.lingochat.entities.Post;
import com.example.lingochat.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {
    @Query("SELECT p FROM Post p WHERE p.forum.id=:forum_id")
    public List<Post> findPostsByForumId(@Param("forum_id") Integer forum_id);
}
