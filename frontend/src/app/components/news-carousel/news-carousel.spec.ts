import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewsCarousel } from './news-carousel';

describe('NewsCarousel', () => {
  let component: NewsCarousel;
  let fixture: ComponentFixture<NewsCarousel>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NewsCarousel],
    }).compileComponents();

    fixture = TestBed.createComponent(NewsCarousel);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
