import { Component, inject } from '@angular/core';
import { AuthService } from '../auth-service';
import { Router } from '@angular/router';
import {
  FormBuilder,
  NonNullableFormBuilder,
  Validators,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
} from '@angular/forms';

type LoginForm = {
  username: FormControl<string>;
  password: FormControl<string>;
};

@Component({
  selector: 'login-form',
  imports: [ReactiveFormsModule],
  templateUrl: './login-form.html',
  styleUrl: './login-form.css',
})
export class LoginFormComponent {
  private readonly router = inject(Router);
  protected readonly authService = inject(AuthService);

  // Using NonNullable builder ensures values are never null
  private readonly _formBuilder = inject(NonNullableFormBuilder);

  protected readonly loginFormGroup = this._formBuilder.group<LoginForm>({
    username: this._formBuilder.control('', Validators.required),
    password: this._formBuilder.control('', Validators.required),
    // In the case of a normal FormBuilder, we might still want to mark some of our controls as non-nullable,
    // and we can do it by passing it as an option like this:
    // count: this._formBuilder.control(1, { nonNullable: true })
    // Note that the default value of nonNullable is false,
    // and it is only available in the FormBuilder and not in the NonNullableFormBuilder
  });

  onFormSubmit(): void {
    if (this.loginFormGroup.valid) {
      this.authService.login();
      this.router.navigate(['home']);
    }
  }
}
