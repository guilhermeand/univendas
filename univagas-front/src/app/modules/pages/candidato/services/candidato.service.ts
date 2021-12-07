import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../../../../environments/environment";
import {Candidato} from "../models/candidato";

@Injectable({
  providedIn: 'root'
})
export class CandidatoService {

  constructor(
    private http: HttpClient,
  ) { }

  list(): Observable<Array<Candidato>> {
    return this.http.get<Array<Candidato>>(`${environment.API_URL}/candidato/list`);
  }

  save(candidato: Candidato): Observable<any> {
    return this.http.post<Candidato>(`${environment.API_URL}/candidato`, candidato);
  }

  update(candidato: Candidato, id: string): Observable<any> {
    return this.http.patch<Candidato>(`${environment.API_URL}/candidato/${id}`, candidato);
  }

  find(id: string): Observable<Candidato> {
    return this.http.get<Candidato>(`${environment.API_URL}/candidato/${id}`);
  }

  delete(id: number):  Observable<Candidato> {
    return this.http.delete<Candidato>(`${environment.API_URL}/candidato/${id}`);
  }
}
