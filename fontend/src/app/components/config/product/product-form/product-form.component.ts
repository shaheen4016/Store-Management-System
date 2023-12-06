import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { OperationStatus } from 'src/app/constants/status.enum';
import { Page } from 'src/app/dto/page.dto';
import { AppResponse } from 'src/app/dto/response.dto';
import { Category } from 'src/app/model/config/category-model';
import { Product } from 'src/app/model/config/product-model';
import { Supplier } from 'src/app/model/config/supplier-model';
import { CrudService } from 'src/app/services/crud.service';
import { NotificationUtil } from 'src/app/utils/notification.util';
import { populateFormControl } from 'src/app/utils/object.util';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.scss']
})
export class ProductFormComponent implements OnInit {

  formGroup!: FormGroup;


  controls: any = {
    "name": new FormControl('', []),
    "description": new FormControl('', []),
    "price": new FormControl('', []),
    "quantityInStock": new FormControl('', []),
    "supplier": new FormControl('', []),
    "category": new FormControl('', [])
  };
  submitted = false;
  endPoint = "product";
  data: any = {}
  suppliers: Supplier[] = [];
  categories: Category[] = [];

  constructor(private formBuilder: FormBuilder, private service: CrudService, private noticeUtil: NotificationUtil) { }

  ngOnInit() {
    this.createForm();
    this.data = this.service.data;
    if (this.data.id) {
      populateFormControl(this.formGroup.controls, this.data);
    }

    this.service.getList('supplier', 0, 999999999).subscribe((res: AppResponse) => {
      const page: Page = res.data;
      this.suppliers = page.content;
    })

    this.service.getList('category', 0, 999999999).subscribe((res: AppResponse) => {
      const page: Page = res.data;
      this.categories = page.content;
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
    const values: Product = { 
      ...this.data, 
      ...this.formGroup.value, 
      supplier: {id: this.formGroup.value.supplier},
      category: {id: this.formGroup.value.category},
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

}
