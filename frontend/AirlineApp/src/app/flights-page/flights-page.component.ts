import { Component, OnInit } from '@angular/core';
import { FlightService} from 'src/services/flight.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { Airport } from 'src/models/airport.model';
import { Flight } from 'src/models/flight.model';

@Component({
  selector: 'app-flights-page',
  templateUrl: './flights-page.component.html',
  styleUrls: ['./flights-page.component.css']
})
export class FlightsPageComponent implements OnInit{
  form: FormGroup;
  countriesSub: Subscription;
  citiesSub: Subscription;
  directAirportsSub: Subscription;
  flightDatesSub: Subscription;
  //passengerNum: Number = 1;

  constructor(private flightService: FlightService) { }

  countries: [string]
  selectedCountry: string
  cities: [Airport]
  selectedSrcAirport: string
  directAirports: [Airport]
  selectedDstAirport: string
  dates: [string]
  selectedDate: string
  selectedPassNum
  flightsResult: [Flight]


  ngOnInit(): void {

    this.form = new FormGroup({
      countrySelect: new FormControl(''),
      airportSelect: new FormControl(''),
      directAirportSelect: new FormControl(''),
      passengerNum: new FormControl(''),
    });

    

    this.countriesSub = this.flightService.getAllCountries().subscribe({
      next: response => {
          console.log(response);
          this.countries=response;
      }, error: err => {
        console.log(err);
      }
    })
  }

  datesFilter = (d: Date | null): boolean => {
    try {
      const time=d.toLocaleDateString();
      var flightDates = [];
      this.dates.forEach(el=>{
        flightDates.push(new Date(el))
      })
      return flightDates.find(x=> x.toLocaleDateString()==time)
    } catch (ex) {
      return false
    }
  }


  changeDateEvent(event){
    let d = event.value;
    d.setDate(d.getDate()+1)
    this.selectedDate=d.toISOString().split('T')[0]
  }

  selectCountry(event: Event) {
    this.selectedCountry = (event.target as HTMLSelectElement).value
    this.citiesSub = this.flightService.getAllAirportCities(this.selectedCountry).subscribe({
      next: response => {
        console.log(response);
        this.cities = response;
    }, error: err => {
      console.log(err);
    }
    })
  }

  selectAirport(event: Event) {
    this.selectedSrcAirport = (event.target as HTMLSelectElement).value;
    this.directAirportsSub = this.flightService.getDirectAirportsFromSrc(this.selectedSrcAirport).subscribe({
      next: response => {
        console.log(response);
        this.directAirports = response;
    }, error: err => {
      console.log(err);
    }
    })
  }

  selectDirectAirport(event: Event) {
    this.selectedDstAirport = (event.target as HTMLSelectElement).value;
    this.flightDatesSub = this.flightService.getPossibleDatesFromTo(this.selectedSrcAirport, this.selectedDstAirport).subscribe({
      next: response => {
        console.log(response);
        this.dates = response;
    }, error: err => {
      console.log(err);
    }
    })
  }

  selectPassNum(event: Event) {
    this.selectedPassNum = (event.target as HTMLSelectElement).value;
  }

  submited() {
    this.flightService.getFlightsBySearch(this.selectedSrcAirport, this.selectedDstAirport, this.selectedDate, this.selectedPassNum).subscribe({
      next : resp =>{
        // this.flightsResult = [];
        // resp.forEach((el)=> {
        //   this.flightsResult.push(new Flight(el.date, el.price, el.baggage, el.srcCode, el.dstCode, el.seatsRemaining))
        // })
        this.flightsResult = resp;
        console.log(this.flightsResult)
        console.log(typeof this.flightsResult[0].date)
        
      },
      error : err =>{
        console.log(err)
       /* this.error = err.error.sadrzaj;
        setTimeout(() => {
          this.error = '';
        }, 3000);*/
      }

    });
  }
  
}
