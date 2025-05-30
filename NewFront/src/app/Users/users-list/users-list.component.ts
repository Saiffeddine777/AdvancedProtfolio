import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../../Types/UserType';
import { Store } from '@ngrx/store';
import { selectFetchError, selectFetchStatus, selectUserList } from './UsersList.selectors';
import { loadUsers } from './UsersList.actions';
import {MatCardModule} from '@angular/material/card';
import { NgFor } from '@angular/common';
import { DeleteUserComponent } from '../delete-user/delete-user.component';
import { StatusType } from '../../Types/StatusType';
import { MatButtonModule } from '@angular/material/button';
import { Router } from '@angular/router';

@Component({
  selector: 'app-users-list',
  imports: [MatCardModule, NgFor, DeleteUserComponent, MatButtonModule],
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.css']
})
export class UsersListComponent implements OnInit {
  users ! :User[]
  usersList$: Observable<User[]>;
  fetchStatus$: Observable<StatusType>;
  fetchError$: Observable<any>;

  constructor(private store: Store ,private router :Router) {
    this.usersList$ = this.store.select(selectUserList);
    this.fetchStatus$ = this.store.select(selectFetchStatus);
    this.fetchError$ = this.store.select(selectFetchError);
  }

  navigateToTheCreateUser (): void {
    this.router.navigate(["/createuser"])
  }

  ngOnInit(): void {
    this.store.dispatch(loadUsers());
    this.usersList$.subscribe((users) => {
      this.users = users; 
    });
  }
}