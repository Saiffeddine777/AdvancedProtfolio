import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { CreateEmailComponent } from './Emails/create-email/create-email.component';
import { SignUpComponent } from './Users/sign-up/sign-up.component';
import { AllSkillsComponent } from './Skills/all-skills/all-skills.component';
import { CreateSkillComponent } from './Skills/create-skill/create-skill.component';

export const routes: Routes = [
    {path :"" ,component: HomeComponent},
    {path :"contactform" , component : CreateEmailComponent},
    {path : "signup" , component :SignUpComponent},
    {path:"skills", component : AllSkillsComponent},
    {path: "createskill", component: CreateSkillComponent}
];
