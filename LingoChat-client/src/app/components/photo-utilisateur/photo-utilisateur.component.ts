import { Component, Input } from '@angular/core';
import { Utilisateur } from '../../models/utilisateur.model';

@Component({
  selector: 'app-photo-utilisateur',
  template: `
  <img [src]="'../../../assets/images/utilisateurs/' + utilisateur.photo">
  `,
  styles: `
    img {
    flex-shrink: 0;
    /* Empêche l'image de se rétrécir */
    height: 100%;
    /* Ensure the height is 100% */
    object-fit: cover;
    /* Ensure the image covers the entire space */
    border-radius: 50%;
    aspect-ratio: 1/1;
}`
})
export class PhotoUtilisateurComponent {
  @Input() utilisateur!: Utilisateur;
}
