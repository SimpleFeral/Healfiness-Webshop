import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewsSlide } from './news-slide';

describe('NewsSlide', () => {
  let component: NewsSlide;
  let fixture: ComponentFixture<NewsSlide>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NewsSlide],
    }).compileComponents();

    fixture = TestBed.createComponent(NewsSlide);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
