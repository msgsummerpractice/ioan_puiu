import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-componenta-mea-puternica',
  imports: [],
  templateUrl: './componenta-mea-puternica.html',
  styleUrl: './componenta-mea-puternica.css',
})
export class ComponentaMeaPuternica {
  @Input()
  text: String = '';
}
