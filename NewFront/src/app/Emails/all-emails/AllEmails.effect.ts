import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { AllEmailsService } from './AllEmails.service';
import {
  loadEmails,
  loadEmailsFailure,
  loadEmailsSuccess,
} from './AllEmails.actions';
import { catchError, map, mergeMap, of } from 'rxjs';
import { Email } from '../../Types/EmailType';

@Injectable()
export class AllEmailsEffect {
  constructor(
    private actions$: Actions,
    private allEmailsService: AllEmailsService
  ) {}

  loadEmails$ = createEffect(() =>
    this.actions$.pipe(
      ofType(loadEmails),
      mergeMap(() =>
        this.allEmailsService.getEmails().pipe(
          map((allEmails: Email[]) => loadEmailsSuccess({ allEmails })),
          catchError((error) => of(loadEmailsFailure({ error })))
        )
      )
    )
  );
}
