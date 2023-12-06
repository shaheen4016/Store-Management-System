import { Component } from '@angular/core';
import { MatDrawerMode } from '@angular/material/sidenav';
import { AuthService } from 'src/app/services/auth/auth.service';

interface SidenavConfig {
  shouldRun: boolean | null;
  hasBackdrop: boolean | null;
  mode: MatDrawerMode;
  position: 'start' | 'end';
  isOpened: boolean;
}

interface Menu {
  title: string;
  link?: string;
  submenu?: Menu[];
}

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {

  config: SidenavConfig = {
    shouldRun: true,
    hasBackdrop: false,
    mode: 'side',
    position: 'start',
    isOpened: true,
  }

  menues: Menu[] = [

    // project authority
    {
      title: "Auth",
      submenu: [
        {
          title: "Permission",
          submenu: [
            {
              title: 'Form',
              link: 'permission-form'
            },
            {
              title: 'List',
              link: 'permission-list'
            }
          ]
        },
        {
          title: "User",
          submenu: [
            {
              title: 'Form',
              link: 'user-form'
            },
            {
              title: 'List',
              link: 'user-list'
            }
          ]
        },
        {
          title: "Role",
          submenu: [
            {
              title: 'Form',
              link: 'role-form'
            },
            {
              title: 'List',
              link: 'role-list'
            }
          ]
        },
      ]
    },




    //project configuration
    {
      title: "Project Configuration",
      submenu: [
        {
          title: "Customer",
          submenu: [
            {
              title: 'Form',
              link: 'customer-form'
            },
            {
              title: 'List',
              link: 'customer-list'
            }
          ]
        },

        {
          title: "Employee",
          submenu: [
            {
              title: 'Form',
              link: 'employee-form'
            },
            {
              title: 'List',
              link: 'employee-list'
            }
          ]
        },

        {
          title: "Category",
          submenu: [
            {
              title: 'Form',
              link: 'category-form'
            },
            {
              title: 'List',
              link: 'category-list'
            }
          ]
        },

        {
          title: "Discount",
          submenu: [
            {
              title: 'Form',
              link: 'discount-form'
            },
            {
              title: 'List',
              link: 'discount-list'
            }
          ]
        },

        {
          title: "Product",
          submenu: [
            {
              title: 'Form',
              link: 'product-form'
            },
            {
              title: 'List',
              link: 'product-list'
            }
          ]
        },

        {
          title: "Supplier",
          submenu: [
            {
              title: 'Form',
              link: 'supplier-form'
            },
            {
              title: 'List',
              link: 'supplier-list'
            }
          ]
        },

      ]
    },



    //project data 
    {
      title: "project data",
      submenu: [
        {
          title: "Inventory",
          submenu: [
            {
              title: 'Form',
              link: 'inventory-form'
            },
            {
              title: 'List',
              link: 'inventory-list'
            }
          ]
        },

        {
          title: "Order",
          submenu: [
            {
              title: 'Form',
              link: 'order-form'
            },
            {
              title: 'List',
              link: 'order-list'
            }
          ]
        },

        {
          title: "OrderItem",
          submenu: [
            {
              title: 'Form',
              link: 'OrderItem-form'
            },
            {
              title: 'List',
              link: 'OrderItem-list'
            }
          ]
        },

        {
          title: "Payment",
          submenu: [
            {
              title: 'Form',
              link: 'payment-form'
            },
            {
              title: 'List',
              link: 'payment-list'
            }
          ]
        },

        {
          title: "Purchase",
          submenu: [
            {
              title: 'Form',
              link: 'purchase-form'
            },
            {
              title: 'List',
              link: 'purchase-list'
            }
          ]
        },

        {
          title: "Review",
          submenu: [
            {
              title: 'Form',
              link: 'review-form'
            },
            {
              title: 'List',
              link: 'review-list'
            }
          ]
        },

        {
          title: "Transaction",
          submenu: [
            {
              title: 'Form',
              link: 'transaction-form'
            },
            {
              title: 'List',
              link: 'transaction-list'
            }
          ]
        }


      ]
    }
  ]

  constructor(private authService: AuthService) {}

  logout() {
    this.authService.logout();
  }

}
