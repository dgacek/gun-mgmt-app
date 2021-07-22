import { Observable } from "rxjs";

export interface BasicCRUDService {
  getAll() :Observable<any>;
  getById(id: number) :Observable<any>;
  add(inputObject: any): Observable<any>;
  update(updatedObject: any): Observable<any>;
  delete(id: number): Observable<void>;
}