import { createReducer, on } from '@ngrx/store';
import { usersList } from './UsersList.state';
import {
  loadUsers,
  loadUsersSuccess,
  loadUsersFailure,
  updatFetchUsersStatus,
  resetUsersFailure,
} from './UsersList.actions';
import { StatusType } from '../../Types/StatusType';

export const usersListReducer = createReducer(
  usersList, // Initial state

  on(loadUsers, (state) => ({
    ...state,
    status: StatusType.Pending,
    error: null,
  })),

  on(loadUsersSuccess, (state, { usersList }) => {
    return {
        ...state,
        userList: usersList,
        status: StatusType.Success,
        error: null,
    };
}),

  on(loadUsersFailure, (state, { error }) => ({
    ...state,
    status: StatusType.Failure,
    error: error,
  })),

  on(updatFetchUsersStatus, (state, { status }) => ({
    ...state,
    status: status,
  })),

  on(resetUsersFailure, (state) => ({
    ...state,
    status: StatusType.StandBy,
    error: null,
  }))
);