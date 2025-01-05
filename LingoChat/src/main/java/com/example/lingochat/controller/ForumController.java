package com.example.lingochat.controller;

import com.example.lingochat.entities.Forum;
import com.example.lingochat.entities.Post;
import com.example.lingochat.entities.Utilisateur;
import com.example.lingochat.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

@Controller
public class ForumController {
    @Autowired
    ForumService service;
    @Autowired
    PostService servicePost;
    @Autowired
    UtilisateurService serviceUser;

    @GetMapping("/community")
    public String afficherForums(Model model){
        List<Forum> listForum = service.afficherForum();
        model.addAttribute("listForum",listForum);
        return "community";
    }
    @GetMapping("/community/recherche")
    public String rechercherForum(Model model, @Param("keyword") String keyword) {
        List<Forum> listForum = service.afficherForumByName(keyword);

        model.addAttribute("listForum",listForum);
        model.addAttribute("keyword", keyword);
        return "community";
    }
    @GetMapping("/forum_{id}")
    public String afficherForum(@PathVariable(name = "id" ) Integer id, Model model){
        Forum forum = service.trouverForum(id);
        List<Post> listPost = servicePost.findPostsByForumId(id);
        //séparer le post original et les réponses -> ne pas avoir le post de l'auteur (listpost[0])
        Post post = listPost.get(0);
        listPost = listPost.stream().skip(1).collect(Collectors.toList());

        model.addAttribute("forum",forum);
        model.addAttribute("post",post);
        model.addAttribute("listPost",listPost);
        return "forum";
    }

    @PostMapping("/post/save")
    public String ajouterPost(@RequestParam("forumId") Integer id, @RequestParam("description") String description, HttpSession session) throws UtilisateurNotFoundException {
        int userId = Integer.parseInt(session.getAttribute("id").toString());
        Utilisateur utilisateur = serviceUser.trouverUtilisateur(userId);
        Forum forum = service.trouverForum(id);
        Post post = new Post();
        post.setUtilisateur(utilisateur);
        post.setDescription(description);
        post.setForum(forum);
        servicePost.enregistrerPost(post);
        return "redirect:/forum_" + id;
    }

    @GetMapping("/forum_create")
    public String afficherFormualireForum(Model model){
        Forum forum = new Forum();
        model.addAttribute("forum",forum);
        return "forum_create";
    }
    @PostMapping("/forum/save")
    public String ajouterForum(Forum forum, @RequestParam("postDescription") String postDescription, RedirectAttributes redirectAttributes, HttpSession session) throws UtilisateurNotFoundException {
        int userId = Integer.parseInt(session.getAttribute("id").toString());
        Utilisateur utilisateur = serviceUser.trouverUtilisateur(userId);
        forum.setUtilisateur(utilisateur);
        Post post = new Post();
        post.setForum(forum);
        post.setUtilisateur(utilisateur);
        post.setDescription(postDescription);
        servicePost.enregistrerPost(post);
        forum.ajouterPost(post);
        service.enregistrerForum(forum);
        return "redirect:/community";
    }
    @GetMapping("/forum_{fid}/post_{pid}/delete")
    public String supprimerPost(@PathVariable(name = "fid") Integer forumId, @PathVariable(name = "pid") Integer postId, Model model, HttpSession session) throws UtilisateurNotFoundException {
        servicePost.deletePost(forumId,postId);
        return "redirect:/forum_" + forumId;
    }
    @GetMapping("/forum_{fid}/delete")
    public String supprimerForum(@PathVariable(name = "fid") Integer forumId, Model model, HttpSession session) throws UtilisateurNotFoundException {
        service.deleteForum(forumId);
        return "redirect:/community";
    }

}
