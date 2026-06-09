import { Component, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { register } from 'swiper/element/bundle';

register();

@Component({
  selector: 'app-news-carousel',
  imports: [],
  templateUrl: './news-carousel.html',
  styleUrl: './news-carousel.scss',
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class NewsCarousel {
    
  swiperConfig = {
    slidesPerView: 1,
    spaceBetween: 30,
    navigation: true,
    pagination: { clickable: true },
    centeredSlides: true,
    loop: true,
    speed: 500,
    autoplay: {
      delay: 4000,
      disableOnInteraction: false,
    },
  };
  
}
