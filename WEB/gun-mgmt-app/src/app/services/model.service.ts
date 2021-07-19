import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Model } from '../models/Model';
import { ModelInput } from '../models/ModelInput';

@Injectable({
  providedIn: 'root'
})
export class ModelService {
  private apiEndpointUrl = `${environment.apiUrl}/models`

  constructor(private http: HttpClient) { }

  public getAllModels() :Observable<Model[]> {
    return this.http.get<Model[]>(`${this.apiEndpointUrl}`);
  }

  public getModelById(id: number) :Observable<Model> {
    return this.http.get<Model>(`${this.apiEndpointUrl}/${id}`);
  }

  public addModel(modelInput: ModelInput) :Observable<Model> {
    return this.http.post<Model>(`${this.apiEndpointUrl}/add`, modelInput);
  }

  public updateModel(model: Model) :Observable<Model> {
    return this.http.put<Model>(`${this.apiEndpointUrl}/update`, model);
  }

  public deleteModel(id: number) :Observable<void> {
    return this.http.delete<void>(`${this.apiEndpointUrl}/delete/${id}`);
  }
}
