import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Inventory } from 'src/app/model/data/inventory-model';
import { CrudService } from '../../../../services/crud.service';
import { AppResponse } from 'src/app/dto/response.dto';


@Component({
  selector: 'app-inventory-list',
  templateUrl: './inventory-list.component.html',
  styleUrls: ['./inventory-list.component.scss']
})
export class InventoryListComponent implements OnInit {

  displayedColumns: string[] = ['product','quantityInStock','restockThreshold','lastRestockDate','location','notes','actions'];
  dataSource: Inventory[] = [];

  constructor(private service: CrudService, private router: Router) { }

  ngOnInit(): void {
    this.service.getList('inventory').subscribe((res: AppResponse) => {
      this.dataSource = res.data.content
    }
    );
  }

  
  delete(index: number) {
    let id = this.dataSource[index].id as number;
    this.service.delete(id, "inventory").subscribe(() => {
      const newData = this.dataSource.filter((s, i) => i != index);
      this.dataSource = newData;
    })
  }

  edit(index: number) {
    this.service.data = { ...this.dataSource[index] };
    this.router.navigate(['/inventory-form']);
  }

}
