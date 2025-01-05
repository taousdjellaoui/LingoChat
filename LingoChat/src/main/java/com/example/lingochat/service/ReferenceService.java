package com.example.lingochat.service;

import com.example.lingochat.entities.Lien;
import com.example.lingochat.entities.Post;
import com.example.lingochat.entities.Reference;
import com.example.lingochat.repos.PostRepository;
import com.example.lingochat.repos.ReferenceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ReferenceService {
    @Autowired
    ReferenceRepository repos;

    public List<Reference> findReferenceByUtilisateurId (int id){
        return ( List<Reference> ) repos.findReferenceByUtilisateurId(id);
    }
    public Reference enregistrerRef(Reference reference) {
        return repos.save(reference);
    }
}
