import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ALlEmailsComponent } from './all-emails.component';

describe('ALlEmailsComponent', () => {
  let component: ALlEmailsComponent;
  let fixture: ComponentFixture<ALlEmailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ALlEmailsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ALlEmailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
