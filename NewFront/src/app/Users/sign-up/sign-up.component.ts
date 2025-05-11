import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import {MatSnackBarModule ,MatSnackBar} from "@angular/material/snack-bar"
import { User } from '../../Types/UserType';
import axios, { AxiosResponse } from 'axios';
import { envVar } from '../../env';

@Component({
  selector: 'app-sign-up',
  imports: [MatFormFieldModule, MatInputModule, MatButtonModule ,MatSnackBarModule],
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.css',
})
export class SignUpComponent {

  constructor(private snackBar : MatSnackBar){
    
  }
  signedUpUser: User & { confirmPassword: string } = {
    id: undefined,
    firstName: '',
    lastName: '',
    occupation: '',
    emailAddress: '',
    phoneNumber: '',
    password: '',
    confirmPassword: '',
  };

  handleChangePassword(e: Event): void {
    const target = e.target as HTMLInputElement;
    switch (target.placeholder) {
      case 'Ex: John':
        this.signedUpUser.firstName = target.value;
        break;
      case 'Ex: Doe':
        this.signedUpUser.lastName = target.value;
        break;
      case 'Ex: johndoe@example.com':
        this.signedUpUser.emailAddress = target.value;
        break;
      case 'Ex: Hr Manager':
        this.signedUpUser.occupation = target.value;
        break;
      case 'Ex: +216 21222222':
        this.signedUpUser.phoneNumber = target.value;
        break;
      case 'Type a Password':
        this.signedUpUser.password = target.value;
        break;
      case 'Confirm a Password':
        this.signedUpUser.confirmPassword = target.value;
        break;
      default:
        break;
    }
  }

  async handleSubmitSignupUser(): Promise<void> {
    
    try {
      const submittedUser: Partial<User> = {
        firstName: this.signedUpUser.firstName,
        lastName: this.signedUpUser.lastName,
        occupation: this.signedUpUser.occupation,
        emailAddress: this.signedUpUser.emailAddress,
        phoneNumber: this.signedUpUser.phoneNumber,
        password: this.signedUpUser.password,
      };
      if (Object.values(this.signedUpUser).filter((e) => typeof e==='string'? e.trim?.() === '':undefined).length > 0) {
        throw new Error('Please fill all the Fields');
      }

      if (this.signedUpUser.confirmPassword !== submittedUser.password){
        throw new Error ("Passwords do not match")
      }
     const axiosResult:AxiosResponse<User>  =  await axios.post(`${envVar}/api/users/signup`, submittedUser);
      if (axiosResult?.data?.id){
        this.snackBar.open('You have sucessfully signed in','Close',{
          duration:3000,
          panelClass :['snackbar-success']
        })
      }

    } catch (error) {
 
      if (axios.isAxiosError(error)) {
        const log :string =error.response?.data?.message ||
        error.message ||
        'Unknown error'
        console.log('Axios Error : ' + log);
        this.snackBar.open(log,'Close',{
          duration:4000,
          panelClass : ['snackbar-failure']
        })
        
      } else if (error instanceof Error) {
        console.log('Error' + error.message);
        this.snackBar.open(error.message,'Close',{
          duration:4000,
          panelClass : ['snackbar-failure']
        })
      } else {
        const log:string  = 'Unexpected Error has happened'
        console.log(log);
        this.snackBar.open(log,'Close',{
          duration:4000,
          panelClass : ['snackbar-failure']
        })
      }
    }
  }
}
