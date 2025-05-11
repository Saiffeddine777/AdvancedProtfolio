import { Component } from '@angular/core';
import { MatButton, MatButtonModule } from '@angular/material/button';
import { Router } from '@angular/router';

@Component({
  selector: 'app-all-skills',
  imports: [MatButton , MatButtonModule],
  templateUrl: './all-skills.component.html',
  styleUrl: './all-skills.component.css'
})
export class AllSkillsComponent {
  constructor(private router :Router){

  }

  navigateToCreateASkill (): void {
    this.router.navigate(["/createskill"])
  }

}