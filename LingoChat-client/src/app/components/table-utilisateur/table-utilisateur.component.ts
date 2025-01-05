import { Component, Input } from '@angular/core';
import { Utilisateur } from '../../models/utilisateur.model';
import { UtilisateurRepository } from '../../repository/utilisateur.repository';

@Component({
  selector: 'app-table-utilisateur',
  templateUrl: './table-utilisateur.component.html',
  styleUrl: './table-utilisateur.component.css'
})
export class TableUtilisateurComponent {
  @Input() utilisateurs: Utilisateur[] = [];

  constructor(private repository: UtilisateurRepository) {}
  ngOnInit(){
    console.log(this.utilisateurs)
  }
}

