import { Component, inject, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MatButton } from '@angular/material/button';
import { MatToolbar } from '@angular/material/toolbar';
import { ComponentaMeaPuternica } from './componenta-mea-puternica/componenta-mea-puternica';
import { dogscript } from './dogscript';
import { forkJoin } from 'rxjs/internal/observable/forkJoin';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, MatButton, MatToolbar, ComponentaMeaPuternica],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {
  protected readonly title = signal('my-app');
  private dogScript = inject(dogscript);

  dogImage = signal<string[]>([]);

  loadDogs() {
    const request = [
      this.dogScript.getRandomDogImage(),
      this.dogScript.getRandomDogImage(),
      this.dogScript.getRandomDogImage(),
    ];

    forkJoin(request).subscribe((results) => {
      const images = results.map((dog) => dog.message);
      this.dogImage.set(images);
    });
  }

  getRandomDogImage(index: number): void {
    this.dogScript.getRandomDogImage().subscribe((response) => {
      const images = this.dogImage();
      images[index] = response.message;
      this.dogImage.set(images);
      console.log('Dog image updated at index', index, ':', response.message);
      console.log('Current dog images:', this.dogImage());
    });
  }
}
