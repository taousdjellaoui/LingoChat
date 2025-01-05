import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DiscoverComponent } from './components/discover/discover.component';
import { ProfilComponent } from './components/profil/profil.component';
import { AccueilComponent } from './components/accueil/accueil.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { TableUtilisateurComponent } from './components/table-utilisateur/table-utilisateur.component';
import { PhotoUtilisateurComponent } from './components/photo-utilisateur/photo-utilisateur.component';
import { HttpClientModule } from '@angular/common/http';


@NgModule({
  declarations: [
    AppComponent,
    DiscoverComponent,
    ProfilComponent,
    AccueilComponent,
    HeaderComponent,
    FooterComponent,
    TableUtilisateurComponent,
    PhotoUtilisateurComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
