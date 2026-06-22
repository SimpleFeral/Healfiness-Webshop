import { Component, inject, output, signal } from '@angular/core';
import { MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import {
  AbstractControl,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  ValidationErrors,
  Validators,
} from '@angular/forms';
import { AuthService } from './auth-service';
import { finalize } from 'rxjs';

function loginValidator(control: AbstractControl): ValidationErrors | null {
  const value = String(control.value ?? '').trim();
  if (value.length === 0) {
    return null;
  }
  if (value.length > 15) {
    return null;
  }

  const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  const usernamePattern = /^[A-Za-z0-9._-]{3,}$/;

  return emailPattern.test(value) || usernamePattern.test(value) ? null : { invalidLogin: true };
}

@Component({
  selector: 'app-login-mask',
  imports: [
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule,
    MatIconModule,
    MatButtonModule,
  ],
  templateUrl: './login-mask.html',
  styleUrl: './login-mask.scss',
  standalone: true,
})
export class LoginMask {
  readonly dialogRef = inject(MatDialogRef<LoginMask>);
  readonly authService = inject(AuthService);

  loginForm = new FormGroup({
    login: new FormControl<string>('', {
      nonNullable: true,
      validators: [Validators.required, loginValidator],
    }),
    password: new FormControl<string>('', {
      nonNullable: true,
      validators: [Validators.required, Validators.minLength(6)],
    }),
  });

  hide = signal(true);
  loginError = signal<string>('');
  isSubmitting = signal<boolean>(false);

  constructor() {
    this.loginForm.valueChanges.subscribe(() => {
      if (this.loginError().trim().length > 0) {
        this.loginError.set('');
      }
    });
  }

  get loginControl() {
    return this.loginForm.get('login') as FormControl<string>;
  }

  get passwordControl() {
    return this.loginForm.get('password') as FormControl<string>;
  }

  clickEvent(event: MouseEvent) {
    this.hide.set(!this.hide());
    event.stopPropagation();
  }

  onLogin(): void {
    if (this.loginForm.invalid) {
      this.loginForm.markAllAsTouched();
      return;
    }

    this.isSubmitting.set(true);
    this.authService
      .login({
        login: this.loginControl.value.trim(),
        password: this.passwordControl.value,
      })
      .pipe(
        finalize(() => {
          this.isSubmitting.set(false);
        }),
      )
      .subscribe({
        next: (value) => {
          console.log(value)
          this.dialogRef.close(value);
        },
        error: (error) => {
          console.log(error);
          this.loginError.set(this.getLoginErrorMessage(error));
        },
      });
  }

  private getLoginErrorMessage(error: any): string {
    if (error?.status === 401) {
      return 'Ungültige Anmeldedaten. Bitte prüfen Sie Ihre Eingabe.';
    }
    return 'Anmeldung fehlgeschlagen. Bitte erneut versuchen.';
  }

  onCancel(): void {
    this.loginForm.reset({
      login: '',
      password: '',
    });
    this.dialogRef.close();
  }
}
