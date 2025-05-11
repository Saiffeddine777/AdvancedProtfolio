import { User } from "../../Types/UserType";



export enum StatusType {
    Failure = 'Failure',
    Success = 'Success',
    StandBy = 'StandBy',
    Pending = 'Pending',
}
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