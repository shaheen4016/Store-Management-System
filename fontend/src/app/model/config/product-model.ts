import { AuditAbleModel } from "../super-model/audit-able-model";
import { Category } from "./category-model";
import { Supplier } from "./supplier-model";

export interface Product extends AuditAbleModel {
    name?:string;
    description?:string;
    price?:number;
    quantityInStock?: number;
    supplier?:Supplier;
    category?:Category;
   
}