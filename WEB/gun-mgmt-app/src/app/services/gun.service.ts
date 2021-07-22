import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { BasicCRUDService } from '../types/BasicCRUDService';
import { Gun } from '../types/Gun';
import { GunInput } from '../types/GunInput';

@Injectable({
  providedIn: 'root'
})
export class GunService implements BasicCRUDService {
  private apiEndpointUrl = `${environment.apiUrl}/guns`

  constructor(private http: HttpClient) { }

  public getAll() :Observable<Gun[]> {
    return this.http.get<Gun[]>(`${this.apiEndpointUrl}`);
  }

  public getById(id: number) :Observable<Gun> {
    return this.http.get<Gun>(`${this.apiEndpointUrl}/${id}`);
  }

  public add(inputObject: GunInput) :Observable<Gun> {
    return this.http.post<Gun>(`${this.apiEndpointUrl}/add`, inputObject);
  }

  public update(updatedObject: Gun) :Observable<Gun> {
    return this.http.put<Gun>(`${this.apiEndpointUrl}/update`, updatedObject);
  }

  public delete(id: number) :Observable<void> {
    return this.http.delete<void>(`${this.apiEndpointUrl}/delete/${id}`);
  }
}
