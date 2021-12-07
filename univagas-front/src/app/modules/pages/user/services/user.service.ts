import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../models/user";
import {environment} from "../../../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private http: HttpClient,
  ) { }

  list(): Observable<Array<User>> {
    return this.http.get<Array<User>>(`${environment.API_URL}/usuario/list`);
  }

  save(user: User): Observable<any> {
    return this.http.post<User>(`${environment.API_URL}/usuario`, user);
  }

  update(user: User, id: string): Observable<any> {
    return this.http.patch<User>(`${environment.API_URL}/usuario/${id}`, user);
  }

  find(id: string): Observable<User> {
    return this.http.get<User>(`${environment.API_URL}/usuario/${id}`);
  }

  delete(id: number):  Observable<User> {
    return this.http.delete<User>(`${environment.API_URL}/usuario/${id}`);
  }
}
