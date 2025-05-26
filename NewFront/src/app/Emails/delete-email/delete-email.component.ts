import { Component, EventEmitter, Input, Output } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import axios, { AxiosResponse } from 'axios';
import { envVar } from '../../env';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { apiErrorHander } from '../../../Helpers/Errorhandler';

@Component({
  selector: 'app-delete-email',
  imports: [MatButtonModule , MatSnackBarModule],
  templateUrl: './delete-email.component.html',
  styleUrl: './delete-email.component.css'
})
export class DeleteEmailComponent {
  @Input() id !:string |undefined; 
  @Output() deletedEmail = new EventEmitter<string|undefined>()
  constructor(private snackBar : MatSnackBar){

  }

  async handleDeleteEmail ():Promise<void>{
    if (!this.id) return;
    try {
      const result : AxiosResponse<string> = await axios.delete(`${envVar}/api/emails/delete/${this.id}`)
      if (result.data){
        this.snackBar.open(result.data,'Close',{
        duration: 3000,
        panelClass :['snackbar-success']
      })
      this.deletedEmail.emit(this.id)
      }

    } catch (error){
      apiErrorHander(this.snackBar,error)
    }finally{

    }
  }

}
