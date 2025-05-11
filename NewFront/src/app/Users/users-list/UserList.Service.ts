import { Injectable } from "@angular/core";
import axios from "axios";
import { envVar } from "../../env";
import { from, Observable } from "rxjs";
import { User } from "../../Types/UserType";

@Injectable({
    providedIn : 'root'
})
export class UserListService {

    private readonly fetchUserListEndPoint :string = `${envVar}/api/users/all`;  

    constructor(){}

    getUsers(): Observable<User[]> {
        return from(
            axios.get<User[]>(this.fetchUserListEndPoint).then((response) => response.data)
        );
    }
    


} 