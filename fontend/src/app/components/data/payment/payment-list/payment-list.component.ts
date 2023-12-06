import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppResponse } from 'src/app/dto/response.dto';
import { Payment } from 'src/app/model/data/payment-model';
import { CrudService } from 'src/app/services/crud.service';

@Component({
  selector: 'app-payment-list',
  templateUrl: './payment-list.component.html',
  styleUrls: ['./payment-list.component.scss']
})
export class PaymentListComponent implements OnInit {

  displayedColumns: string[] = ['order','amount','paymentDate','paymentMethod','actions'];
  dataSource: Payment[] = [];

  constructor(private service: CrudService, private router: Router) { }

  ngOnInit(): void {
    this.service.getList('payment').subscribe((res: AppResponse) => {
      this.dataSource = res.data.content
    }
    );
  }

  
  delete(index: number) {
    let id = this.dataSource[index].id as number;
    this.service.delete(id, "payment").subscribe(() => {
      const newData = this.dataSource.filter((s, i) => i != index);
      this.dataSource = newData;
    })
  }

  edit(index: number) {
    this.service.data = { ...this.dataSource[index] };
    this.router.navigate(['/payment-form']);
  }
}
