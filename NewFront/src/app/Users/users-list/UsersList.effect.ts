import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { UserListService } from './UserList.Service';
import {
  loadUsers,
  loadUsersFailure,
  loadUsersSuccess,
} from './UsersList.actions';
import { catchError, map, mergeMap, of } from 'rxjs';
import { User } from '../../Types/UserType';

@Injectable()
export class UserListEffect {
  constructor(
    private actions$: Actions,
    private userListService: UserListService
  ) {}

  loadUsers$ = createEffect(() =>
    this.actions$.pipe(
      ofType(loadUsers),
      mergeMap(() =>
        this.userListService.getUsers().pipe(
          map((usersList: User[]) => loadUsersSuccess({ usersList })),
          catchError((error) => of(loadUsersFailure({ error })))
        )
      )
    )
  );
}