import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthRequest } from 'src/app/shared/types/AuthRequest';
import { AuthResponse } from 'src/app/shared/types/AuthResponse';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private apiEndpointUrl = `${environment.apiUrl}/login`;

  constructor(private http: HttpClient) { }

  public login(authRequest: AuthRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(this.apiEndpointUrl, authRequest);
  }
}
