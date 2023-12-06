import { Employee } from "../config/employee-model";
import { AuditAbleModel } from "../super-model/audit-able-model";

export interface Transaction extends AuditAbleModel {
    employee?:Employee;
    transactionType?:string;
    amount?: number;
    transactionDate?: Date;
    description?: string;
}