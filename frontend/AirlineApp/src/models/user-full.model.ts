import { User } from "./user.model";

export class UserFull extends User {
    public firstName: string;
    public lastName: string;
    public phoneNumber: string;
   
    constructor(id: string, email: string, firstName: string, lastName: string, phone: string) {
    super(id, email)
      this.firstName = firstName;
      this.lastName = lastName;
      this.phoneNumber = phone;
    }  
}