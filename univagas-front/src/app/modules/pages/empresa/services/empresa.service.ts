import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../../../../environments/environment";
import {Empresa} from "../models/empresa";

@Injectable({
  providedIn: 'root'
})
export class EmpresaService {

  constructor(
    private http: HttpClient,
  ) { }

  list(): Observable<Array<Empresa>> {
    return this.http.get<Array<Empresa>>(`${environment.API_URL}/empresa/list`);
  }

  save(empresa: Empresa): Observable<any> {
    return this.http.post<Empresa>(`${environment.API_URL}/empresa`, empresa);
  }

  update(empresa: Empresa, id: string): Observable<any> {
    return this.http.patch<Empresa>(`${environment.API_URL}/empresa/${id}`, empresa);
  }

  find(id: string): Observable<Empresa> {
    return this.http.get<Empresa>(`${environment.API_URL}/empresa/${id}`);
  }

  delete(id: number):  Observable<Empresa> {
    return this.http.delete<Empresa>(`${environment.API_URL}/empresa/${id}`);
  }
}
