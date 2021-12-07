import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {EmpresaListComponent} from "../empresa/components/empresa-list/empresa-list.component";
import {CandidatoListComponent} from "./components/candidato-list/candidato-list.component";
import {CandidatoFormComponent} from "./components/candidato-form/candidato-form.component";

const routes: Routes = [
  {
    path: '',
    component: CandidatoListComponent,
  },
  {
    path: 'new',
    component: CandidatoFormComponent,
  },
  {
    path: ':id',
    component: CandidatoFormComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CandidatoRoutingModule { }
