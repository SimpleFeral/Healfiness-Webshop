import { AfterViewInit, Component, CUSTOM_ELEMENTS_SCHEMA, signal } from '@angular/core';
import { MatGridListModule } from '@angular/material/grid-list';
import { ProductCard } from '../product-card/product-card';
import { NewsSlide } from './news-slide/news-slide';

@Component({
  selector: 'app-home-page',
  imports: [MatGridListModule, ProductCard, NewsSlide],
  templateUrl: './home-page.html',
  styleUrl: './home-page.scss',
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  standalone: true
})
export class HomePage implements AfterViewInit {
  isSwiperReady = signal<boolean>(false)

  ngAfterViewInit() {
    setTimeout(() => {
      this.isSwiperReady.set(true)
    }, 0)
  }
}
