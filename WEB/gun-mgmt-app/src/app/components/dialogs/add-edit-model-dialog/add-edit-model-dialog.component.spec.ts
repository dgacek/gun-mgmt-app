import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEditModelDialogComponent } from './add-edit-model-dialog.component';

describe('AddModelDialogComponent', () => {
  let component: AddEditModelDialogComponent;
  let fixture: ComponentFixture<AddEditModelDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddEditModelDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddEditModelDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
