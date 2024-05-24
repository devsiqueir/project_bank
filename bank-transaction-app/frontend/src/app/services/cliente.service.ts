import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {
  private baseUrl = 'http://localhost:8080/clients';

  constructor(private http: HttpClient) { }

  cadastrarCliente(cliente: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}`, cliente);
  }

  consultarCliente(id: string): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/${id}`);
  }
}
