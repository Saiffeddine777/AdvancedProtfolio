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

  constructor(private snackbar : MatSnackBar){

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
        console.log('The Skill has been inserted');
      }
    } catch (error) {
      console.log(error);
    }
  }
}
