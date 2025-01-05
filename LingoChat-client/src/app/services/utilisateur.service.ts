import { Injectable } from '@angular/core';
import { Utilisateur } from '../models/utilisateur.model';
import { UtilisateurRepository } from '../repository/utilisateur.repository';

@Injectable({
  providedIn: 'root'
})
export class UtilisateurService {
  private utilisateurs: Utilisateur[] = []; // Tableau local d'utilisateurs
  private languesParle: string[] = []; // Tableau local des rôles
  private languesApprise: string[] = []; // Tableau local des rôles

  constructor(private repository: UtilisateurRepository) {
    repository.getUtilisateurs().subscribe((data) => {
      // Met à jour le tableau local d'utilisateurs avec les données reçues du service
      this.utilisateurs = data;
      console.log(data)
      this.languesParle = this.getLangueParleFromUtilisateurs(data);
      this.languesApprise= this.getLangueAppriseFromUtilisateurs(data);
   });
}
private getLangueParleFromUtilisateurs(utilisateurs: Utilisateur[]): string[] {
  const uniqueLanguesSet = new Set<string>(); // Utilisation d'un ensemble pour stocker des valeurs uniques
  utilisateurs.forEach((utilisateur) => {
    if (utilisateur.languesParle && utilisateur.languesParle.length > 0) {
      // Si l'utilisateur a des langues, ajoute chaque nom de langue à l'ensemble
      utilisateur.languesParle.forEach(langue => {
        if (langue && langue.nom) { // Vérifie si role est défini et si son nom est une chaîne de caractères
          uniqueLanguesSet.add(langue.nom);
        }
      });
    }
  });
  return Array.from(uniqueLanguesSet).sort(); // Transforme l'ensemble en tableau et le trie par ordre alphabétique
}

private getLangueAppriseFromUtilisateurs(utilisateurs: Utilisateur[]): string[] {
  const uniqueLanguesSet = new Set<string>(); // Utilisation d'un ensemble pour stocker des valeurs uniques
  utilisateurs.forEach((utilisateur) => {
    if (utilisateur.languesApprise && utilisateur.languesApprise.length > 0) {
      // Si l'utilisateur a des langues, ajoute chaque nom de langue à l'ensemble
      utilisateur.languesApprise.forEach(langue => {
        if (langue && langue.nom) { // Vérifie si role est défini et si son nom est une chaîne de caractères
          uniqueLanguesSet.add(langue.nom);
        }
      });
    }
  });
  return Array.from(uniqueLanguesSet).sort(); // Transforme l'ensemble en tableau et le trie par ordre alphabétique
}
getLanguesParle(): string[] {
  return this.languesParle;
}
getLanguesApprise(): string[] {
  return this.languesApprise;
}
getUtilisateurs(): Utilisateur[] {
  return this.utilisateurs
}
}
