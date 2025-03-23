import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { ApiService } from './services/api.service';
import { HomeComponent } from './pages/home/home.component';
import { CharacterListComponent } from './pages/character-list/character-list.component';
import { CharacterCreateComponent } from './pages/character-create/character-create.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    CharacterListComponent,
    CharacterCreateComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule
  ],
  providers: [ApiService],
  bootstrap: [AppComponent]
})
export class AppModule { }
