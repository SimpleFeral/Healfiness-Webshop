import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShopInfo } from './shop-info';

describe('ShopInfo', () => {
  let component: ShopInfo;
  let fixture: ComponentFixture<ShopInfo>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShopInfo],
    }).compileComponents();

    fixture = TestBed.createComponent(ShopInfo);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
