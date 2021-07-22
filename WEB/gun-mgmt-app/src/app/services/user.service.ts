import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { BasicCRUDService } from '../types/BasicCRUDService';
import { User } from '../types/User';
import { UserInput } from '../types/UserInput';

@Injectable({
  providedIn: 'root'
})
export class UserService implements BasicCRUDService {
  private apiEndpointUrl = `${environment.apiUrl}/users`

  constructor(private http: HttpClient) { }

  whoAmI(): string {
    return "user";
  }

  public getAll() :Observable<User[]> {
    return this.http.get<User[]>(`${this.apiEndpointUrl}`);
  }

  public getById(id: number) :Observable<User> {
    return this.http.get<User>(`${this.apiEndpointUrl}/${id}`);
  }

  public add(inputObject: UserInput) :Observable<User> {
    return this.http.post<User>(`${this.apiEndpointUrl}/add`, inputObject);
  }

  public update(updatedObject: User) :Observable<User> {
    return this.http.put<User>(`${this.apiEndpointUrl}/update`, updatedObject);
  }

  public delete(id: number) :Observable<void> {
    return this.http.delete<void>(`${this.apiEndpointUrl}/delete/${id}`);
  }
}
