import { Component, signal, ChangeDetectionStrategy } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { TopNavigationBar } from './components/top-navigation-bar/top-navigation-bar';
import { Footer } from './components/footer/footer';
import { MatSidenavModule } from '@angular/material/sidenav';
import { register } from 'swiper/element/bundle';

register()

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, TopNavigationBar, Footer, MatSidenavModule],
  templateUrl: './app.html',
  changeDetection: ChangeDetectionStrategy.Eager,
  styleUrl: './app.scss',
})
export class App {
  protected readonly title = signal('Healfiness-Webshop');
}
