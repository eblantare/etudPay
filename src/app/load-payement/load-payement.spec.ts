import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoadPayement } from './load-payement';

describe('LoadPayement', () => {
  let component: LoadPayement;
  let fixture: ComponentFixture<LoadPayement>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LoadPayement]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoadPayement);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
