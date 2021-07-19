import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Gun } from '../models/Gun';
import { GunInput } from '../models/GunInput';

@Injectable({
  providedIn: 'root'
})
export class GunService {
  private apiEndpointUrl = `${environment.apiUrl}/guns`

  constructor(private http: HttpClient) { }

  public getAllGuns() :Observable<Gun[]> {
    return this.http.get<Gun[]>(`${this.apiEndpointUrl}`);
  }

  public getGunById(id: number) :Observable<Gun> {
    return this.http.get<Gun>(`${this.apiEndpointUrl}/${id}`);
  }

  public addGun(gunInput: GunInput) :Observable<Gun> {
    return this.http.post<Gun>(`${this.apiEndpointUrl}/add`, gunInput);
  }

  public updateGun(gun: Gun) :Observable<Gun> {
    return this.http.put<Gun>(`${this.apiEndpointUrl}/update`, gun);
  }

  public deleteGun(id: number) :Observable<void> {
    return this.http.delete<void>(`${this.apiEndpointUrl}/delete/${id}`);
  }
}
