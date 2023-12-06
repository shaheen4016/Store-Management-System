import { AuditAbleModel } from "../super-model/audit-able-model";

export interface Customer extends AuditAbleModel {
    name?:string;
    email?:string;
    phoneNumber?:string;
    address?:string;
    city?:string;
    state?:string;
    postalCode?:string;
    country?:String;
}