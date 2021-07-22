import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { BasicCRUDService } from '../types/BasicCRUDService';
import { Model } from '../types/Model';
import { ModelInput } from '../types/ModelInput';

@Injectable({
  providedIn: 'root'
})
export class ModelService implements BasicCRUDService {
  private apiEndpointUrl = `${environment.apiUrl}/models`

  constructor(private http: HttpClient) { }

  whoAmI(): string {
    return "model";
  }

  public getAll() :Observable<Model[]> {
    return this.http.get<Model[]>(`${this.apiEndpointUrl}`);
  }

  public getById(id: number) :Observable<Model> {
    return this.http.get<Model>(`${this.apiEndpointUrl}/${id}`);
  }

  public add(inputObject: ModelInput) :Observable<Model> {
    return this.http.post<Model>(`${this.apiEndpointUrl}`, inputObject);
  }

  public update(updatedObject: Model) :Observable<Model> {
    return this.http.put<Model>(`${this.apiEndpointUrl}`, updatedObject);
  }

  public delete(id: number) :Observable<void> {
    return this.http.delete<void>(`${this.apiEndpointUrl}/${id}`);
  }
}
