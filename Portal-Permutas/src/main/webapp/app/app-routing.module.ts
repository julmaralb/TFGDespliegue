import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { AppComponent } from './app.component';
import { MapComponent } from './homeMap/map.component';
import { PlazasListComponent } from './listadoPlazas/plazas-list.component';
import { ZonaDeseadaComponent } from './zonasDeseadas/zona-deseada.component';
import { EditarUsuarioComponent } from './editarUsuario/editar-usuario.component';
import { CrearPropuestaComponent } from './crearPropuesta/crear-propuesta.component';
import { EditarPlazaComponent } from './editarPlaza/editar-plaza.component';
import { ListarPropuestasEnviadasComponent } from './listarPropuestas/listar-propuestas-enviadas.component';
import { ListarPropuestasRecibidasComponent } from './listarPropuestas/listar-propuestas-recibidas.component';
import { ListarPermutasComponent } from './listarPermutas/listar-permutas.component';

const routes: Routes = [
  { path: '', component: MapComponent},
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'list', component: PlazasListComponent },
  { path: 'zonas', component: ZonaDeseadaComponent },
  { path: 'editaUsuario', component: EditarUsuarioComponent },
  { path: 'editaPlaza', component: EditarPlazaComponent },
  { path: 'crearPropuesta/:id', component: CrearPropuestaComponent },
  { path: 'propuestasEnviadas', component: ListarPropuestasEnviadasComponent },
  { path: 'propuestasRecibidas', component: ListarPropuestasRecibidasComponent },
  { path: 'historialPermutas', component: ListarPermutasComponent },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}