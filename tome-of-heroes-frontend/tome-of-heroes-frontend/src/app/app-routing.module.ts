import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { CharacterListComponent } from './pages/character-list/character-list.component';
import { CharacterCreateComponent } from './pages/character-create/character-create.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'characters', component: CharacterListComponent },
  { path: 'character/create', component: CharacterCreateComponent },
  { path: '**', redirectTo: '' }
];