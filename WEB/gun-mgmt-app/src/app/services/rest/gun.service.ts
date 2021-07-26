import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { BasicCRUDService } from '../../shared/types/BasicCRUDService';
import { Gun } from '../../shared/types/Gun';
import { GunInput } from '../../shared/types/GunInput';

@Injectable({
  providedIn: 'root'
})
export class GunService implements BasicCRUDService {
  private apiEndpointUrl = `${environment.apiUrl}/guns`

  constructor(private http: HttpClient) { }

  public whoAmI(): string {
    return "gun";
  }

  public getAll() :Observable<Gun[]> {
    return this.http.get<Gun[]>(`${this.apiEndpointUrl}`);
  }

  public getById(id: number) :Observable<Gun> {
    return this.http.get<Gun>(`${this.apiEndpointUrl}/${id}`);
  }

  public add(inputObject: GunInput) :Observable<Gun> {
    return this.http.post<Gun>(`${this.apiEndpointUrl}`, inputObject);
  }

  public update(updatedObject: Gun) :Observable<Gun> {
    return this.http.put<Gun>(`${this.apiEndpointUrl}`, updatedObject);
  }

  public delete(id: number) :Observable<void> {
    return this.http.delete<void>(`${this.apiEndpointUrl}/${id}`);
  }
}
