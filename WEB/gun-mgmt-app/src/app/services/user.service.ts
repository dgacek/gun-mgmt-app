import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/User';
import { UserInput } from '../models/UserInput';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiEndpointUrl = "http://localhost:8080/users"

  constructor(private http: HttpClient) { }

  public getAllUsers() :Observable<User[]> {
    return this.http.get<User[]>(`${this.apiEndpointUrl}`);
  }

  public getUserById(id: number) :Observable<User> {
    return this.http.get<User>(`${this.apiEndpointUrl}/${id}`);
  }

  public addUser(userInput: UserInput) :Observable<User> {
    return this.http.post<User>(`${this.apiEndpointUrl}/add`, userInput);
  }

  public updateUser(user: User) :Observable<User> {
    return this.http.put<User>(`${this.apiEndpointUrl}/update`, user);
  }

  public deleteUser(id: number) :Observable<void> {
    return this.http.delete<void>(`${this.apiEndpointUrl}/delete/${id}`);
  }
}
