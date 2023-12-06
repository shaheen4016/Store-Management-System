import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder } from '@angular/forms';
import { OperationStatus } from 'src/app/constants/status.enum';
import { Page } from 'src/app/dto/page.dto';
import { AppResponse } from 'src/app/dto/response.dto';
import { Product } from 'src/app/model/config/product-model';
import { Order } from 'src/app/model/data/order-model';
import { OrderItem } from 'src/app/model/data/orderItem-model';
import { CrudService } from 'src/app/services/crud.service';
import { NotificationUtil } from 'src/app/utils/notification.util';
import { populateFormControl } from 'src/app/utils/object.util';

@Component({
  selector: 'app-order-item-form',
  templateUrl: './order-item-form.component.html',
  styleUrls: ['./order-item-form.component.scss']
})
export class OrderItemFormComponent implements OnInit {

  formGroup!: FormGroup;


  controls: any = {
    "order": new FormControl('', []),
    "product": new FormControl('', []),
    "quantity": new FormControl('', []),
    "price": new FormControl('', [])
  };
  submitted = false;
  endPoint = "orderItem";
  data: any = {}
  orders: Order[] = [];
  products: Product[] = [];

  constructor(private formBuilder: FormBuilder, private service: CrudService, private noticeUtil: NotificationUtil) { }

  ngOnInit() {
    this.createForm();
    this.data = this.service.data;
    if (this.data.id) {
      populateFormControl(this.formGroup.controls, this.data);
    }

    this.service.getList('order', 0, 999999999).subscribe((res:AppResponse) => {
      const page: Page = res.data;
      this.orders = page.content;
    })

    this.service.getList('product', 0, 999999999).subscribe((res:AppResponse) => {
      const page: Page = res.data;
      this.products = page.content;
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
    const values: OrderItem = { 
      ...this.data,
      ...this.formGroup.value, 
      order: {id: this.formGroup.value.order},
      product: {id: this.formGroup.value.product},
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
