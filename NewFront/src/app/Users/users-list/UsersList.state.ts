import { User } from "../../Types/UserType";
import { StatusType } from "../../Types/StatusType";



export type UsersFetchObjectType ={
    userList : User [],
    status : StatusType
    error : any 
}

export const usersList : UsersFetchObjectType = {
    userList : [],
    status : StatusType.StandBy,
    error :null
}