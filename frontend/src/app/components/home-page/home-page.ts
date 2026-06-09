import { Component } from '@angular/core';
import { NewsCarousel } from '../news-carousel/news-carousel';
import { ShopInfo } from '../shop-info/shop-info';

@Component({
  selector: 'app-home-page',
  imports: [NewsCarousel, ShopInfo],
  templateUrl: './home-page.html',
  styleUrl: './home-page.scss',
})
export class HomePage {}
