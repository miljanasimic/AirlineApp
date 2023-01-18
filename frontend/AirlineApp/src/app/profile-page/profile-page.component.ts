import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Router } from '@angular/router';
import { AuthService } from 'src/services/auth.service';
import { ProfileService } from 'src/services/profile.service';
import { Subscription } from 'rxjs';
import { UserFull } from 'src/models/user-full.model';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.css']
})
export class ProfilePageComponent  implements OnInit {

  form: FormGroup;
  user: UserFull;
  userSub: Subscription;
  loggedId: string;
  rdonly = true;
  isLoading: boolean = true;
  error: string = '';
  success: string = '';

  constructor(private router: Router, private profileService: ProfileService,
    private authService: AuthService) { }

  ngOnInit(): void {

    this.form = new FormGroup({
      email: new FormControl(''),
      firstName: new FormControl(''),
      lastName: new FormControl(''),
      phoneNumber: new FormControl(''),
      newPassword: new FormControl('')
    })
    
    this.authService.user.subscribe(user => {
      const id=user.id;
      console.log(user)
      this.profileService.getLoggedUserProfileInfo(user.id).subscribe({
        next: response => {
          this.user=response;
          this.form.patchValue({
            email: this.user.email,
            firstName: this.user.firstName,
            lastName: this.user.lastName,
            phoneNumber: this.user.phoneNumber,
          })

        },
        error: err => {
          console.log(err);
        }
      })
    }).unsubscribe();
  }

  onSubmit() {

  }

  onCancel() {

  }
}
