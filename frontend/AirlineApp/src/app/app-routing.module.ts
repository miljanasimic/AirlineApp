import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FlightsPageComponent } from './flights-page/flights-page.component';

import { HomePageComponent } from './home-page/home-page.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { ProfilePageComponent } from './profile-page/profile-page.component';
import { RecommendationPageComponent } from './recommendation-page/recommendation-page.component';
import { RegistrationPageComponent } from './registration-page/registration-page.component';
import { ReservationsPageComponent } from './reservations-page/reservations-page.component';

const routes: Routes = [
  { path: '', redirectTo: '/pocetna', pathMatch: 'full' },
  { path : 'pocetna', component : HomePageComponent },
  { path: 'registracija', component: RegistrationPageComponent},
  { path: 'prijavljivanje', component: LoginPageComponent},
  { path: 'profil', component: ProfilePageComponent},
  { path: 'letovi', component: FlightsPageComponent},
  { path: 'preporuke', component: RecommendationPageComponent},
  { path: 'rezervacije', component: ReservationsPageComponent},
  { path: '**', redirectTo: 'pocetna' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
