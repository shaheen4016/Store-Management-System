import { Product } from "../config/product-model";
import { AuditAbleModel } from "../super-model/audit-able-model";

export interface Inventory extends AuditAbleModel {
    product?:Product;
    quantityInStock?:number;
    restockThreshold?:number;
    lastRestockDate?:Date;
    location?:string;
    notes?:string;
}