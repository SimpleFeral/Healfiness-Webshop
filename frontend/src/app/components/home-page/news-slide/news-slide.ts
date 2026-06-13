import { Component, input } from '@angular/core';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-news-slide',
  imports: [MatCardModule],
  templateUrl: './news-slide.html',
  styleUrl: './news-slide.scss',
})
export class NewsSlide {
  newsTitle = input<string>()
  newsSubtitle = input<string>()
  bgColor = input<string>()
}
