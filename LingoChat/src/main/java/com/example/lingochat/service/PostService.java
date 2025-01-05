package com.example.lingochat.service;

import com.example.lingochat.entities.Forum;
import com.example.lingochat.entities.Langue;
import com.example.lingochat.entities.Post;
import com.example.lingochat.entities.Utilisateur;
import com.example.lingochat.repos.ForumRepository;
import com.example.lingochat.repos.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PostService {
    @Autowired
    PostRepository repos;
    @Autowired
    ForumRepository reposF;
    public List<Post> findPostsByForumId(int id){
        return ( List<Post> ) repos.findPostsByForumId(id);
    }
    public Post enregistrerPost(Post post) {
        return repos.save(post);
    }
    public void deletePost(Integer forumId,Integer PostId){
        Forum forum = reposF.findById(forumId).orElseThrow(() -> new RuntimeException("Forum not found"));
        Post post = repos.findById(PostId).orElseThrow(() -> new RuntimeException("Post not found"));

        forum.getPosts().remove(post);
        reposF.save(forum);
    }

}
