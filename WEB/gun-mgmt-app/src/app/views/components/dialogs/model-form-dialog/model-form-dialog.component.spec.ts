import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModelFormDialogComponent } from './model-form-dialog.component';

describe('AddModelDialogComponent', () => {
  let component: ModelFormDialogComponent;
  let fixture: ComponentFixture<ModelFormDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModelFormDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModelFormDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
