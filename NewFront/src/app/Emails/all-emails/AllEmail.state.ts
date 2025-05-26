import { Email } from "../../Types/EmailType";
import { StatusType } from "../../Types/StatusType";


export type EmailsFetchObjectType ={
    allEmails : Email[],
    status:StatusType,
    error :any
}

export const allEmails :EmailsFetchObjectType ={
    allEmails : [],
    status : StatusType.StandBy,
    error :null
}