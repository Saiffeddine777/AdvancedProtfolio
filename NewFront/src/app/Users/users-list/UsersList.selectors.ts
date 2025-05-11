import { createFeatureSelector, createSelector } from '@ngrx/store';
import { StatusType, UsersFetchObjectType } from './UsersList.state';


// Feature Selector
export const selectUsersListState = createFeatureSelector<UsersFetchObjectType>('usersList');

// Selectors
export const selectUserList = createSelector(
  selectUsersListState,
  (state) => state?.userList || [] // Safeguard: Return an empty array if undefined
);

export const selectFetchStatus = createSelector(
  selectUsersListState,
  (state) => state?.status || StatusType.StandBy // Safeguard: Return default status
);

export const selectFetchError = createSelector(
  selectUsersListState,
  (state) => state?.error || null // Safeguard: Return null if no error
);