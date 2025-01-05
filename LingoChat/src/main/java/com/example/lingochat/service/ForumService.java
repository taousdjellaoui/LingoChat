package com.example.lingochat.service;

import com.example.lingochat.entities.Forum;
import com.example.lingochat.entities.Post;
import com.example.lingochat.repos.ForumRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ForumService {
    @Autowired
    ForumRepository repos;
    public List<Forum> afficherForum(){
        return ( List<Forum> ) repos.findAll();
    }

    public List<Forum> afficherForumByName(String keyword){
        return ( List<Forum> ) repos.findAllByName(keyword);
    }
    public Forum trouverForum(Integer id) {
            Forum forum = repos.findById(id).get();
            return forum;
    }
    public Forum enregistrerForum(Forum forum) {
        return repos.save(forum);
    }
    public void deleteForum(Integer forumId){
        repos.deleteById(forumId);
    }

}
