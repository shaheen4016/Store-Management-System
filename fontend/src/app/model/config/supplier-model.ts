import { AuditAbleModel } from "../super-model/audit-able-model";

export interface Supplier extends AuditAbleModel {
    name?:string;
    contactName?:string;
    contactEmail?:string;
    contactPhone?:number;
    address?:string;
    city?:string;
    state?:string;
    postalCode?:string;
    country?:string;
   
}