import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Credenciais } from '../models/credenciais';
import { API_CONFIG } from '../config/standard-url';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  jwtService: JwtHelperService = new JwtHelperService();

  constructor(private httpClient: HttpClient) { }

  authenticate(creds: Credenciais) {
    return this.httpClient.post(`${API_CONFIG.BASE_URL}/login`, creds, {
      observe: 'response',
      responseType: 'text'
    });
  }

  successfullyLogin(token: string){
    localStorage.setItem('token', token);
  }

  isAuthenticated(): boolean {
    let token = localStorage.getItem('token');
    if(token != null) {
      return !this.jwtService.isTokenExpired(token);// true se expirado
    }
    return false;
  }
}
