import { Component, EventEmitter, Input, Output } from '@angular/core';

import axios from 'axios';
import { envVar } from '../../env';
import { MatButton, MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-delete-one-skill',
  imports: [MatButtonModule ,MatButton],  
  templateUrl: './delete-one-skill.component.html',
  styleUrl: './delete-one-skill.component.css'
})
export class DeleteOneSkillComponent {
 @Input() id !:number |undefined
 @Output() skillDeleted = new EventEmitter<number|undefined>()
  async handleDeleteSkill ():Promise <void> {
    console.log(this.id)
    try {
      await axios.delete(`${envVar}/api/skills/delete/${this.id}`)
      console.log("The Skill has been deleted")
      this.skillDeleted.emit(this.id)
    } catch (error) {
      console.log(error)
    }
  }
}
