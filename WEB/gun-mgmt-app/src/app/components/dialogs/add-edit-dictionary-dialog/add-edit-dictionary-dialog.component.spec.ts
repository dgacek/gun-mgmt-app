import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEditDictionaryDialogComponent } from './add-edit-dictionary-dialog.component';

describe('AddEditDictionaryDialogComponent', () => {
  let component: AddEditDictionaryDialogComponent;
  let fixture: ComponentFixture<AddEditDictionaryDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddEditDictionaryDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddEditDictionaryDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
