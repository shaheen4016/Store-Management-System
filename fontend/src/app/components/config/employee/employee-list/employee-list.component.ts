import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Customer } from 'src/app/model/config/customer-model';
import { Employee } from 'src/app/model/config/employee-model';
import { CrudService } from '../../../../services/crud.service';
import { AppResponse } from 'src/app/dto/response.dto';


@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.scss']
})
export class EmployeeListComponent implements OnInit {

  displayedColumns: string[] = ['firstName','lastName','email','phoneNumber','hireDate','position','salary', 'actions'];
  dataSource: Employee[] = [];

  constructor(private service: CrudService, private router: Router) { }

  ngOnInit(): void {
    this.service.getList('employee').subscribe((res: AppResponse) => {
      this.dataSource = res.data.content
    }
    );
  }

  
  delete(index: number) {
    let id = this.dataSource[index].id as number;
    this.service.delete(id, "employee").subscribe(() => {
      const newData = this.dataSource.filter((s, i) => i != index);
      this.dataSource = newData;
    })
  }

  edit(index: number) {
    this.service.data = { ...this.dataSource[index] };
    this.router.navigate(['/employee-form']);
  }

  

}