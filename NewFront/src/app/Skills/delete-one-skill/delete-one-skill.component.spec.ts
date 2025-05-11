import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteOneSkillComponent } from './delete-one-skill.component';

describe('DeleteOneSkillComponent', () => {
  let component: DeleteOneSkillComponent;
  let fixture: ComponentFixture<DeleteOneSkillComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DeleteOneSkillComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DeleteOneSkillComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
