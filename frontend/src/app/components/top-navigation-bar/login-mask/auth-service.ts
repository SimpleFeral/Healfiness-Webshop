import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthResponse, LoginRequestModel } from '../../../shared/model/authentication.model';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly authUrl = '/api/v1/auth';

  constructor(private readonly http: HttpClient) {}

  login(request: LoginRequestModel): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(this.authUrl, request);
  }
}
