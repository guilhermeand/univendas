import {Component, OnInit} from '@angular/core';
import {UserService} from "../../services/user.service";
import {User} from "../../models/user";
import {ActivatedRoute, Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  users: Array<User> = new Array<User>();
  displayedColumns: string[] = [
    'id', 'username', 'email', 'actions'
  ];
  loadingData: boolean = false;
  removingData: boolean = false;

  constructor(
    private userService: UserService,
    private router: Router,
    private route: ActivatedRoute,
    private toastrService: ToastrService,
  ) {
  }

  ngOnInit(): void {
    this.list();
  }

  list() {
    this.loadingData = true;
    this.userService.list().subscribe(
      (result: Array<User>) => {
        this.loadingData = false;
        this.users = result;
      }
    );
  }

  edit(id: number) {
    this.router.navigate([id], {relativeTo: this.route});
  }

  delete(id: number) {
    if (!this.removingData) {
      this.removingData = true;
      this.userService.delete(id).subscribe(
        (result: any) => {
          this.removingData = false;
          this.toastrService.success('Usuário removido com sucesso!');
          this.list();
        }
      );
    }
  }
}
