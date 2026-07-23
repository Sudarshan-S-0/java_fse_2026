import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { CommonModule } from '@angular/common';

// Component handling template-driven enrollment form with two-way data binding
@Component({
  selector: 'app-enrollment-form',
  imports: [CommonModule, FormsModule],
  templateUrl: './enrollment-form.component.html',
  styleUrls: ['./enrollment-form.component.css']
})
export class EnrollmentFormComponent {
  // Form model fields bound via [(ngModel)]
  studentName = '';
  studentEmail = '';
  courseId: number | null = null;
  preferredSemester = '';
  agreeToTerms = false;
  
  // Flag tracking successful form submission state
  submitted = false;

  // Handler for form submit event triggered by ngSubmit
  onSubmit(form: NgForm): void {
    if (form.valid) {
      console.log('Form Value:', form.value);
      console.log('Form Valid:', form.valid);
      this.submitted = true;
      
      // Auto-hide success message after 3 seconds
      setTimeout(() => {
        this.submitted = false;
      }, 3000);
    }
  }
}

