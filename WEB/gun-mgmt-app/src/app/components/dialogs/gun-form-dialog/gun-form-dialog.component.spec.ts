import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GunFormDialogComponent } from './gun-form-dialog.component';

describe('AddGunDialogComponent', () => {
  let component: GunFormDialogComponent;
  let fixture: ComponentFixture<GunFormDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GunFormDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GunFormDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
