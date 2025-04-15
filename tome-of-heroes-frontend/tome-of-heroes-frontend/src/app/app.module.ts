import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import {ApiService} from "./services/api/api.service";
import { HomeComponent } from './pages/home/home.component';

const routes: Routes = [
    { path: '', component: HomeComponent },
    // { path: 'character-list', component: CharacterListComponent },
    // { path: 'character-create', component: CharacterCreateComponent }
];

@NgModule({
    declarations: [AppComponent],
    imports: [BrowserModule, HttpClientModule, RouterModule.forRoot(routes)],
    providers: [ApiService],
    bootstrap: [AppComponent]
})
export class AppModule { }