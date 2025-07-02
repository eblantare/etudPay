import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPayementDialogComponent } from './add-payement-dialog';

describe('AddPayementDialog', () => {
  let component: AddPayementDialogComponent;
  let fixture: ComponentFixture<AddPayementDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddPayementDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddPayementDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
