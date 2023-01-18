import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from "rxjs/operators";

@Injectable({ providedIn: 'root' })
export class ReservationService {

    constructor(private http: HttpClient) {}

    /*getUserReservations(id: string) {
        return this.http.get<any>('http://localhost:9000/reservations-service/reservation/review/?passengerId=' + id)
        .pipe(map(responseData => {
            return new ;
        }));
    }*/


}