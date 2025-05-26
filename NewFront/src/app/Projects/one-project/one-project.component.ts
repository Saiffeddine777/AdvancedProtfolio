import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import axios, { AxiosResponse } from 'axios';
import { Project } from '../../Types/ProjectType';
import { envVar } from '../../env';
import { apiErrorHander } from '../../../Helpers/Errorhandler';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-one-project',
  imports: [MatSnackBarModule , MatCardModule  , MatButtonModule , NgIf],
  templateUrl: './one-project.component.html',
  styleUrl: './one-project.component.css'
})
export class OneProjectComponent implements OnInit{
  project !:Project
  id!:number|undefined
  constructor(private router:Router ,private snackBar :MatSnackBar){
    this.id =this.router.getCurrentNavigation()?.extras.state?.["id"]
  }

  async handleFetchOneProject():Promise<void>{
    try {
      const response :AxiosResponse<Project> = await axios.get(`${envVar}/api/projects/one/${this.id}`)
      this.project = response?.data
    } catch (error) {
      apiErrorHander( this.snackBar ,error)
    }
  }

  ngOnInit(): void {
      this.handleFetchOneProject()
  }

  navigateToUpdateProject():void{
    this.router.navigate(["/updateproject"] ,{
      state:{project : this.project}
    })
  } 
  navigateBackToProjects():void{
        this.router.navigate(["/projects"])
  } 
}
