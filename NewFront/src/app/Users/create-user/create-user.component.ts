import { Component } from '@angular/core';
import { User } from '../../Types/UserType';
import {FormsModule} from "@angular/forms"
import {MatInputModule} from "@angular/material/input"
import { MatFormFieldModule } from '@angular/material/form-field';
import {MatButtonModule} from "@angular/material/button"
import axios  ,{AxiosResponse}from "axios"
import { envVar } from '../../env';
import { MatSelectModule } from '@angular/material/select';
import { NgFor } from '@angular/common';
import { MatSnackBarModule } from '@angular/material/snack-bar';
 
@Component({
  selector: 'app-create-user',
  imports: [ MatSnackBarModule, FormsModule,MatInputModule, MatFormFieldModule ,MatButtonModule ,MatSelectModule , NgFor],
  templateUrl: './create-user.component.html',
  styleUrl: './create-user.component.css',
})
export class CreateUserComponent {
  roles : string [] = ["Admin" , "visitor"]
  createUserState: User = {
    id :undefined,
    emailAddress: '',
    occupation: '',
    lastName: '',
    firstName: '',
    phoneNumber: '',
    password :'',
    role : "Choose"
  };

  handleFirstNameChange (e:Event){
    const target = e.target as HTMLInputElement
    this.createUserState.firstName = target.value
  }

  handleLastNameChange (e:Event){
    const target = e.target as HTMLInputElement
    this.createUserState.lastName = target.value
  }

  handleEmailAddressChange (e:Event){
    const target = e.target as HTMLInputElement
    this.createUserState.emailAddress = target.value
  }

  handlePhoneNumberChange (e:Event){
    const target = e.target as HTMLInputElement
    this.createUserState.phoneNumber = target.value
  }  
  
  handleOccupationChange (e:Event){
    const target = e.target as HTMLInputElement
    this.createUserState.occupation = target.value
  }

  handleRoleChange (e:Event){
    const target = e.target as HTMLSelectElement
    this.createUserState.occupation = target.value
    console.log(this.createUserState.role)
  }


  async handleSubmitUser (): Promise<void> {
    try {
      if (Object.entries(this.createUserState).filter(([key, value])=> key!=="password"&&value==='').length>0){
          throw new Error(": Fill Out all the Fields");
      }
      const result:  AxiosResponse<User> = await axios.post(`${envVar }/api/users/create`,this.createUserState)
      if (result.data.id){
        console.log("user has been Inserted")
      }
    } catch (error) {
      if (axios.isAxiosError(error)){
        console.log("Axios Error : " +error.response?.data?.message || error.message || "Unknown error")
      }
      else if(error instanceof Error){
        console.log("Error" + error.message)
      }
      else {
        console.log("Unexpected Error has happened")
      } 
    }
  }
  
}
