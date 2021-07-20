import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddGunDialogComponent } from './add-gun-dialog.component';

describe('AddGunDialogComponent', () => {
  let component: AddGunDialogComponent;
  let fixture: ComponentFixture<AddGunDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddGunDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddGunDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
