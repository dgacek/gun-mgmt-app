import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEditGunDialogComponent } from './add-edit-gun-dialog.component';

describe('AddGunDialogComponent', () => {
  let component: AddEditGunDialogComponent;
  let fixture: ComponentFixture<AddEditGunDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddEditGunDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddEditGunDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
