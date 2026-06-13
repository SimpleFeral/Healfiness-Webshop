import { Component, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { register } from 'swiper/element/bundle';
import { MatGridListModule } from '@angular/material/grid-list';
import { ProductCard } from '../product-card/product-card';
import { NewsSlide } from './news-slide/news-slide';

register()

@Component({
  selector: 'app-home-page',
  imports: [MatGridListModule, ProductCard, NewsSlide],
  templateUrl: './home-page.html',
  styleUrl: './home-page.scss',
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class HomePage {}
