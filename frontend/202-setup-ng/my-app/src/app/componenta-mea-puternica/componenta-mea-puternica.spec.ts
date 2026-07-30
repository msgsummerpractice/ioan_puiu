import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ComponentaMeaPuternica } from './componenta-mea-puternica';

describe('ComponentaMeaPuternica', () => {
  let component: ComponentaMeaPuternica;
  let fixture: ComponentFixture<ComponentaMeaPuternica>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ComponentaMeaPuternica],
    }).compileComponents();

    fixture = TestBed.createComponent(ComponentaMeaPuternica);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
