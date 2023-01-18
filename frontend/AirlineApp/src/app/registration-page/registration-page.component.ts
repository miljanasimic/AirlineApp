import { Component, OnInit} from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { Router } from '@angular/router';
import { AuthService } from 'src/services/auth.service';

@Component({
  selector: 'app-registration-page',
  templateUrl: './registration-page.component.html',
  styleUrls: ['./registration-page.component.css']
})
export class RegistrationPageComponent implements OnInit{

  form: FormGroup;
  hide = true;
  error: string = '';
  success: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.form = new FormGroup({
      firstName: new FormControl('', Validators.required),
      lastName: new FormControl('', Validators.required),
      phoneNum: new FormControl('', Validators.required),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [
        Validators.required,
        Validators.minLength(6),
      ])

    })
  }

  onSubmit() {
    const data: FormData = new FormData();

    const firstName = this.form.get('firstName').value;
    data.append('firstName', firstName);
    const lastName = this.form.get('lastName').value;
    data.append('lastName', lastName);
    const email = this.form.get('email').value;
    data.append('email', email);
    const password = this.form.get('password').value;
    data.append('password',password);
    const phoneNum = this.form.get('phoneNum').value;
    data.append('phoneNum',phoneNum);

    this.authService.registerUser(data).subscribe({
      next : resp =>{
        console.log(resp)
        this.success="Uspesno ste se registrovali, možete se prijaviti na aplikaciju";
        setTimeout(() => {
          this.router.navigate(['prijavljivanje']);
        }, 3000);
      },
      error : err =>{
        console.log(err)
        this.error = err.error.sadrzaj;
        setTimeout(() => {
          this.error = '';
        }, 3000);
      }

    });


   
    

  }
}
