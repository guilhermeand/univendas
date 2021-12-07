import {Component, OnInit} from '@angular/core';
import {Empresa} from "../../../empresa/models/empresa";
import {EmpresaService} from "../../../empresa/services/empresa.service";
import {ActivatedRoute, Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-empresa-list',
  templateUrl: './empresa-list.component.html',
  styleUrls: ['./empresa-list.component.css']
})
export class EmpresaListComponent implements OnInit {

  users: Array<Empresa> = new Array<Empresa>();
  displayedColumns: string[] = [
    'id', 'razaosocial', 'nomefantasia', 'usuario', 'cnpj', 'endereco', 'actions'
  ];
  loadingData: boolean = false;
  removingData: boolean = false;

  constructor(
    private empresaService: EmpresaService,
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
    this.empresaService.list().subscribe(
      (result: Array<Empresa>) => {
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
      this.empresaService.delete(id).subscribe(
        (result: any) => {
          this.removingData = false;
          this.toastrService.success('Empresa removida com sucesso!');
          this.list();
        }
      );
    }
  }
}
