import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TopNavigationBar } from './top-navigation-bar';

describe('TopNavigationBar', () => {
  let component: TopNavigationBar;
  let fixture: ComponentFixture<TopNavigationBar>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TopNavigationBar],
    }).compileComponents();

    fixture = TestBed.createComponent(TopNavigationBar);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
