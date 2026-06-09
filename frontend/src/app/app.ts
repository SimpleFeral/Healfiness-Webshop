import { Component, signal, ChangeDetectionStrategy } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { TopNavigationBar } from './components/top-navigation-bar/top-navigation-bar';
import { Footer } from './components/footer/footer';
import { MatSidenav, MatSidenavContainer, MatSidenavContent } from '@angular/material/sidenav';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, TopNavigationBar, Footer, MatSidenav, MatSidenavContainer, MatSidenavContent],
  templateUrl: './app.html',
  changeDetection: ChangeDetectionStrategy.Eager,
  styleUrl: './app.scss',
})
export class App {
  protected readonly title = signal('frontend');
}
