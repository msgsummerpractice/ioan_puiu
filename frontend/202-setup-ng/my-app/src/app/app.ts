import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MatButton } from '@angular/material/button';
import { MatToolbar } from '@angular/material/toolbar';
import { ComponentaMeaPuternica } from './componenta-mea-puternica/componenta-mea-puternica';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, MatButton, MatToolbar, ComponentaMeaPuternica],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {
  protected readonly title = signal('my-app');
}
