import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { BasicCRUDService } from '../types/BasicCRUDService';
import { Role } from '../types/Role';
import { RoleInput } from '../types/RoleInput';

@Injectable({
  providedIn: 'root'
})
export class RoleService implements BasicCRUDService {
  private apiEndpointUrl = `${environment.apiUrl}/roles`

  constructor(private http: HttpClient) { }

  whoAmI(): string {
    return "role";
  }

  public getAll() :Observable<Role[]> {
    return this.http.get<Role[]>(`${this.apiEndpointUrl}`);
  }

  public getById(id: number) :Observable<Role> {
    return this.http.get<Role>(`${this.apiEndpointUrl}/${id}`);
  }

  public add(inputObject: RoleInput) :Observable<Role> {
    return this.http.post<Role>(`${this.apiEndpointUrl}`, inputObject);
  }

  public update(updatedObject: Role) :Observable<Role> {
    return this.http.put<Role>(`${this.apiEndpointUrl}`, updatedObject);
  }

  public delete(id: number) :Observable<void> {
    return this.http.delete<void>(`${this.apiEndpointUrl}/${id}`);
  }
}
