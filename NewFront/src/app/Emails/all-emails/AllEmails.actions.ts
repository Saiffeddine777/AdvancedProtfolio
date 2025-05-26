import { createAction ,props } from "@ngrx/store";
import {Email} from "../../Types/EmailType"
import { StatusType } from "../../Types/StatusType";

export const loadEmails = createAction(
    "[All emails] load emails"
)

export const loadEmailsSuccess = createAction(
    "[All Emails] load Emails success",
    props<{allEmails:Email[]}>()
)

export const loadEmailsFailure = createAction(
    "[All Emails] load Emails Failure",
    props<{error:any}>()
)

export const loadFetchEmailsStatus = createAction(
    "[All Emails] load Emails Status Update",
    props<{status:StatusType}>()
)

export const resetEmailsFailure = createAction(
    "[All Emails] load Emails Failure"
)