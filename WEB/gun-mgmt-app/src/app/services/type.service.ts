import { HttpClient } from '@angular/common/http';
import { Injectable, Type } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { DictionaryData } from '../types/DictionaryData';
import { DictionaryDataInput } from '../types/DictionaryDataInput';

@Injectable({
  providedIn: 'root'
})
export class TypeService {
  private apiEndpointUrl = `${environment.apiUrl}/types`;

  constructor(private http: HttpClient) { }

  public getAllTypes() :Observable<DictionaryData[]> {
    return this.http.get<DictionaryData[]>(`${this.apiEndpointUrl}`);
  }

  public getTypeById(id: number) :Observable<DictionaryData> {
    return this.http.get<DictionaryData>(`${this.apiEndpointUrl}/${id}`);
  }

  public addType(typeInput: DictionaryDataInput) :Observable<DictionaryData> {
    return this.http.post<DictionaryData>(`${this.apiEndpointUrl}/add`, typeInput);
  }

  public updateType(type: DictionaryData) :Observable<DictionaryData> {
    return this.http.put<DictionaryData>(`${this.apiEndpointUrl}/update`, type);
  }

  public deleteType(id: number) :Observable<void> {
    return this.http.delete<void>(`${this.apiEndpointUrl}/delete/${id}`);
  }
}
