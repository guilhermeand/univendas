import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {CandidatoRoutingModule} from './candidato-routing.module';
import {CandidatoListComponent} from './components/candidato-list/candidato-list.component';
import {CandidatoFormComponent} from './components/candidato-form/candidato-form.component';
import {SharedModule} from "../../share/shared.module";


@NgModule({
  declarations: [
    CandidatoListComponent,
    CandidatoFormComponent,
  ],
  imports: [
    CommonModule,
    CandidatoRoutingModule,
    SharedModule
  ]
})
export class CandidatoModule { }
