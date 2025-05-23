import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../../Types/UserType';
import { StatusType } from './UsersList.state';
import { Store } from '@ngrx/store';
import { selectFetchError, selectFetchStatus, selectUserList } from './UsersList.selectors';
import { loadUsers } from './UsersList.actions';
import {MatCardModule} from '@angular/material/card';
import { NgFor } from '@angular/common';
import { DeleteUserComponent } from '../delete-user/delete-user.component';

@Component({
  selector: 'app-users-list',
  imports:[MatCardModule ,NgFor , DeleteUserComponent],
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.css']
})
export class UsersListComponent implements OnInit {
  users ! :User[]
  usersList$: Observable<User[]>;
  fetchStatus$: Observable<StatusType>;
  fetchError$: Observable<any>;

  constructor(private store: Store) {
    this.usersList$ = this.store.select(selectUserList);
    this.fetchStatus$ = this.store.select(selectFetchStatus);
    this.fetchError$ = this.store.select(selectFetchError);
  }

  ngOnInit(): void {
    this.store.dispatch(loadUsers());
    this.usersList$.subscribe((users) => {
      this.users = users; 
    });
  }
}