import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import jwtDecode from 'jwt-decode';
import { AuthToken } from '../shared/types/AuthToken';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private jwtHelper: JwtHelperService) { }

  public logout(): void {
    localStorage.removeItem("token");
    window.location.reload();
  }

  public isAuthenticated(): boolean {
    return !this.jwtHelper.isTokenExpired(localStorage.getItem("token")!);
  }

  public getPermissions(): string[] {
    const tokenPayload: AuthToken = jwtDecode(localStorage.getItem("token")!);
    return tokenPayload.permissions;
  }
}
