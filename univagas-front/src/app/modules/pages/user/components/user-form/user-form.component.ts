import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../services/user.service";
import {ToastrService} from "ngx-toastr";
import {ActivatedRoute, Router} from "@angular/router";
import {User} from "../../models/user";

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent implements OnInit {
  form: FormGroup = new FormGroup({});
  isSaving: boolean = false;
  id: string | null = '';

  constructor(
    private userService: UserService,
    private toastrService: ToastrService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
  ) {
  }

  ngOnInit(): void {
    this.form = new FormGroup({
      username: new FormControl('', Validators.required),
      email: new FormControl('', [Validators.required, Validators.email]),
      senha: new FormControl('', Validators.required),
    });
    this.id = this.activatedRoute.snapshot.paramMap.get('id');
    if (this.id) {
      this.userService.find(this.id).subscribe(
        (result: User) => {
          Object.keys(this.form.controls).forEach(
            (key) => {
              this.form.get(key)?.setValue((result as any)[key]);
            }
          )
        }
      );
    }
  }

  save() {
    if (this.form.valid && !this.isSaving) {
      this.isSaving = true;
      this.userService.save(this.form.getRawValue()).subscribe(
        (result: any) => {
          this.toastrService.success('Usuário salvo com sucesso!');
          this.isSaving = false;
          this.router.navigateByUrl('user').then(
            (r: any) => {
            }
          ).catch(
            (error: any) => this.toastrService.error('Erro ao voltar para a listagem')
          )
        },
        error => {
          this.toastrService.error('Erro ao criar usuário!');
          this.isSaving = false;
        }
      );
    }
  }

  update() {
    if (this.form.valid && !this.isSaving && this.id) {
      this.isSaving = true;
      this.userService.update(this.form.getRawValue(), this.id).subscribe(
        (result: any) => {
          this.toastrService.success('Dados do candidato alterado com sucesso!');
          this.isSaving = false;
          this.router.navigateByUrl('user').then(
            (r: any) => {
            }
          ).catch(
            (error: any) => this.toastrService.error('Erro ao voltar para a listagem')
          )
        },
        error => {
          this.toastrService.error('Erro ao editar os dados do usuário!');
          this.isSaving = false;
        }
      );
    }
  }
}
