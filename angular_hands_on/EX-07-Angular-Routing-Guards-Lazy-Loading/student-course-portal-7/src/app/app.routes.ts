import { Routes } from '@angular/router';
import { Home } from './pages/home/home';
import { CourseList } from './pages/course-list/course-list';
import { CourseDetail } from './pages/course-detail/course-detail';
import { StudentProfile } from './components/student-profile/student-profile';
import { EnrollmentFormComponent } from './pages/enrollment-form/enrollment-form.component';
import { ReactiveEnrollmentFormComponent } from './pages/reactive-enrollment-form/reactive-enrollment-form.component';
import { NotFound } from './pages/not-found/not-found';
import { authGuard } from './guards/auth.guard';
import { unsavedChangesGuard } from './guards/unsaved-changes.guard';

// Application route configuration with parameters, guards, and wildcard fallback
export const routes: Routes = [
  // Default route loading Home page
  { path: '', component: Home },
  
  // Course list catalog route
  { path: 'courses', component: CourseList },
  
  // Dynamic route parameter loading Course Detail page for specific course ID
  { path: 'courses/:id', component: CourseDetail },
  
  // Protected student profile route requiring authentication
  { 
    path: 'profile', 
    component: StudentProfile,
    canActivate: [authGuard] 
  },

  
  // Template-driven enrollment form route
  { path: 'enroll', component: EnrollmentFormComponent },
  
  // Protected reactive enrollment form route with unsaved changes protection guard
  { 
    path: 'enroll-reactive', 
    component: ReactiveEnrollmentFormComponent, 
    canActivate: [authGuard], 
    canDeactivate: [unsavedChangesGuard] 
  },
  
  // Wildcard route catching all invalid URLs and showing 404 Not Found page
  { path: '**', component: NotFound }
];

