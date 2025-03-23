import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { ApiService } from './services/api.service';
import { Character } from './models/character.model';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  title = 'Tome of Heroes';
  characters: Character[] = [];
  error: string | null = null;

  constructor(private apiService: ApiService) {}

  ngOnInit() {
    console.log('Aplicação iniciada');
    console.log('Iniciando carregamento dos personagens...');
    this.apiService.getCharacters().subscribe({
      next: (data) => {
        console.log('Personagens carregados:', data);
        this.characters = data;
      },
      error: (error) => {
        console.error('Erro ao carregar personagens:', error);
        this.error = 'Erro ao carregar personagens. Por favor, tente novamente mais tarde.';
      }
    });
  }

  criarPersonagem() {
    // TODO: Implementar navegação para a página de criação de personagem
    console.log('Criar novo personagem');
  }

  listarPersonagens() {
    // TODO: Implementar navegação para a página de listagem de personagens
    console.log('Listar personagens');
  }
}
