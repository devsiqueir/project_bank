import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CadastroClienteComponent } from './feature/feature-cliente/components/cadastro-cliente/cadastro-cliente.component';


const routes: Routes = [
    {
        path: 'cliente/:id',
        component: CadastroClienteComponent
    },
    {
      path: 'cliente',
      component: CadastroClienteComponent
  }
];
@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]

})
export class AppRoutingModule {}