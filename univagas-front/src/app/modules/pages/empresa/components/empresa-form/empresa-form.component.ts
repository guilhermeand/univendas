import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {User} from "../../../user/models/user";
import {UserService} from "../../../user/services/user.service";
import {ToastrService} from "ngx-toastr";
import {ActivatedRoute, Router} from "@angular/router";
import {EmpresaService} from "../../services/empresa.service";
import {Empresa} from "../../models/empresa";

@Component({
  selector: 'app-empresa-form',
  templateUrl: './empresa-form.component.html',
  styleUrls: ['./empresa-form.component.css']
})
export class EmpresaFormComponent implements OnInit {
  form: FormGroup = new FormGroup({});
  isSaving: boolean = false;
  id: string | null = '';
  fb: FormBuilder = new FormBuilder();
  users: Array<User> = new Array<User>();

  constructor(
    private empresaService: EmpresaService,
    private userService: UserService,
    private toastrService: ToastrService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
  ) {
  }

  ngOnInit(): void {
    this.userService.list().subscribe(
      (result: Array<User>) => {
        this.users = result;
      }
    )
    this.form = new FormGroup({
      razaosocial: new FormControl('', Validators.required),
      nomefantasia: new FormControl('', [Validators.required]),
      cnpj: new FormControl('', Validators.required),
      usuario: this.fb.group({
        id: new FormControl(''),
        email: new FormControl(''),
        username: new FormControl(''),
      }),
      endereco: this.fb.group({
        id: new FormControl(''),
        logradouro: new FormControl(''),
        complemento: new FormControl(''),
        bairro: new FormControl(''),
        cidade: new FormControl(''),
        estado: new FormControl(''),
        pais: new FormControl(''),
        cep: new FormControl(''),
      })
    });
    this.id = this.activatedRoute.snapshot.paramMap.get('id');
    if (this.id) {
      this.empresaService.find(this.id).subscribe(
        (result: Empresa) => {
          Object.keys(this.form.controls).forEach(
            (key) => {
              if (key === 'usuario') {
                this.form.get(key)?.patchValue({
                  id: result.usuario.id,
                  email: result.usuario.email,
                  username: result.usuario.username,
                });
              } else {
                this.form.get(key)?.setValue((result as any)[key]);
              }
            }
          )
        }
      );
    }
  }

  save() {
    if (this.form.valid && !this.isSaving) {
      this.isSaving = true;
      this.empresaService.save(this.form.getRawValue()).subscribe(
        (result: any) => {
          this.toastrService.success('Empresa salva com sucesso!');
          this.isSaving = false;
          this.router.navigateByUrl('empresa').then(
            (r: any) => {
            }
          ).catch(
            (error: any) => this.toastrService.error('Erro ao voltar para a listagem')
          )
        },
        error => {
          this.toastrService.error('Erro ao cadastrar empresa!');
          this.isSaving = false;
        }
      );
    }
  }

  update() {
    if (this.form.valid && !this.isSaving && this.id) {
      this.isSaving = true;
      this.empresaService.update(this.form.getRawValue(), this.id).subscribe(
        (result: any) => {
          this.toastrService.success('Dados da empresa alterados com sucesso!');
          this.isSaving = false;
          this.router.navigateByUrl('empresa').then(
            (r: any) => {
            }
          ).catch(
            (error: any) => this.toastrService.error('Erro ao voltar para a listagem')
          )
        },
        error => {
          this.toastrService.error('Erro ao alterar os dados da empresa!');
          this.isSaving = false;
        }
      );
    }
  }

  dataNascimentoFilter(d: Date | null): boolean {
    if (d) {
      d.setHours(0, 0, 0, 0);
      const now = new Date();
      now.setHours(0, 0, 0, 0);
      return d <= now;
    }
    return false;
  }

  userCompareWith(option1: number, option2: number): boolean {
    return option1 === option2;
  }
}
