import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import axios, { AxiosResponse } from 'axios';
import { envVar } from '../../env';
import { Skill } from '../../Types/SkillType';
import { MatCard, MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-one-skill',
  imports: [MatCard , MatCardModule , MatButtonModule, NgIf ],
  templateUrl: './one-skill.component.html',
  styleUrl: './one-skill.component.css'
})
export class OneSkillComponent implements OnInit {
  skill !: Skill
  id !:number;
  constructor(private router :Router){
    this.id = this.router.getCurrentNavigation()?.extras.state?.["id"] as number;  
  }
navigateBackToSkills ():void {
  this.router.navigate(["/skills"])
}
navigateToUpadteSkill ():void {
  this.router.navigate(["/updateskill"],{state:{ skill:this.skill}})
}

 async handleGetOneSkill ():Promise<void>{
  try {
    const res : AxiosResponse<Skill> =  await axios.get(`${envVar}/api/skills/one/${this.id}`)
    this.skill = res.data
  } catch (error) {
    console.log(error)
  }
 }

  ngOnInit(): void {
    this.handleGetOneSkill()
  }
}
