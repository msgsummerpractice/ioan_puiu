// import { Component, inject } from '@angular/core';
// import { AuthService } from '../service/auth-service';
// import { Router } from '@angular/router';
// import {
//   FormBuilder,
//   NonNullableFormBuilder,
//   Validators,
//   FormControl,
//   FormGroup,
//   ReactiveFormsModule,
// } from '@angular/forms';

// type LoginForm = {
//   username: FormControl<string>;
//   password: FormControl<string>;
// };

// @Component({
//   selector: 'login-form',
//   imports: [ReactiveFormsModule],
//   templateUrl: './login-form.html',
//   styleUrl: './login-form.css',
// })
// export class LoginFormComponent {
//   private readonly router = inject(Router);
//   protected readonly authService = inject(AuthService);

//   // Using NonNullable builder ensures values are never null
//   private readonly _formBuilder = inject(NonNullableFormBuilder);

//   protected readonly loginFormGroup = this._formBuilder.group<LoginForm>({
//     username: this._formBuilder.control('', Validators.required),
//     password: this._formBuilder.control('', Validators.required),
//     // In the case of a normal FormBuilder, we might still want to mark some of our controls as non-nullable,
//     // and we can do it by passing it as an option like this:
//     // count: this._formBuilder.control(1, { nonNullable: true })
//     // Note that the default value of nonNullable is false,
//     // and it is only available in the FormBuilder and not in the NonNullableFormBuilder
//   });

//   onFormSubmit(): void {
//     if (this.loginFormGroup.valid) {
//       this.authService.login();
//       this.router.navigate(['home']);
//     }
//   }
// }

import { Component, inject, signal } from '@angular/core';
import {
  FormBuilder,
  NonNullableFormBuilder,
  Validators,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
} from '@angular/forms';
import { MatAnchor } from '@angular/material/button';
import { AuthService } from '../service/auth-service';
import { Router } from '@angular/router';

type LoginForm = {
  username: FormControl<string>;
  password: FormControl<string>;
};

@Component({
  selector: 'app-login-form',
  imports: [ReactiveFormsModule, MatAnchor],
  templateUrl: './login-form.html',
  styleUrl: './login-form.css',
})
export class LoginFormComponent {
  private readonly _formBuilder = inject(NonNullableFormBuilder);
  private readonly authService = inject(AuthService);
  private readonly router = inject(Router);
  protected readonly isSubmited = signal<boolean>(false);
  private readonly usrnameForMfa = signal('');
  protected readonly loginError = signal('');

  protected readonly loginFormGroup = this._formBuilder.group<LoginForm>({
    username: this._formBuilder.control('', Validators.required),
    password: this._formBuilder.control('', [Validators.required, Validators.minLength(6)]),
  });

  onFormSubmit(): void {
    this.loginError.set('');
    if (this.loginFormGroup.invalid) {
      this.loginFormGroup.markAllAsTouched();
      return;
    }

    const { username, password } = this.loginFormGroup.getRawValue();

    this.authService.login(username, password).subscribe({
      next: () => {
        this.usrnameForMfa.set(username);
        this.isSubmited.set(true);
      },
      error: (err) => {
        this.isSubmited.set(false);
        if (err.status === 401) {
          this.loginError.set('Invalida username or password.');
        } else {
          this.loginError.set('Something went wrong. Please try again.');
        }
      },
    });
  }

  logout(): void {
    this.authService.logout();
  }

  isAuthenticated(): boolean {
    return this.authService.isAuthenticated();
  }

  get username() {
    return this.loginFormGroup.get('username');
  }

  get password() {
    return this.loginFormGroup.get('password');
  }

  verifyMfa(code: string): void {
    this.authService.verifyMfa(this.usrnameForMfa(), code).subscribe({
      next: (response) => {
        console.log('MFA verification successful. Access token:', response.token);
        this.authService.saveToken(response.token);
        this.isSubmited.set(false);
        this.router.navigate(['/']);
      },
    });
  }
}
