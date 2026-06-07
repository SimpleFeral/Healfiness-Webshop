import { Component, ChangeDetectionStrategy } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';

@Component({
  selector: 'app-top-navigation-bar',
  imports: [MatToolbarModule, MatButtonModule, MatIconModule, MatInputModule, MatFormFieldModule],
  templateUrl: './top-navigation-bar.html',
  changeDetection: ChangeDetectionStrategy.Eager,
  styleUrl: './top-navigation-bar.scss',
})
export class TopNavigationBar {
  searchTerm: any;

  toggleDrawer(): void {
    console.log('Drawer öffnen/schließen');
  }
}
