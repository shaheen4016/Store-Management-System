import { AuditAbleModel } from "../super-model/audit-able-model";

export interface Category extends AuditAbleModel {
    name?:string;
    description?:string;
}