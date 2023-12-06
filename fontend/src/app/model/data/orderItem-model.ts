import { Product } from "../config/product-model";
import { AuditAbleModel } from "../super-model/audit-able-model";
import { Order } from "./order-model";

export interface OrderItem extends AuditAbleModel {
    order?: Order;
    product?: Product;
    quantity?: number;
    price?: number;

}