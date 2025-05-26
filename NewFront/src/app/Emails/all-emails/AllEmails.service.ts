import { Injectable } from "@angular/core";
import { envVar } from "../../env";
import { from, Observable } from "rxjs";
import { Email } from "../../Types/EmailType";
import axios from "axios";

@Injectable({
    providedIn: 'root',
})

export class AllEmailsService {
    private readonly fetchAllEmailsEndPoint:string = `${envVar}/api/emails/all`
    constructor(){}
    getEmails () : Observable<Email[]>{
        return from(
            axios.get<Email[]>(this.fetchAllEmailsEndPoint).then(response =>response.data)
        )
    }
}