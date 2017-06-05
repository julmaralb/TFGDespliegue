"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require('@angular/core');
var router_1 = require('@angular/router');
var login_component_1 = require('./login/login.component');
var register_component_1 = require('./register/register.component');
var map_component_1 = require('./homeMap/map.component');
var plazas_list_component_1 = require('./listadoPlazas/plazas-list.component');
var zona_deseada_component_1 = require('./zonasDeseadas/zona-deseada.component');
var editar_usuario_component_1 = require('./editarUsuario/editar-usuario.component');
var crear_propuesta_component_1 = require('./crearPropuesta/crear-propuesta.component');
var editar_plaza_component_1 = require('./editarPlaza/editar-plaza.component');
var listar_propuestas_enviadas_component_1 = require('./listarPropuestas/listar-propuestas-enviadas.component');
var listar_propuestas_recibidas_component_1 = require('./listarPropuestas/listar-propuestas-recibidas.component');
var listar_permutas_component_1 = require('./listarPermutas/listar-permutas.component');
var routes = [
    { path: '', component: map_component_1.MapComponent },
    { path: 'login', component: login_component_1.LoginComponent },
    { path: 'register', component: register_component_1.RegisterComponent },
    { path: 'list', component: plazas_list_component_1.PlazasListComponent },
    { path: 'zonas', component: zona_deseada_component_1.ZonaDeseadaComponent },
    { path: 'editaUsuario', component: editar_usuario_component_1.EditarUsuarioComponent },
    { path: 'editaPlaza', component: editar_plaza_component_1.EditarPlazaComponent },
    { path: 'crearPropuesta/:id', component: crear_propuesta_component_1.CrearPropuestaComponent },
    { path: 'propuestasEnviadas', component: listar_propuestas_enviadas_component_1.ListarPropuestasEnviadasComponent },
    { path: 'propuestasRecibidas', component: listar_propuestas_recibidas_component_1.ListarPropuestasRecibidasComponent },
    { path: 'historialPermutas', component: listar_permutas_component_1.ListarPermutasComponent },
    { path: '**', redirectTo: '' }
];
var AppRoutingModule = (function () {
    function AppRoutingModule() {
    }
    AppRoutingModule = __decorate([
        core_1.NgModule({
            imports: [router_1.RouterModule.forRoot(routes)],
            exports: [router_1.RouterModule]
        }), 
        __metadata('design:paramtypes', [])
    ], AppRoutingModule);
    return AppRoutingModule;
}());
exports.AppRoutingModule = AppRoutingModule;
//# sourceMappingURL=app-routing.module.js.map