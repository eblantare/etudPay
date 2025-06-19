import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoadStudent } from './load-student';

describe('LoadStudent', () => {
  let component: LoadStudent;
  let fixture: ComponentFixture<LoadStudent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LoadStudent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoadStudent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
