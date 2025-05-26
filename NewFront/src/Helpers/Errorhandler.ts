import { MatSnackBar } from "@angular/material/snack-bar";
import axios from "axios";


export const apiErrorHander = function (snackBar: MatSnackBar, error: any) :void{

    if (axios.isAxiosError(error)) {
        const log: string = error.response?.data?.message ||
            error.message ||
            'Unknown error'
        console.log('Axios Error : ' + log);
        snackBar.open(log, 'Close', {
            duration: 4000,
            panelClass: ['snackbar-failure']
        })

    } else if (error instanceof Error) {
        console.log('Error' + error.message);
        snackBar.open(error.message, 'Close', {
            duration: 4000,
            panelClass: ['snackbar-failure']
        })
    } else {
        const log: string = 'Unexpected Error has happened'
        console.log(log);
        snackBar.open(log, 'Close', {
            duration: 4000,
            panelClass: ['snackbar-failure']
        })
    }
}
