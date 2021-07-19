import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Role } from '../models/Role';
import { RoleInput } from '../models/RoleInput';

@Injectable({
  providedIn: 'root'
})
export class RoleService {
  private apiEndpointUrl = `${environment.apiUrl}/roles`

  constructor(private http: HttpClient) { }

  public getAllRoles() :Observable<Role[]> {
    return this.http.get<Role[]>(`${this.apiEndpointUrl}`);
  }

  public getRoleById(id: number) :Observable<Role> {
    return this.http.get<Role>(`${this.apiEndpointUrl}/${id}`);
  }

  public addRole(roleInput: RoleInput) :Observable<Role> {
    return this.http.post<Role>(`${this.apiEndpointUrl}/add`, roleInput);
  }

  public updateRole(role: Role) :Observable<Role> {
    return this.http.put<Role>(`${this.apiEndpointUrl}/update`, role);
  }

  public deleteRole(id: number) :Observable<void> {
    return this.http.delete<void>(`${this.apiEndpointUrl}/delete/${id}`);
  }
}
