import { Component, OnInit } from '@angular/core';
import { MatButton, MatButtonModule } from '@angular/material/button';
import { Project } from '../../Types/ProjectType';
import { Router } from '@angular/router';
import axios, { AxiosResponse } from 'axios';
import { envVar } from '../../env';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { NgFor } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { DeleteProjectComponent } from "../delete-project/delete-project.component";
import { apiErrorHander } from '../../../Helpers/Errorhandler';

@Component({
  selector: 'app-project-list',
  imports: [MatButtonModule, MatButton, MatSnackBarModule, NgFor, MatCardModule, DeleteProjectComponent],
  templateUrl: './project-list.component.html',
  styleUrl: './project-list.component.css'
})
export class ProjectListComponent implements OnInit{
  projects !: Project[] 
  constructor(private router :Router , private snackBar :MatSnackBar){
      
  }

  navigateToCreateProject ():void {
    this.router.navigate(["/createproject"])
  }
  navigateToOneProject(id:number | undefined):void{
    this.router.navigate(["/oneproject"] ,{state:{
      id:id
    }})
  }

  async handleFetchProjects():Promise<void>{
    try {
      const response :AxiosResponse<Project[]> = await axios.get(`${envVar}/api/projects/all`)
       if (response.data?.length){
        this.projects = response.data
       }
    } catch (error) {
       apiErrorHander(this.snackBar , error)
    }finally{

    }
  }

  handleProjectDeleted ( deletedId :number|undefined): void{
   this.projects = this.projects.filter((e)=>e.id !== deletedId)
  }

  ngOnInit(): void {
      this.handleFetchProjects()
  }
}
