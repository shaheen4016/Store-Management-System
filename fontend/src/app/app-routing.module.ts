import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RoleFormComponent } from './components/auth/role/role-form/role-form.component';
import { RoleListComponent } from './components/auth/role/role-list/role-list.component';
import { CustomerFormComponent } from './components/config/customer/customer-form/customer-form.component';
import { CustomerListComponent } from './components/config/customer/customer-list/customer-list.component';
import { EmployeeFormComponent } from './components/config/employee/employee-form/employee-form.component';
import { EmployeeListComponent } from './components/config/employee/employee-list/employee-list.component';
import { ProductFormComponent } from './components/config/product/product-form/product-form.component';
import { ProductListComponent } from './components/config/product/product-list/product-list.component';
import { SupplierFormComponent } from './components/config/supplier/supplier-form/supplier-form.component';
import { SupplierListComponent } from './components/config/supplier/supplier-list/supplier-list.component';
import { CategoryFormComponent } from './components/config/category/category-form/category-form.component';
import { CategoryListComponent } from './components/config/category/category-list/category-list.component';
import { DiscountFormComponent } from './components/config/discount/discount-form/discount-form.component';
import { DiscountListComponent } from './components/config/discount/discount-list/discount-list.component';
import { InventoryFormComponent } from './components/data/inventory/inventory-form/inventory-form.component';
import { InventoryListComponent } from './components/data/inventory/inventory-list/inventory-list.component';
import { OrderFormComponent } from './components/data/order/order-form/order-form.component';
import { OrderListComponent } from './components/data/order/order-list/order-list.component';
import { OrderItemFormComponent } from './components/data/orderItem/order-item-form/order-item-form.component';
import { OrderItemListComponent } from './components/data/orderItem/order-item-list/order-item-list.component';
import { PaymentFormComponent } from './components/data/payment/payment-form/payment-form.component';
import { PaymentListComponent } from './components/data/payment/payment-list/payment-list.component';
import { ReviewFormComponent } from './components/data/review/review-form/review-form.component';
import { ReviewListComponent } from './components/data/review/review-list/review-list.component';
import { TransactionFormComponent } from './components/data/transaction/transaction-form/transaction-form.component';
import { TransactionListComponent } from './components/data/transaction/transaction-list/transaction-list.component';
import { BlankPageComponent } from './components/blank-page/blank-page.component';
import { DashboardComponent } from './components/layout/dashboard/dashboard.component';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { LoginComponent } from './components/login-page/login/login.component';
import { canActivate, canActivateChild } from './services/auth/auth-guard';
import { AuthInterceptor } from './services/interceptors/auth-interceptor.service';
import { PermissionFormComponent } from './components/auth/permission/permission-form/permission-form.component';
import { PermissionListComponent } from './components/auth/permission/permission-list/permission-list.component';
import { UserFormComponent } from './components/auth/user/user-form/user-form.component';
import { UserListComponent } from './components/auth/user/user-list/user-list.component';
import { AboutUsComponent } from './components/layout/about-us/about-us.component';
import { SalesReportComponent } from './components/layout/sales-report/sales-report.component';
import { InventoryReportComponent } from './components/layout/inventory-report/inventory-report.component';
import { PurchaseFormComponent } from './components/data/purchase/purchase-form/purchase-form.component';
import { PurchaseListComponent } from './components/data/purchase/purchase-list/purchase-list.component';


const routes: Routes =
  [
    {
      path: '',
      component: DashboardComponent,
      canActivate: [canActivate],
      canActivateChild: [canActivateChild],
      children: [
        { path: 'role-form', component: RoleFormComponent },
        { path: 'role-list', component: RoleListComponent },
        { path: 'customer-form', component: CustomerFormComponent },
        { path: 'customer-list', component: CustomerListComponent },
        { path: 'employee-form', component: EmployeeFormComponent },
        { path: 'employee-list', component: EmployeeListComponent },
        { path: 'product-form', component: ProductFormComponent },
        { path: 'product-list', component: ProductListComponent },
        { path: 'supplier-form', component: SupplierFormComponent },
        { path: 'supplier-list', component: SupplierListComponent },
        { path: 'category-form', component: CategoryFormComponent },
        { path: 'category-list', component: CategoryListComponent },
        { path: 'discount-form', component: DiscountFormComponent },
        { path: 'discount-list', component: DiscountListComponent },
        { path: 'inventory-form', component: InventoryFormComponent },
        { path: 'inventory-list', component: InventoryListComponent },
        { path: 'order-form', component: OrderFormComponent },
        { path: 'order-list', component: OrderListComponent },
        { path: 'orderItem-form', component: OrderItemFormComponent },
        { path: 'orderItem-list', component: OrderItemListComponent },
        { path: 'payment-form', component: PaymentFormComponent },
        { path: 'payment-list', component: PaymentListComponent },
        { path: 'review-form', component: ReviewFormComponent },
        { path: 'review-list', component: ReviewListComponent },
        { path: 'transaction-form', component: TransactionFormComponent },
        { path: 'transaction-list', component: TransactionListComponent },
        { path: 'permission-form', component: PermissionFormComponent },
        { path: 'permission-list', component: PermissionListComponent },
        { path: 'user-form', component: UserFormComponent },
        { path: 'user-list', component: UserListComponent },
        { path: 'about', component: AboutUsComponent},
        { path: 'sales-report', component: SalesReportComponent},
        { path: 'inventory-report', component: InventoryReportComponent},
        { path: 'purchase-form', component: PurchaseFormComponent},
        { path: 'purchase-list', component: PurchaseListComponent},


        // {path: '', component: RoleFormComponent},
        // {path: 'list', component: RoleListComponent},


      ]
    },
    {
      path: 'login', component: LoginComponent
    },
  ];

// @NgModule({
//   imports: [RouterModule.forRoot(routes)],
//   exports: [RouterModule]
// })
// export class AppRoutingModule { }

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ],
})
export class AppRoutingModule { }
