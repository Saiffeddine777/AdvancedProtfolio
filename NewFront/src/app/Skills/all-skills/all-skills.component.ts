import { Component, OnInit } from '@angular/core';    
import { Router } from '@angular/router';
import { Skill } from '../../Types/SkillType';
import axios, { AxiosResponse } from 'axios';
import { envVar } from '../../env';

import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { NgFor } from '@angular/common';
import { DeleteOneSkillComponent } from "../delete-one-skill/delete-one-skill.component";

@Component({
  selector: 'app-all-skills',
  standalone: true,
  imports: [MatCardModule, MatButtonModule, NgFor, DeleteOneSkillComponent],
  templateUrl: './all-skills.component.html',
  styleUrl: './all-skills.component.css'
})
export class AllSkillsComponent implements OnInit {
  
  constructor(private router :Router){

  }

  skills : Skill[] = []

  async handleFetchSkills():Promise<void> {
    try {
      const response : AxiosResponse<Skill[]> = await axios.get(`${envVar}/api/skills/allnodescription`)
      if (response?.data.length){
        this.skills = response.data
      }
      console.log(this.skills )
    } catch (error) {
     console.log(error) 
    }
  }

  ngOnInit(): void {
    this.handleFetchSkills()
  }

  navigateToCreateASkill (): void{
    this.router.navigate(["/createskill"])
  }


  navigateToOneSkill(id:number|undefined): void{
    this.router.navigate(["/oneskill"],{state:{id :id}})
  }

  handleSkillDeleted (deletedId :number |undefined):void{
    this.skills= this.skills.filter(skill=>skill.id !== deletedId)
  }

}