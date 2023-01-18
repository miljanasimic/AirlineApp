import { Component, OnInit } from '@angular/core';
import { FlightService} from 'src/services/flight.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { Airport } from 'src/models/airport.model';

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
  flight

  constructor(private flightService: FlightService) { }

  countries: [string]
  selectedCountry: string
  cities: [Airport]
  selectedSrcAirport: string
  directAirports: [Airport]
  selectedDstAirport: string
  dates: [string]
  selectedDate: string
  returnFlight = null
  returnDates: [string]
  selectedReturnDate: string


  ngOnInit(): void {

    this.form = new FormGroup({
      countrySelect: new FormControl(''),
      airportSelect: new FormControl(''),
      directAirportSelect: new FormControl('')
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

  returnDatesFilter = (d: Date | null): boolean => {
    try {
      const time=d.toLocaleDateString();
      var flightDates = [];
      this.returnDates.forEach(el=>{
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

  changeReturnDateEvent(event){
    let d = event.value;
    d.setDate(d.getDate()+1)
    this.selectedDate=d.toISOString().split('T')[0]
  }

  flightTypeChanged(event) {
    this.returnFlight = event.value;
    console.log(this.returnFlight)
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

  
}
