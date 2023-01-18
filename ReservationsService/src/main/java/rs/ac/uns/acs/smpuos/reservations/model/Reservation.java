package rs.ac.uns.acs.smpuos.reservations.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Reservation {

    @Id
    private String id;
    private Date date;
    private Integer price;
    private Integer baggagePrice;
    private Integer baggageNumber;
    private Integer passengerNumber;
    private String passengerId;
    private Long flightId;

    public Reservation(Date date, Integer price, Integer baggagePrice, Integer baggageNumber, Integer passengerNumber, String passengerId, Long flightId) {
        this.date = date;
        this.price = price;
        this.baggagePrice = baggagePrice;
        this.baggageNumber = baggageNumber;
        this.passengerNumber = passengerNumber;
        this.passengerId = passengerId;
        this.flightId = flightId;
    }

    public String getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getBaggagePrice() {
        return baggagePrice;
    }

    public void setBaggagePrice(Integer baggagePrice) {
        this.baggagePrice = baggagePrice;
    }

    public Integer getBaggageNumber() {
        return baggageNumber;
    }

    public void setBaggageNumber(Integer baggageNumber) {
        this.baggageNumber = baggageNumber;
    }

    public Integer getPassengerNumber() {
        return passengerNumber;
    }

    public void setPassengerNumber(Integer passengerNumber) {
        this.passengerNumber = passengerNumber;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public String toString(){
        return "Reservation [id=" + id + ", date=" + date + ", price=" + price
                + ", baggagePrice=" + baggagePrice + ", baggageNumber=" + baggageNumber
                + ", passengerNumber=" + passengerNumber + ", passengerId=" + passengerId
                + ", flightId=" + flightId + "]";
    }
}
