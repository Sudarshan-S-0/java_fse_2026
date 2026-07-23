import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

// Home dashboard page component for routing and guards demo
@Component({
  selector: 'app-home',
  imports: [CommonModule],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {
  portalName = 'Student Course Portal - Routing & Guards';
  coursesCount = 5;
}

// Export alias for component naming consistency
export { Home as HomeComponent };

