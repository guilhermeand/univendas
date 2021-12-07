import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {EmpresaListComponent} from "./components/empresa-list/empresa-list.component";
import {EmpresaFormComponent} from "./components/empresa-form/empresa-form.component";

const routes: Routes = [
  {
    path: '',
    component: EmpresaListComponent,
  },
  {
    path: 'new',
    component: EmpresaFormComponent,
  },
  {
    path: ':id',
    component: EmpresaFormComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EmpresaRoutingModule {
}
