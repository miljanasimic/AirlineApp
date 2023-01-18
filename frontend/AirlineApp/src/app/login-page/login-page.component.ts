import { Component, OnInit} from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { Router } from '@angular/router';
import { AuthService } from 'src/services/auth.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit{

  form: FormGroup;
  hide = true;
  error: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.form = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required])
    })
  }

  onSubmit() {
    const data: FormData = new FormData();

    const email = this.form.get('email').value;
    data.append('email', email);
    const password = this.form.get('password').value;
    data.append('password',password);

    this.authService.login(email, password).subscribe({
      next: (resp) =>{
        this.router.navigate(['/pocetna']);
      },
      error: (err) =>{
        this.error=err.error.poruka;
        setTimeout(() => {
          this.error = '';
        }, 3000);
      }
    })
    



  }


}
