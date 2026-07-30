import { Component, inject, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MatButton } from '@angular/material/button';
import { MatToolbar } from '@angular/material/toolbar';
import { ComponentaMeaPuternica } from '../componenta-mea-puternica/componenta-mea-puternica';
import { dogscript } from '.././dogscript';
import { forkJoin } from 'rxjs/internal/observable/forkJoin';
import { Observable } from 'rxjs';
import { MatIcon } from '@angular/material/icon';
import { AuthenticatedCheckDirective } from '.././authenticated-check-directive';

@Component({
  selector: 'app-home-component',
  imports: [
    RouterOutlet,
    MatButton,
    MatToolbar,
    MatIcon,
    AuthenticatedCheckDirective,
    ComponentaMeaPuternica,
  ],
  templateUrl: './home-component.html',
  styleUrl: './home-component.css',
})
export class HomeComponent {
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
