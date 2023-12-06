import { Customer } from "../config/customer-model";
import { Product } from "../config/product-model";
import { AuditAbleModel } from "../super-model/audit-able-model";

export interface Review extends AuditAbleModel {
    product?:Product;
    customer?:Customer;
    rating?:number;
    comment?:string;
}