import { Component, Input, Output, EventEmitter, OnChanges, SimpleChanges } from '@angular/core';
import { CommonModule } from '@angular/common';

// Interface defining course structure
export interface Course {
  id: number;
  name: string;
  code: string;
  credits: number;
}

// Child component representing individual course card
@Component({
  selector: 'app-course-card',
  imports: [CommonModule],
  templateUrl: './course-card.html',
  styleUrl: './course-card.css',
})
export class CourseCard implements OnChanges {
  // @Input: Receive course data object from parent component
  @Input() course!: Course;
  
  // @Output: Emit course ID when user requests enrollment
  @Output() enrollRequested = new EventEmitter<number>();

  // Lifecycle Hook: Triggers when @Input properties change
  ngOnChanges(changes: SimpleChanges): void {
    if (changes['course']) {
      console.log('Previous value:', changes['course'].previousValue);
      console.log('Current value:', changes['course'].currentValue);
    }
  }

  // Event handler for enroll button click
  onEnroll(): void {
    this.enrollRequested.emit(this.course.id);
  }
}

