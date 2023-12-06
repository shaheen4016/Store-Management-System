import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder } from '@angular/forms';
import { OperationStatus } from 'src/app/constants/status.enum';
import { Page } from 'src/app/dto/page.dto';
import { AppResponse } from 'src/app/dto/response.dto';
import { Employee } from 'src/app/model/config/employee-model';
import { Transaction } from 'src/app/model/data/transaction-model';
import { CrudService } from 'src/app/services/crud.service';
import { NotificationUtil } from 'src/app/utils/notification.util';
import { populateFormControl } from 'src/app/utils/object.util';
import { OperatorFunction, Observable, debounceTime, distinctUntilChanged, map } from 'rxjs';

@Component({
  selector: 'app-transaction-form',
  templateUrl: './transaction-form.component.html',
  styleUrls: ['./transaction-form.component.scss']
})
export class TransactionFormComponent implements OnInit {

  formGroup!: FormGroup;


  controls: any = {
    "employee": new FormControl('', []),
    "transactionType": new FormControl('', []),
    "amount": new FormControl('', []),
    "transactionDate": new FormControl('', []),
    "description": new FormControl('', [])
  };
  submitted = false;
  endPoint = "transaction";
  data: any = {};
  employees: Employee[] = [];
  transactionTypes: string[] = ['Sales', 'Inventory', 'Bills','Product', 'customer',
   'Category', 'Discount', 'Employee', 'Supplier', 'Purchase', 'Review', 'Transaction' ];

  constructor(private formBuilder: FormBuilder, private service: CrudService, private noticeUtil: NotificationUtil) { }

  ngOnInit() {
    this.createForm();
    this.data = this.service.data;
    if (this.data.id) {
      populateFormControl(this.formGroup.controls, this.data);
    }

    this.service.getList('employee', 0, 999999999).subscribe((res: AppResponse) => {
      const page: Page = res.data;
      this.employees = page.content;
    })

  }

  createForm() {
    this.formGroup = this.formBuilder.group(this.controls);
  }

  submitForm() {
    this.submitted = true;
    if (this.formGroup.invalid) {
      return;
    }
    const values: Transaction = {
      ...this.data,
      ...this.formGroup.value,
      employee: { id: this.formGroup.value.employee },
    };
    this.service.save(values, this.endPoint).subscribe(response => {
      this.formGroup.reset();
      this.submitted = false;
      this.noticeUtil.showResponseMessage(response);
    },
      (error: Error) => {
        const res = { status: OperationStatus.FAILURE, message: "Server error! Please contact with support team." };
        this.noticeUtil.showResponseMessage(res);
        console.log(this.endPoint, error);
      });
  }


  searchTransactionType: OperatorFunction<string, readonly string[]> = (text$: Observable<string>) => {
    return text$.pipe(
      debounceTime(100),
      distinctUntilChanged(),
      map(this.searchTrnsType)
    );
  }

  searchTrnsType = (term: string) => {
    if (term.length) {
      return this.transactionTypes.filter((type) => {
        let t = type.toLocaleLowerCase();
        let search = term.toLocaleLowerCase();
        return t.includes(search);
      })
    } else {
      return [];
    }
  }
}
