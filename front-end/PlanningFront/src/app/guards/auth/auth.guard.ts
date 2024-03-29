import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  
  constructor(private router: Router) {}
  
  canActivate(): boolean {
    if (!this.isSignedIn()) {
      this.router.navigate(['/login']);
    }
    return true;
  }

  isSignedIn() {
    return !!sessionStorage.getItem('userMail');
  }
  
}
