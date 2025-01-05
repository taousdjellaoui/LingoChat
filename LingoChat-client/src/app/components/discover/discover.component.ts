import { Component, OnInit } from '@angular/core';
import { Utilisateur } from '../../models/utilisateur.model';
import { UtilisateurService } from '../../services/utilisateur.service';

@Component({
  selector: 'app-discover',
  templateUrl: './discover.component.html',
  styleUrl: './discover.component.css'
})
export class DiscoverComponent {
  private utilisateurs_: Utilisateur[]=[]
  constructor(private service : UtilisateurService){}
 
  get utilisateurs(){
    this.utilisateurs_=this.service.getUtilisateurs()
    console.log(this.utilisateurs_)
   return this.utilisateurs_
  }
}
