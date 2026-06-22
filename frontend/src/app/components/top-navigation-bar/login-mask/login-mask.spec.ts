import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginMask } from './login-mask';

describe('LoginMask', () => {
  let component: LoginMask;
  let fixture: ComponentFixture<LoginMask>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LoginMask],
    }).compileComponents();

    fixture = TestBed.createComponent(LoginMask);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
