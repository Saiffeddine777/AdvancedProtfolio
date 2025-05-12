import { Component } from '@angular/core';
import { MatButton } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { Level, Skill, TechNature } from '../../Types/SkillType';
import axios, { AxiosResponse } from 'axios';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule, NgFor } from '@angular/common';
import { envVar } from '../../env';
import { FormsModule } from '@angular/forms';
import {MatSnackBar , MatSnackBarModule, MatSnackBarRef, TextOnlySnackBar} from "@angular/material/snack-bar"
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-skill',
  imports: [
    MatButton,
    MatInputModule,
    CommonModule,
    MatButton,
    MatButtonModule,
    MatSelectModule,
    MatFormFieldModule,
    NgFor,
    FormsModule,
    MatSnackBarModule
  ],
  templateUrl: './create-skill.component.html',
  styleUrl: './create-skill.component.css',
})
export class CreateSkillComponent {

  constructor(private snackBar : MatSnackBar , private router :Router){

  }
  natures : string[] =["FrontEnd","BackEnd","Infrastructure","Database"]
  levels : string[]= ["Beginner" , "Intermediate" , "Advanced"]
  skillToInsert: Skill = {
    id: undefined,
    grade: 0,
    name: '',
    nature: 'Choose',
    level: 'Choose',
  };

  handleGradeChange(e: Event): MatSnackBarRef<TextOnlySnackBar>|void{
    const target = e.target as HTMLInputElement;
    this.skillToInsert.grade = Number(target.value);
  }

  handleNameChange(e: Event): void {
    const target = e.target as HTMLInputElement;
    this.skillToInsert.name = target.value;
  }

  handleNatureChange(e: Event): void {
    const target = e.target as HTMLSelectElement;
    this.skillToInsert.nature = target.value as TechNature;
  }

  handleLevelChange(e: Event): void {
    const target = e.target as HTMLSelectElement;
    this.skillToInsert.level = target.value as Level;
  }

  async handleSubmitSkill(): Promise<void> {
    try {
      const response: AxiosResponse<Skill> = await axios.post(
        `${envVar}/api/skills/create`,
        this.skillToInsert
      );
      if (response?.data?.id) {
          this.snackBar.open("the Skill has been inserted",'Close',{
          duration:4000,
          panelClass : ['snackbar-failure']
        })

          this.router.navigate(["/skills"])
       
      }
    } catch (error) {
     if (axios.isAxiosError(error)) {
        const log :string =error.response?.data?.message ||
        error.message ||
        'Unknown error'
        console.log('Axios Error : ' + log);
        this.snackBar.open(log,'Close',{
          duration:4000,
          panelClass : ['snackbar-failure']
        })
        
      } else if (error instanceof Error) {
        console.log('Error' + error.message);
        this.snackBar.open(error.message,'Close',{
          duration:4000,
          panelClass : ['snackbar-failure']
        })
      } else {
        const log:string  = 'Unexpected Error has happened'
        console.log(log);
        this.snackBar.open(log,'Close',{
          duration:4000,
          panelClass : ['snackbar-failure']
        })
      }
      
    }
  }
}
