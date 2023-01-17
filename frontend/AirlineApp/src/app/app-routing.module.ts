import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HomePageComponent } from './home-page/home-page.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { RegistrationPageComponent } from './registration-page/registration-page.component';

const routes: Routes = [
  { path: '', redirectTo: '/pocetna', pathMatch: 'full' },
  { path : 'pocetna', component : HomePageComponent },
  { path: 'registracija', component: RegistrationPageComponent},
  { path: 'prijavljivanje', component: LoginPageComponent},
  { path: '**', redirectTo: 'pocetna' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
