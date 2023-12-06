import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { OperationStatus } from 'src/app/constants/status.enum';
import { Page } from 'src/app/dto/page.dto';
import { AppResponse } from 'src/app/dto/response.dto';
import { Product } from 'src/app/model/config/product-model';
import { Inventory } from 'src/app/model/data/inventory-model';
import { CrudService } from 'src/app/services/crud.service';
import { NotificationUtil } from 'src/app/utils/notification.util';
import { populateFormControl } from 'src/app/utils/object.util';

@Component({
  selector: 'app-inventory-form',
  templateUrl: './inventory-form.component.html',
  styleUrls: ['./inventory-form.component.scss']
})
export class InventoryFormComponent implements OnInit {

  formGroup!: FormGroup;


  controls: any = {
    "product": new FormControl('', []),
    "quantityInStock": new FormControl('', []),
    "restockThreshold": new FormControl('', []),
    "lastRestockDate": new FormControl('', []),
    "location": new FormControl('', []),
    "notes": new FormControl('', [])
  };
  submitted = false;
  endPoint = "inventory";
  data: any = {};
  products: Product[] = [];

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
    
  }

  createForm() {
    this.formGroup = this.formBuilder.group(this.controls);
  }

  submitForm() {
    this.submitted = true;
    if (this.formGroup.invalid) {
      return;
    }
    const values: Inventory = { ...this.data,
       ...this.formGroup.value,
      product: {id: this.formGroup.value.product}
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
