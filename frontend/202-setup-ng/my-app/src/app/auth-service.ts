import { inject, Injectable, Service, signal } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly router = inject(Router);

  isAuthenticated = signal(false);

  login() {
    this.isAuthenticated.set(true);
    return this.router.navigate(['home']);
  }

  logout() {
    this.isAuthenticated.set(false);
    return this.router.navigate(['login']);
  }
}
