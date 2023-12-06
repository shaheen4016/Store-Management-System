import { Customer } from "../config/customer-model";
import { AuditAbleModel } from "../super-model/audit-able-model";

export interface Order extends AuditAbleModel {
    customer?:Customer;
    orderDate?:Date;
    totalAmount?:number;

}