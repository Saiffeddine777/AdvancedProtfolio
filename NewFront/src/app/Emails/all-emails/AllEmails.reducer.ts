import { createReducer, on } from "@ngrx/store";
import { allEmails } from "./AllEmail.state";
import { loadEmails, loadEmailsFailure, loadEmailsSuccess, loadFetchEmailsStatus, resetEmailsFailure } from "./AllEmails.actions";
import { StatusType } from "../../Types/StatusType";


export const allEmailsReducer= createReducer(
    allEmails,
    on(loadEmails , (state)=>({
        ...state,
        status : StatusType.Pending,
        error:null
    })),
    on(loadEmailsSuccess , (state ,{allEmails})=>({
        ...state,
        status: StatusType.Success,
        allEmails:allEmails
    })),
    on (loadEmailsFailure , (state ,{error})=>({
        ...state,
        status: StatusType.Failure,
        error :error
    })),
    on(loadFetchEmailsStatus ,(state, {status})=>({
        ...state,
        status: status
    })),
    on(resetEmailsFailure , (state)=>({
        ...state,
        status : StatusType.StandBy,
        error : null
    }))

)