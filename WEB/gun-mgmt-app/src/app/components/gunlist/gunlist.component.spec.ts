import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GunlistComponent } from './gunlist.component';

describe('GunlistComponent', () => {
  let component: GunlistComponent;
  let fixture: ComponentFixture<GunlistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GunlistComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GunlistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
