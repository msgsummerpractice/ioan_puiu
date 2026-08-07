import { Component, inject, signal } from '@angular/core';
import { CutePipe } from '../cute-pipe';
import { RouterOutlet } from '@angular/router';
import { MatButton } from '@angular/material/button';
import { MatToolbar } from '@angular/material/toolbar';
import { ComponentaMeaPuternica } from '../componenta-mea-puternica/componenta-mea-puternica';
import { dogscript } from '.././dogscript';
import { forkJoin } from 'rxjs/internal/observable/forkJoin';
import { Observable, tap } from 'rxjs';
import { MatIcon } from '@angular/material/icon';
import { AuthenticatedCheckDirective } from '.././authenticated-check-directive';
import { AuthService } from '../service/auth-service';

@Component({
  selector: 'app-home-component',
  imports: [
    RouterOutlet,
    MatButton,
    MatToolbar,
    MatIcon,
    AuthenticatedCheckDirective,
    ComponentaMeaPuternica,
    CutePipe,
  ],
  templateUrl: './home-component.html',
  styleUrl: './home-component.css',
})
export class HomeComponent {
  protected readonly authService = inject(AuthService);
  private dogScript = inject(dogscript);

  dogImages = signal<string[]>([]);

  loadAllDogs() {
    const request = [
      this.dogScript.getRandomDogImage(),
      this.dogScript.getRandomDogImage(),
      this.dogScript.getRandomDogImage(),
    ];
    forkJoin(request).subscribe((results) => {
      const images = results.map((dog) => dog.message);
      this.dogImages.set(images);
    });
  }

  getRandomDogImage(index: number): void {
    this.dogScript.getRandomDogImage().subscribe((response) => {
      this.dogImages.update((images) => {
        return images.map((img, i) => (i === index ? response.message : img));
      });
    });
  }
}
