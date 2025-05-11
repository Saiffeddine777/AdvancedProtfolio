import { Component } from '@angular/core';
import { MatIconModule, MatIconRegistry } from '@angular/material/icon';
import {  DomSanitizer } from '@angular/platform-browser';
@Component({
  selector: 'app-footer',
  imports: [ MatIconModule ],
  providers:[],
  templateUrl: './footer.component.html',
  styleUrl: './footer.component.css'
})
export class FooterComponent {
  constructor(private matIconRegisty: MatIconRegistry, private domSanitizer: DomSanitizer) {
    this.matIconRegisty.addSvgIcon(
      "linkedin",
      this.domSanitizer.bypassSecurityTrustResourceUrl("assets/icons8-linkedin-logo.svg"
      ))
    this.matIconRegisty.addSvgIcon(
      "github",
      this.domSanitizer.bypassSecurityTrustResourceUrl("assets/icons8-github-logo.svg"
      ))
    this.matIconRegisty.addSvgIcon(
      "instagram",
      this.domSanitizer.bypassSecurityTrustResourceUrl("assets/icons8-instagram-logo.svg"
      ))
  }
}
