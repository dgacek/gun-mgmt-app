import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { DictionaryData } from '../types/DictionaryData';
import { DictionaryDataInput } from '../types/DictionaryDataInput';

@Injectable({
  providedIn: 'root'
})
export class CaliberService {
  private apiEndpointUrl = `${environment.apiUrl}/calibers`;

  constructor(private http: HttpClient) { }

  public getAllCalibers() :Observable<DictionaryData[]> {
    return this.http.get<DictionaryData[]>(`${this.apiEndpointUrl}`);
  }

  public getCaliberById(id: number) :Observable<DictionaryData> {
    return this.http.get<DictionaryData>(`${this.apiEndpointUrl}/${id}`);
  }

  public addCaliber(caliberInput: DictionaryDataInput) :Observable<DictionaryData> {
    return this.http.post<DictionaryData>(`${this.apiEndpointUrl}/add`, caliberInput);
  }

  public updateCaliber(caliber: DictionaryData) :Observable<DictionaryData> {
    return this.http.put<DictionaryData>(`${this.apiEndpointUrl}/update`, caliber);
  }

  public deleteCaliber(id: number) :Observable<void> {
    return this.http.delete<void>(`${this.apiEndpointUrl}/delete/${id}`);
  }
}
