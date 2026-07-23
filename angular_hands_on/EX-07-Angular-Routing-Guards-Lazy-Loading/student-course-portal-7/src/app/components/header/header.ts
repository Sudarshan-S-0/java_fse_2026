import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth.service';

// Header navigation component with authentication state controls
@Component({
  selector: 'app-header',
  imports: [RouterLink, CommonModule],
  templateUrl: './header.html',
  styleUrl: './header.css',
})
export class Header {
  constructor(public authService: AuthService) {}

  // Toggle login/logout state for route guard testing
  toggleAuth(): void {
    this.authService.toggleAuth();
  }
}

// Export alias for consistent component imports
export { Header as HeaderComponent };

