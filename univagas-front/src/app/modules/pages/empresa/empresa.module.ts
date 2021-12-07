import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {EmpresaRoutingModule} from './empresa-routing.module';
import {SharedModule} from "../../share/shared.module";
import {EmpresaFormComponent} from "./components/empresa-form/empresa-form.component";
import {EmpresaListComponent} from "./components/empresa-list/empresa-list.component";


@NgModule({
  declarations: [
    EmpresaListComponent,
    EmpresaFormComponent,
  ],
  imports: [
    CommonModule,
    EmpresaRoutingModule,
    SharedModule,
  ]
})
export class EmpresaModule { }
