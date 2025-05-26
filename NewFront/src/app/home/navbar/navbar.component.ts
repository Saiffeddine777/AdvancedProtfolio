import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { NgFor } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  imports: [MatToolbarModule, MatButtonModule, NgFor],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css',
})
export class NavbarComponent {
  navMenuItems : string [] = ["CV","Projects","Skills","Contact" , "Emails" , "Users"] 
  buttonTitles : string [] = ["Sign-up", "Log-in"]

  constructor(private router :Router){}

  navigateToFromMenu (e:Event) : void{
    const target = e.target as HTMLButtonElement
    switch (target.innerText) {
      case "Contact":
        this.router.navigate(['/contactform'])
        break;
      case "Sign-up":
        this.router.navigate(['/signup'])
        break;
      case "Skills":
        this.router.navigate(['/skills'])
        break;
      case "Projects":
        this.router.navigate(['/projects'])
        break;
            case "Emails":
        this.router.navigate(['/allemails'])
        break;
        case "Users":
          this.router.navigate(['allusers'])
          break;
      default:
        break;
    }
  }

}
