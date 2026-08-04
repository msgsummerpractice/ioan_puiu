import { Directive } from '@angular/core';
import { inject } from '@angular/core';
import { AuthService } from './auth-service';
import { TemplateRef, ViewContainerRef } from '@angular/core';
@Directive({
  selector: '[appAuthenticatedCheckDirective]',
})
export class AuthenticatedCheckDirective {
  private isAuthenticated = false;

  constructor(
    private templateRef: TemplateRef<unknown>,
    private viewContainer: ViewContainerRef,
  ) {
    this.viewContainer.clear();
    if (this.isAuthenticated) {
      this.viewContainer.createEmbeddedView(this.templateRef);
    }
  }
}
