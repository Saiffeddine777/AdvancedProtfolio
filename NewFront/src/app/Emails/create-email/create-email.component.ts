import { Component } from '@angular/core';
import { Email } from '../../Types/EmailType';
import axios from 'axios';
import { envVar } from '../../env';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-create-email',
  imports: [MatFormFieldModule, MatInputModule, MatButtonModule],
  templateUrl: './create-email.component.html',
  styleUrl: './create-email.component.css',
})
export class CreateEmailComponent {
  emailInserted: Email = {
    id: undefined,
    senderEmail: '',
    subject: '',
    text: '',
  };

  handleFieldsChange(e: Event): void {
    const target = e.target as HTMLInputElement;
    switch (target.placeholder) {
      case 'Submit Email':
        this.emailInserted.senderEmail = target.value as string;
        break;
      case 'Submit Subject':
        this.emailInserted.subject = target.value as string;
        break;
      case 'Submit Text':
        this.emailInserted.text = target.value as string;
        break;
      default:
        break;
    }
  }

  async handleSubmitEmail(): Promise<void> {
    try {
      if(this.emailInserted.senderEmail ==='' ||  this.emailInserted.subject ==='' || this.emailInserted.text ==='' ){
        throw new Error("Please fill all the form fields.")
      }
      await axios.post(`${envVar}/api/emails/create`, this.emailInserted);
    } catch (error) {
      if (axios.isAxiosError(error)) {
        console.log('Axios error: ' + error.message);
      } else if (error instanceof Error) {
        console.log('Error : ' + error.message);
      } else {
        console.log('Unexpected Error: ', error);
      }
    }
  }
}
