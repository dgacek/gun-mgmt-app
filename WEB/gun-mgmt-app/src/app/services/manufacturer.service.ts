import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { DictionaryData } from '../models/DictionaryData';
import { DictionaryDataInput } from '../models/DictionaryDataInput';

@Injectable({
  providedIn: 'root'
})
export class ManufacturerService {
  private apiEndpointUrl = `${environment.apiUrl}/manufacturers`;

  constructor(private http: HttpClient) { }

  public getAllManufacturers() :Observable<DictionaryData[]> {
    return this.http.get<DictionaryData[]>(`${this.apiEndpointUrl}`);
  }

  public getManufacturerById(id: number) :Observable<DictionaryData> {
    return this.http.get<DictionaryData>(`${this.apiEndpointUrl}/${id}`);
  }

  public addManufacturer(manufacturerInput: DictionaryDataInput) :Observable<DictionaryData> {
    return this.http.post<DictionaryData>(`${this.apiEndpointUrl}/add`, manufacturerInput);
  }

  public updateManufacturer(manufacturer: DictionaryData) :Observable<DictionaryData> {
    return this.http.put<DictionaryData>(`${this.apiEndpointUrl}/update`, manufacturer);
  }

  public deleteManufacturer(id: number) :Observable<void> {
    return this.http.delete<void>(`${this.apiEndpointUrl}/delete/${id}`);
  }
}
