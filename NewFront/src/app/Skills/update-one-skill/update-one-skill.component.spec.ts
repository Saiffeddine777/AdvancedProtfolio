import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateOneSkillComponent } from './update-one-skill.component';

describe('UpdateOneSkillComponent', () => {
  let component: UpdateOneSkillComponent;
  let fixture: ComponentFixture<UpdateOneSkillComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UpdateOneSkillComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateOneSkillComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
