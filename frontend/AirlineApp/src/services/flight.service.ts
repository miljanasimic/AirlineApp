import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from "rxjs/operators";

@Injectable({ providedIn: 'root' })
export class FlightService {

    constructor(private http: HttpClient) {}

    getAllCountries() {
        return this.http.get<any>('http://localhost:9000/flights-service/airport/countries')
        .pipe(map(responseDate => {
            return responseDate;
        }))
    }

    getAllAirportCities(coutry: string){
        return this.http.get<any>('http://localhost:9000/flights-service/airport/byCountry?country='+coutry)
        .pipe(map(responseDate => {
            return responseDate;
        }))
    }

    getDirectAirportsFromSrc(srcCode: string) {
        return this.http.get<any>('http://localhost:9000/flights-service/airport/directAirports?startAirportCode='+srcCode)
        .pipe(map(responseDate => {
            return responseDate;
        }))
    }

    getPossibleDatesFromTo(src: string, dst: string) {
        return this.http.get<any>('http://localhost:9000/flights-service/airport/allPossibleFlightsDates?srcAirportCode='+src+'&dstAirportCode='+dst)
        .pipe(map(responseDate => {
            return responseDate;
        }))
    }
    
}