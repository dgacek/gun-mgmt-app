import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import jwtDecode from 'jwt-decode';
import { AuthToken } from '../shared/types/AuthToken';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private jwtHelper: JwtHelperService, private router: Router) { }

  public logout(): void {
    localStorage.removeItem("token");
    this.router.navigate(["/login"]);
  }
  
  public getAuthToken(): string {
    return localStorage.getItem("token")!;
  }

  public setAuthToken(token: string) {
    localStorage.setItem("token", token);
  }
  
  public isAuthenticated(): boolean {
    return !this.jwtHelper.isTokenExpired(this.getAuthToken());
  }

  public getPermissions(): string[] {
    const tokenPayload: AuthToken = jwtDecode(this.getAuthToken());
    return tokenPayload.permissions;
  }
}
