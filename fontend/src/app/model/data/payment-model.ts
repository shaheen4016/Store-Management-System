import { AuditAbleModel } from "../super-model/audit-able-model";
import { Order } from "./order-model";

export interface Payment extends AuditAbleModel {
    order?:Order;
    amount?:number;
    paymentDate?:Date;
    paymentMethod?:string;
}