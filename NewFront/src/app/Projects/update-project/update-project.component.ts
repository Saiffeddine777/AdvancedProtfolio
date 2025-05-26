import { Component } from '@angular/core';
import { Project } from '../../Types/ProjectType';
import axios, { AxiosResponse } from 'axios';
import { envVar } from '../../env';
import { MatFormField, MatFormFieldModule } from '@angular/material/form-field';
import {  MatInputModule } from '@angular/material/input';
import { Router } from '@angular/router';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatButtonModule } from '@angular/material/button';
import { apiErrorHander } from '../../../Helpers/Errorhandler';
import { filterObject } from '../../../Helpers/ObjectFilter';

@Component({
  selector: 'app-update-project',
  imports: [MatFormField , MatFormFieldModule , MatInputModule , MatButtonModule , MatSnackBarModule],
  templateUrl: './update-project.component.html',
  styleUrl: './update-project.component.css'
})
export class UpdateProjectComponent {
   existingProject !:Project
   projectToUpdate :Project={
    id :undefined,
    name: '',
    description: '',
    repoUrl: '',
    liveUrl: '',
    techStack: '',
   }
   constructor(private router :Router  , private snackBar :MatSnackBar){
      this.existingProject = this.router.getCurrentNavigation()?.extras.state?.["project"]
   }

    handleNameChange(e: Event): void {
    const target = e.target as HTMLInputElement;
    this.projectToUpdate.name = target.value;
  }
  handleDescriptionChange(e: Event): void {
    const target = e.target as HTMLInputElement;
    this.projectToUpdate.description = target.value;
  }
  handleRepoUrlChange(e: Event): void {
    const target = e.target as HTMLInputElement;
    this.projectToUpdate.repoUrl = target.value;
  }
  handleLiveUrlChange(e: Event): void {
    const target = e.target as HTMLInputElement;
    this.projectToUpdate.liveUrl = target.value;
  }
  handleTechStackChange(e: Event): void {
    const target = e.target as HTMLInputElement;
    this.projectToUpdate.techStack = target.value;
  }

  async handleSubmitUpdateProject():Promise<void> {
    try {
      const data =  filterObject(this.projectToUpdate);
      const response :AxiosResponse<string> = await axios.put(`${envVar}/api/projects/update/${this.existingProject.id}`,data)
         if (response?.data) {
          this.snackBar.open("The Project has been updated",'Close',{
          duration:3000,
          panelClass : ['snackbar-success']
        })
      }
      this.router.navigate(["/projects"])

    } catch (error) {
      apiErrorHander(this.snackBar , error)
    }
  }
}
