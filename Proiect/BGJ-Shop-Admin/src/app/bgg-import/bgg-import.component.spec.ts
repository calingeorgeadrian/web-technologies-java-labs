import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BggImportComponent } from './bgg-import.component';

describe('BggImportComponent', () => {
  let component: BggImportComponent;
  let fixture: ComponentFixture<BggImportComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BggImportComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BggImportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
