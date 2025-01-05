import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Utilisateur } from "../models/utilisateur.model";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
  })
  export class UtilisateurRepository {
     // URL de l'API pour les utilisateurs
    // private apiUrl = "http://localhost:3000/utilisateurs";
    private apiUrl = "http://localhost:8080/api/utilisateurs";
     // Constructeur du service, injecte HttpClient
     // HttpClient est un service Angular qui permet d'effectuer des requêtes HTTP.
     constructor(private http: HttpClient) { }
  
     // Obtient la liste des utilisateurs de manière asynchrone
     // Observable est une classe du module rxjs qui représente une séquence d'événements asynchrones.
     // Dans ce contexte, il est utilisé pour gérer la réponse asynchrone de la requête HTTP.
     getUtilisateurs(): Observable<Utilisateur[]> {
       return this.http.get<Utilisateur[]>(this.apiUrl);
     }
    }
  