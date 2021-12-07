import { Component, OnInit } from '@angular/core';
import {User} from "../../../user/models/user";
import {UserService} from "../../../user/services/user.service";
import {ActivatedRoute, Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";
import {CandidatoService} from "../../services/candidato.service";
import {Candidato} from "../../models/candidato";

@Component({
  selector: 'app-candidato-list',
  templateUrl: './candidato-list.component.html',
  styleUrls: ['./candidato-list.component.css']
})
export class CandidatoListComponent implements OnInit {

  users: Array<Candidato> = new Array<Candidato>();
  displayedColumns: string[] = [
    'id', 'nome', 'usuario', 'cpf', 'endereco', 'actions'
  ];
  loadingData: boolean = false;
  removingData: boolean = false;

  constructor(
    private candidatoService: CandidatoService,
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
    this.candidatoService.list().subscribe(
      (result: Array<Candidato>) => {
        this.loadingData = false;
        this.users = result;
      }
    )
  }

  edit(id: number) {
    this.router.navigate([id], {relativeTo: this.route});
  }

  delete(id: number) {
    if (!this.removingData) {
      this.removingData = true;
      this.candidatoService.delete(id).subscribe(
        (result: any) => {
          this.removingData = false;
          this.toastrService.success('Usuário removido com sucesso!');
          this.list();
        }
      );
    }
  }
}
