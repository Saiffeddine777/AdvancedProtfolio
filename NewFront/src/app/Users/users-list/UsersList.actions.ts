import { createAction, props } from "@ngrx/store";
import { User } from "../../Types/UserType";
import { StatusType } from "./UsersList.state";


export const loadUsers = createAction(
    "[Users list] laod users" 
)

export const loadUsersSuccess = createAction(
    "[Users list] laod users Success",
    props<{usersList:User[]}>() 
)

export const loadUsersFailure = createAction(
    "[Users list] laod users failure",
    props<{error : any}>()
)

export const updatFetchUsersStatus = createAction(
    "[Users list] load users status update",
    props<{status: StatusType}>()
)

export const resetUsersFailure = createAction(
    "[Users list] laod users failure",
)



