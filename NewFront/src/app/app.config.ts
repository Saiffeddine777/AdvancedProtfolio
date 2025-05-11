import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideStore } from '@ngrx/store';
import { provideEffects } from '@ngrx/effects';
import { usersListReducer } from './Users/users-list/UsersList.Reducer';
import { UserListEffect } from './Users/users-list/UsersList.effect';
import { provideHttpClient } from '@angular/common/http';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes), 
    provideHttpClient(),
    provideStore({
        usersList :usersListReducer
    }), 
    provideEffects([
      UserListEffect
    ])
  ]
};
