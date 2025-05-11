import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OneSkillComponent } from './one-skill.component';

describe('OneSkillComponent', () => {
  let component: OneSkillComponent;
  let fixture: ComponentFixture<OneSkillComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OneSkillComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OneSkillComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
