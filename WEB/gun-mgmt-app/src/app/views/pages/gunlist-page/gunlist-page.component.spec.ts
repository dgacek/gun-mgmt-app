import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GunlistPageComponent } from './gunlist-page.component';

describe('GunlistPageComponent', () => {
  let component: GunlistPageComponent;
  let fixture: ComponentFixture<GunlistPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GunlistPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GunlistPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
