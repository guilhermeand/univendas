import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

const routes: Routes = [
  {
    path: 'user',
    loadChildren: () => import('./user/user.module').then(module => module.UserModule)
  },
  {
    path: 'candidato',
    loadChildren: () => import('./candidato/candidato.module').then(module => module.CandidatoModule)
  },
  {
    path: 'empresa',
    loadChildren: () => import('./empresa/empresa.module').then(module => module.EmpresaModule)
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PagesRoutingModule { }
