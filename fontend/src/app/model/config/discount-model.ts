import { AuditAbleModel } from "../super-model/audit-able-model";

export interface Discount extends AuditAbleModel {
    name?:string;
    description?:string;
    discountType?:string;
    value?:number;
    startDate?:Date;
    endDate?:Date;
}