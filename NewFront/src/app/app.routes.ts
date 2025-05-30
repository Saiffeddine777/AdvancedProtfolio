import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { CreateEmailComponent } from './Emails/create-email/create-email.component';
import { SignUpComponent } from './Users/sign-up/sign-up.component';
import { AllSkillsComponent } from './Skills/all-skills/all-skills.component';
import { CreateSkillComponent } from './Skills/create-skill/create-skill.component';
import { OneSkillComponent } from './Skills/one-skill/one-skill.component';
import { UpdateOneSkillComponent } from './Skills/update-one-skill/update-one-skill.component';
import { ProjectListComponent } from './Projects/project-list/project-list.component';
import { CreateProjectComponent } from './Projects/create-project/create-project.component';
import { OneProjectComponent } from './Projects/one-project/one-project.component';
import { UpdateProjectComponent } from './Projects/update-project/update-project.component';
import { ALlEmailsComponent } from './Emails/all-emails/all-emails.component';
import { UsersListComponent } from './Users/users-list/users-list.component';
import { CreateUserComponent } from './Users/create-user/create-user.component';
import { LogInComponent } from './Users/log-in/log-in.component';

export const routes: Routes = [
    {path:"" ,component: HomeComponent},
    {path:"contactform" , component : CreateEmailComponent},
    {path:"signup" , component :SignUpComponent},
    {path:"skills", component : AllSkillsComponent},
    {path:"createskill", component: CreateSkillComponent},
    {path:"oneskill" , component: OneSkillComponent},
    {path:"updateskill",component: UpdateOneSkillComponent},
    {path:"projects" ,component : ProjectListComponent},
    {path:"createproject" , component : CreateProjectComponent},
    {path:"oneproject" , component : OneProjectComponent},
    {path:"updateproject", component: UpdateProjectComponent},
    {path:"allemails",component : ALlEmailsComponent},
    {path:"allusers", component: UsersListComponent},
    {path:"createuser", component: CreateUserComponent},
    {path:"signin" , component : LogInComponent}
];
