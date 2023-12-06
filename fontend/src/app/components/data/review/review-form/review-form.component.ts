import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder } from '@angular/forms';
import { OperationStatus } from 'src/app/constants/status.enum';
import { Page } from 'src/app/dto/page.dto';
import { AppResponse } from 'src/app/dto/response.dto';
import { Customer } from 'src/app/model/config/customer-model';
import { Product } from 'src/app/model/config/product-model';
import { Review } from 'src/app/model/data/review-model';
import { CrudService } from 'src/app/services/crud.service';
import { NotificationUtil } from 'src/app/utils/notification.util';
import { populateFormControl } from 'src/app/utils/object.util';

@Component({
  selector: 'app-review-form',
  templateUrl: './review-form.component.html',
  styleUrls: ['./review-form.component.scss']
})
export class ReviewFormComponent implements OnInit {

  formGroup!: FormGroup;


  controls: any = {
    "product": new FormControl('', []),
    "customer": new FormControl('', []),
    "rating": new FormControl('', []),
    "comment": new FormControl('', [])
  };
  submitted = false;
  endPoint = "review";
  data: any = {};
  products: Product[] = [];
  customers: Customer[] = [];

  constructor(private formBuilder: FormBuilder, private service: CrudService, private noticeUtil: NotificationUtil) { }

  ngOnInit() {
    this.createForm();
    this.data = this.service.data;
    if (this.data.id) {
      populateFormControl(this.formGroup.controls, this.data);
    }

    this.service.getList('product', 0, 999999999).subscribe((res: AppResponse) => {
      const page: Page = res.data;
      this.products = page.content;
    })

    this.service.getList('customer', 0, 999999999).subscribe((res: AppResponse) => {
      const page: Page = res.data;
      this.customers = page.content;
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
    const values: Review = { 
      ...this.data, 
      ...this.formGroup.value, 
      product: {id: this.formGroup.value.product},
      customer: {id: this.formGroup.value.customer},
    };
    this.service.save(values, this.endPoint).subscribe(response => {
      this.formGroup.reset();
      this.submitted = false;
      this.noticeUtil.showResponseMessage(response);
    },
    (error: Error) => {
      const res = {status: OperationStatus.FAILURE, message: "Server error! Please contact with support team."};
      this.noticeUtil.showResponseMessage(res);
      console.log(this.endPoint, error);
    });
  }
}
