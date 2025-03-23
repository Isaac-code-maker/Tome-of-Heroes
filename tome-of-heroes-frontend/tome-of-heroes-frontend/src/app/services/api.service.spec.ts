import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private baseUrl = 'http://localhost:8080'; // Substitua pela URL do seu backend

  constructor(private http: HttpClient) {}

  getCharacters(): Observable<any> {
    return this.http.get(`${this.baseUrl}/characters`);
  }
}
