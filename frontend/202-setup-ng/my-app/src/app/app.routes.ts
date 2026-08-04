import { provideRouter, Routes } from '@angular/router';
import { HomeComponent } from './home-component/home-component';
import { NotFoundComponent } from './not-found-component/not-found-component';
import { App } from './app';
import { bootstrapApplication } from '@angular/platform-browser';
import { authGuard } from './auth-guard';

export const routes: Routes = [
  { path: 'home', component: HomeComponent, canActivate: [authGuard] },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  {
    path: 'login',
    loadComponent: () => import('./login-form/login-form').then((m) => m.LoginFormComponent),
  },
  { path: '**', component: NotFoundComponent },
];
