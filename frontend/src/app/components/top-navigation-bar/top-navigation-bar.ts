import { Component, ChangeDetectionStrategy, Output, EventEmitter, signal, inject } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatDialog } from '@angular/material/dialog';
import { LoginMask } from './login-mask/login-mask';
import { AuthResponse } from '../../shared/model/authentication.model';
import { MatMenuModule } from '@angular/material/menu';
import { MatDividerModule } from '@angular/material/divider';

@Component({
  selector: 'app-top-navigation-bar',
  imports: [MatToolbarModule, MatButtonModule, MatIconModule, MatInputModule, MatFormFieldModule, MatMenuModule, MatDividerModule,],
  templateUrl: './top-navigation-bar.html',
  changeDetection: ChangeDetectionStrategy.Eager,
  styleUrl: './top-navigation-bar.scss',
})
export class TopNavigationBar {
  searchTerm: any;
  @Output() toggleMenu = new EventEmitter<void>();
  readonly dialog = inject(MatDialog)

  authResponse = signal<AuthResponse | null>(null)
  
  onMenuClick() {
    this.toggleMenu.emit();
  }

  openLoginDialog() {
    const dialogRef = this.dialog.open(LoginMask, {
      width: '400px',
      height: '400px'
    })
    
    this.dialog.afterAllClosed.subscribe((value: any) => {
      this.authResponse.set(value? (value as AuthResponse) : null)
    })
  }

  onLogout(): void {
    // logout via rest call
    this.authResponse.set(null)
  }
}
