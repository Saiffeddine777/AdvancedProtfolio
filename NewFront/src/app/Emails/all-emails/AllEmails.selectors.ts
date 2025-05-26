import { createFeatureSelector, createSelector } from "@ngrx/store";
import { EmailsFetchObjectType } from "./AllEmail.state";
import { StatusType } from "../../Types/StatusType";
StatusType
export const selectAllEmailsState  = createFeatureSelector<EmailsFetchObjectType>('allEmails')

export const selectALLEmails =createSelector(
    selectAllEmailsState,
    (state)=>state?.allEmails ||[]
)

export const selectFetchStatus = createSelector(
  selectAllEmailsState,
  (state) => state?.status || StatusType.StandBy 
);

export const selectFetchError = createSelector(
  selectAllEmailsState,
  (state) => state?.error || null
);