export class Flight {
    public date: Date
    public price: Number
    public baggage: Number
    public srcCode: String
    public dstCode: string
    public seatsRemaining: Number

    constructor(date: Date, price: Number, baggage: Number, srcCode: String, dstCode: string, seatsRemaining: Number) {
        this.date = date;
        this.price = price;
        this.baggage = baggage;
        this.srcCode = srcCode;
        this.dstCode = dstCode;
        this.seatsRemaining = seatsRemaining;
      }  
}