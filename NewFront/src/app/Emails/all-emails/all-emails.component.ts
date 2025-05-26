import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { Email } from '../../Types/EmailType';
import { Observable } from 'rxjs';
import { StatusType } from '../../Types/StatusType';
import { selectALLEmails, selectFetchError, selectFetchStatus } from './AllEmails.selectors';
import { loadEmails } from './AllEmails.actions';
import { MatCardModule } from '@angular/material/card';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { NgFor, NgIf } from '@angular/common';
import { DeleteEmailComponent } from "../delete-email/delete-email.component";

@Component({
  selector: 'app-all-emails',
  imports: [
    MatCardModule,
    MatProgressSpinnerModule,
    MatGridListModule,
    MatIconModule,
    MatButtonModule,
    NgFor,
    NgIf,
    DeleteEmailComponent
],
  templateUrl: './all-emails.component.html',
  styleUrl: './all-emails.component.css'
})
export class ALlEmailsComponent implements OnInit{
  emails! :Email[]
  fetchedEmails$ !: Observable<Email[]> 
  fetchedStatus$ ! : Observable<StatusType>
  fetchedError$ !: Observable<any>
  constructor(private store :Store){
    this.fetchedEmails$ = this.store.select(selectALLEmails)
    this.fetchedStatus$ = this.store.select(selectFetchStatus)
    this.fetchedError$ = this.store.select(selectFetchError)
  }

  ngOnInit(): void {
    this.store.dispatch(loadEmails())
    this.fetchedEmails$.subscribe((allEmails)=>{
      this.emails = allEmails;  
    })
  }

  handleFilterDeltedEmail (deletedId  : string|undefined): void{
    this.emails = this.emails.filter((e)=>e.id !== deletedId)
  }
}
