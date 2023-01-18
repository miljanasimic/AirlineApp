import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from "rxjs/operators";
import { UserFull } from 'src/models/user-full.model';

@Injectable({ providedIn: 'root' })
export class ProfileService {

    constructor(private http: HttpClient) {}

    getLoggedUserProfileInfo(id: string) {
        return this.http.get<any>('http://localhost:9000/users-service/user/user-info/?id=' + id)
        .pipe(map(responseData => {
            return new UserFull(responseData.id, responseData.email, responseData.firstName, responseData.lastName, responseData.phoneNumber);
        }));
    }

}