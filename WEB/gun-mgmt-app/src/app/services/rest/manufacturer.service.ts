import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { BasicCRUDService } from '../../types/BasicCRUDService';
import { DictionaryData } from '../../types/DictionaryData';
import { DictionaryDataInput } from '../../types/DictionaryDataInput';

@Injectable({
  providedIn: 'root'
})
export class ManufacturerService implements BasicCRUDService {
  private apiEndpointUrl = `${environment.apiUrl}/dictionary/manufacturers`;

  constructor(private http: HttpClient) { }

  public whoAmI(): string {
    return "manufacturer";
  }

  public getAll() :Observable<DictionaryData[]> {
    return this.http.get<DictionaryData[]>(`${this.apiEndpointUrl}`);
  }

  public getById(id: number) :Observable<DictionaryData> {
    return this.http.get<DictionaryData>(`${this.apiEndpointUrl}/${id}`);
  }

  public add(inputObject: DictionaryDataInput) :Observable<DictionaryData> {
    return this.http.post<DictionaryData>(`${this.apiEndpointUrl}`, inputObject);
  }

  public update(updatedObject: DictionaryData) :Observable<DictionaryData> {
    return this.http.put<DictionaryData>(`${this.apiEndpointUrl}`, updatedObject);
  }

  public delete(id: number) :Observable<void> {
    return this.http.delete<void>(`${this.apiEndpointUrl}/${id}`);
  }
}
