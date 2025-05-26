import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OneEmailComponent } from './one-email.component';

describe('OneEmailComponent', () => {
  let component: OneEmailComponent;
  let fixture: ComponentFixture<OneEmailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OneEmailComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OneEmailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
