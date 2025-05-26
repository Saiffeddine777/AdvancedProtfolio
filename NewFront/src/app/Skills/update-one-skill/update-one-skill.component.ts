import { Component } from '@angular/core';
import { Skill , TechNature ,Level } from '../../Types/SkillType';
import { MatButton, MatButtonModule } from '@angular/material/button';
import { MatFormField, MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { CommonModule, NgFor } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import axios, { AxiosResponse } from 'axios';
import { envVar } from '../../env';
import { Router } from '@angular/router';

@Component({
  selector: 'app-update-one-skill',
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
    MatSnackBarModule,
  ],
  templateUrl: './update-one-skill.component.html',
  styleUrl: './update-one-skill.component.css'
})
export class UpdateOneSkillComponent {
  etc : string = " ..."
  placeholderSkill !:Skill 
  natures : string[] =["FrontEnd","BackEnd","Infrastructure","Database"]
  levels : string[]= ["Beginner" , "Intermediate" , "Advanced"]
  skill  :Skill={
    id : undefined,
    name:"",
    level :"Choose",
    description :"",
    nature :"Choose",
    grade :0, 
  }
  constructor(private router : Router , private snackBar : MatSnackBar){
    this.placeholderSkill = this.router.getCurrentNavigation()?.extras.state?.["skill"] as Skill 
  }
    handleGradeChange(e: Event):void{
    const target = e.target as HTMLInputElement;
    this.skill.grade = Number(target.value);
  }

  handleNameChange(e: Event): void {
    const target = e.target as HTMLInputElement;
    this.skill.name = target.value;
  }

  handleNatureChange(e: Event): void {
    const target = e.target as HTMLSelectElement;
    this.skill.nature = target.value as TechNature;
  }

  handleLevelChange(e: Event): void {
    const target = e.target as HTMLSelectElement;
    this.skill.level = target.value as Level;
  }
  handleDescrptionChange(e: Event): void {
    const target = e.target as HTMLTextAreaElement;
    this.skill.description = target.value;
  }

  async handleSubmitUpdate():Promise<void>{
    try {
      const data:Object = Object.fromEntries(
        Object.entries(this.skill).filter(([_,value])=>value!==null && value !==undefined && value !=="" && value !=="Choose" )
      )
      const response :AxiosResponse<string> = await axios.put(`${`${envVar}/api/skills/update/${this.placeholderSkill.id}`}`,data)
      this.snackBar.open(response.data, 'Close' ,{
        duration:3000,
        panelClass: ["snackbar-success"]
      })
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
