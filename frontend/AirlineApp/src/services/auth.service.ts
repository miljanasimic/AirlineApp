import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Router } from '@angular/router';
import { User } from 'src/models/user.model';

interface LoginData {
    id: string,
    email: string
}


@Injectable({ providedIn: 'root' })
export class AuthService {
    user: BehaviorSubject<User> = new BehaviorSubject<User>(null);

    constructor(private http: HttpClient, private router: Router) {}

    registerUser(userData: FormData) {
        console.log(userData.get('email'), userData.get('password'),userData.get('firstName'), userData.get('lastName'),userData.get('phoneNum'))
        return this.http.post<any>("http://localhost:9000/users-service/user/register", {
            email: userData.get('email'),
            password: userData.get('password'),
            firstName: userData.get('firstName'),
            lastName: userData.get('lastName'),
            phoneNumber: userData.get('phoneNum'),
        })
    }

    login(email: string, password: string) {
        return this.http.post<LoginData>('http://localhost:9000/users-service/user/login', {
            email: email,
            password: password
        })
        .pipe(
            tap((responseData) => {
                let user = new User(responseData.id, responseData.email);
                this.user.next(user);
                localStorage.setItem('logged-user', JSON.stringify(user));
            })
        )
    }

    logout() {
        this.user.next(null);
        this.router.navigate(['/prijavljivanje']);
        localStorage.removeItem('logged-user');
    }

    autoLogin() {
        const loggedUser: {
          id: string;
          email: string } = JSON.parse(localStorage.getItem('logged-user'));
        if (!loggedUser) {
          return;
        }
    
        let user = new User(loggedUser.id, loggedUser.email);    
        this.user.next(user);
      }
}