import { Component, EventEmitter, Input, Output } from '@angular/core';
import axios, { AxiosResponse } from 'axios';
import { envVar } from '../../env';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { apiErrorHander } from '../../../Helpers/Errorhandler';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-delete-project',
  imports: [MatSnackBarModule , MatButtonModule],
  templateUrl: './delete-project.component.html',
  styleUrl: './delete-project.component.css'
})
export class DeleteProjectComponent {
  
  @Input() id !: number |undefined;
  @Output() eventEmitter  = new EventEmitter<number|undefined>();

  constructor(private snackBar :MatSnackBar ){
    
  }
  async handleDeleteProject():Promise<void>{
    try {
     const response:AxiosResponse<string> = await axios.delete(`${envVar}/api/projects/delete/${this.id}`) 
      this.snackBar.open(response?.data, 'Close', {
            duration: 4000,
            panelClass: ['snackbar-success']
        })
        this.eventEmitter.emit(this.id)
    } catch (error) {
      apiErrorHander(this.snackBar , error)
    }finally{

    }
  }
}
