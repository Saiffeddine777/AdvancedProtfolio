import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSnackBarModule } from '@angular/material/snack-bar';

@Component({
  selector: 'app-log-in',
  imports: [MatFormFieldModule, MatInputModule, MatButtonModule ,MatSnackBarModule],
  templateUrl: './log-in.component.html',
  styleUrl: './log-in.component.css'
})
export class LogInComponent {
  signInCredentials : { emailAddress :string , password :string} = {emailAddress : "" , password: ""}


  handleChangeEmail(event :Event):void{

  }

  handleChangePassword(event :Event):void{

  }

  async handleSubmitSignIn  () :Promise<void>{
    try {
      
    } catch (error) {
      
    }
  }
}
