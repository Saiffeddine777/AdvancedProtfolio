import { Component } from '@angular/core';
import { MatButton, MatButtonModule } from '@angular/material/button';
import { MatFormField, MatLabel } from '@angular/material/form-field';
import { Project } from '../../Types/ProjectType';
import axios, { AxiosResponse } from 'axios';
import { envVar } from '../../env';
import { MatInputModule } from '@angular/material/input';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { apiErrorHander } from '../../../Helpers/Errorhandler';

@Component({
  selector: 'app-create-project',
  imports: [MatButtonModule, MatFormField, MatLabel, MatInputModule, MatButtonModule , MatSnackBarModule],
  templateUrl: './create-project.component.html',
  styleUrl: './create-project.component.css',
})
export class CreateProjectComponent {
  projectToInsert: Project = {
    id: undefined,
    name: '',
    description: '',
    repoUrl: '',
    liveUrl: '',
    techStack: '',
  };

  constructor(private snackBar : MatSnackBar , private router :Router){}

  handleNameChange(e: Event): void {
    const target = e.target as HTMLInputElement;
    this.projectToInsert.name = target.value;
  }
  handleDescriptionChange(e: Event): void {
    const target = e.target as HTMLInputElement;
    this.projectToInsert.description = target.value;
  }
  handleRepoUrlChange(e: Event): void {
    const target = e.target as HTMLInputElement;
    this.projectToInsert.repoUrl = target.value;
  }
  handleLiveUrlChange(e: Event): void {
    const target = e.target as HTMLInputElement;
    this.projectToInsert.liveUrl = target.value;
  }
  handleTechStackChange(e: Event): void {
    const target = e.target as HTMLInputElement;
    this.projectToInsert.techStack = target.value;
  }

  async handleSubmitProject(): Promise<void> {
    try {
      if(Object.values(this.projectToInsert).filter((e)=>e && e==='').length>0){
        throw new Error ("Please fill all the Fields")
      }
      const response: AxiosResponse<Project> = await axios.post(`${envVar}/api/projects/create`, this.projectToInsert)
      if (response.data.id) {
          this.snackBar.open("The Project has been created",'Close',{
          duration:3000,
          panelClass : ['snackbar-success']
        })
      }

      this.router.navigate(["/projects"])
    } catch (error) {
      apiErrorHander(this.snackBar, error) 
    }

  }
}
