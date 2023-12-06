import { Product } from "../config/product-model";
import { AuditAbleModel } from "../super-model/audit-able-model";

export interface Purchase extends AuditAbleModel {
    supplier: string;
    challanNo: string;
    product: Product;
    purchaseDate: Date;
    quantity: number;
    totalAmount: number;
}