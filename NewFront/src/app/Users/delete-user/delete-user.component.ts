import { Component, Input } from '@angular/core';
import axios from 'axios';
import { envVar } from '../../env';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-delete-user',
  imports: [MatButtonModule],
  templateUrl: './delete-user.component.html',
  styleUrl: './delete-user.component.css'
})
export class DeleteUserComponent {
   @Input() id !: number |undefined


   async deleteOneUser () : Promise <void>{
    try {
      await axios.delete(`${envVar}/api/users/delete/${this.id}`)
    } catch (error) {
      console.log(error)
    }    
   }
}
