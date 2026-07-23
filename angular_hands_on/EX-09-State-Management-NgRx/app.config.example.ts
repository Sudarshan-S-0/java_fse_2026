// Example: app.config.ts for Standalone Angular with NgRx state management
import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { provideStore, provideState } from '@ngrx/store';
import { provideEffects } from '@ngrx/effects';
import { provideStoreDevtools } from '@ngrx/store-devtools';

import { routes } from './app.routes';
import { courseReducer } from './store/course/course.reducer';
import { enrollmentReducer } from './store/enrollment/enrollment.reducer';
import { CourseEffects } from './store/course/course.effects';
import { authInterceptor } from './interceptors/auth.interceptor';

// Main application configuration configuring NgRx store, effects, and devtools
export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideHttpClient(withInterceptors([authInterceptor])),
    
    // Initialize root NgRx Store
    provideStore(),
    
    // Register feature states and reducers
    provideState({ name: 'course', reducer: courseReducer }),
    provideState({ name: 'enrollment', reducer: enrollmentReducer }),
    
    // Register side-effect handlers (RxJS Effects)
    provideEffects([CourseEffects]),
    
    // Enable Redux DevTools extension for state debugging
    provideStoreDevtools({
      maxAge: 25, // Retain last 25 action state snapshots
      logOnly: false, // Enable full state time-travel debugging
      autoPause: true, // Pause recording when extension window is closed
      trace: false,
      traceLimit: 75,
    }),
  ]
};

