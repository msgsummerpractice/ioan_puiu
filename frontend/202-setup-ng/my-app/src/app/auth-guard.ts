import { CanActivateFn } from '@angular/router';
import { inject } from '@angular/core';
import { AuthService } from './auth-service';
import { Router, RedirectCommand } from '@angular/router';
export const authGuard: CanActivateFn = () => {
  const authService = inject(AuthService);
  const router = inject(Router);

  // can return a GuardResult, Observable<GuardResult>, or Promise<GuardResult>,
  // where GuardResult can be a boolean, UrlTree or a RedirectCommand

  if (authService.isAuthenticated()) {
    return true; // allow access
  }

  // redirect to login if not authenticated
  return new RedirectCommand(router.parseUrl('login'));
};
